# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Create build pack 'pack-b1-numbers-sol.tgz.uue' from content in pack folder
# 'pack-b1-numbers-sol':
# - tar cvfz - -C pack-b1-numbers-sol . | ccrypt -efK "$pwd" | uuencode - > pack-b1-numbers-sol.tgz.uue
# - tar cvfz - -C pack-b2-streams-sol . | ccrypt -efK "$pwd" | uuencode - > pack-b2-streams-sol.tgz.uue
# 
# Applying the pack installs files from the pack folder into the project:
# - uudecode -o - < pack-b1-numbers-sol.tgz.uue | ccrypt -dfK "$pwd" | tar tfvz -
# 
# un-tar patch from .tgz and pipe to patch command using -O flag (O, not zero)
# - tar xOf tmp/NumbersImpl_patches.tgz NumbersImpl_0_sum0.patch | patch -p1
# 

# Overall build() function.
function build() {
    [ -z "$1" ] && local build_dir="se1-play" || local build_dir="$1"
    # 
    # build 'se1-play' project (main) in $build_dir
    build_se1_play "$build_dir"
    # 
    # build empty commit as base for branches 'b1-numbers' and 'b2-streams'
    build_base
    # 
    # supplement branches 'b1-numbers' and 'b1-numbers-sol'
    build_numbers
    build_numbers_sol
    build_numbers_completed     # 'b1-numbers' with all commits completed

    # supplement branches 'b2-streams' and 'b2-streams-sol'
    build_streams
    build_streams_sol
}


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Probe build_dir ($1, "se1-play") exists, create if not, and cd into it.
# Build project with initial "src" and "tests" files extracted from README.md
# and commit:
#  - 712313b (HEAD -> main) add junit tests
#  - 315d513 add src
#  - 996e14c add .gitmodules
#  - b78f544 add .gitignore
#  - a836206 (tag: root) root commit (empty)
# 
# @param $1 folder to build project in
# 
function build_se1_play() {

    [ -z "$1" ] && local build_dir="se1-play" || local build_dir="$1"

    [[ $(pwd) =~ "$build_dir"$ ]] || { \
        [ ! -d "$build_dir" ] && echo mkdir -p "$build_dir" && mkdir -p "$build_dir"; \
        builtin cd "$build_dir"
    }

    [ ! -d ".git" ] && \
        git init && \
        git commit --allow-empty -m "root commit (empty)" && \
        git tag root

    [ ! -f ".gitignore" ] && \
        curl -o .gitignore https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/.gitignore

    [ -f .gitignore -a -z "$(git log --oneline | grep 'add .gitignore')" ] && \
        git add -f ".gitignore" && \
        git commit -m "add .gitignore"

    # import '.env' submodule from branch 'env' of the remote repository
    [ ! -d .env ] && \
        git submodule add -b env -f -- https://github.com/sgra64/se1-play.git .env && \
        prune_submodule env

    # import '.vscode' submodule from branch 'vscode-settings' of the remote repository
    [ ! -d .vscode ] && \
        git submodule add -b vscode-settings -f -- https://github.com/sgra64/se1-play.git .vscode && \
        prune_submodule vscode-settings

    # import '.libs' submodule from branch 'libs' of the remote repository
    if [ ! -d "libs" ]; then
        for libs in "../libs" "branches/libs" "../se1-play/branches/libs"; do
            [ -d "$libs" -o -L "$libs" ] && local libs_path="$libs" && break
        done
        # [ -d ../libs -o -L ../libs ] && local libs_path="../libs"
        # [ -d branches/libs -o -L branches/libs ] && local libs_path="branches/libs"
        [ "$libs_path" ] && ln -s "$libs_path" "libs" || { \
            git submodule add -b libs -f -- https://github.com/sgra64/se1-play.git "libs" && \
            prune_submodule libs; }
    fi

    # don't commit git submodules
    [ -f .gitmodules -a -z "$(git log --oneline | grep 'add .gitmodules')" ] && \
        git add .gitmodules && \
        git commit -m "add .gitmodules"

    for f in src.module-info.java \
            src.application.package-info.java \
            src.application.Application.java \
            tests.application.Application_0_always_pass_Tests.java
    do
        local filename=$(tr '.' '/' <<< $f | sed -e 's/\/java/.java/')
        if [ ! -f "$filename" ]; then
            [ ! -f README.md ] && \
                curl -o README.md https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/README.md
            # 
            local filepath=$(dirname "$filename")
            [ ! -d "$filepath" ] && mkdir -p "$filepath"
            sed -n "/@@ $f @BEGIN/,/@@ $f @END/p" < README.md \
            | sed -e '1,/java/d' -e '/```/,$d' > "$filename"
            echo "created: $filename"
        fi
    done
    rm -f README.md

    [ -d src -a -z "$(git log --oneline | grep 'add src')" ] && \
        git add src && \
        git commit -m "add src"

    [ -d tests -a -z "$(git log --oneline | grep 'add junit tests')" ] && \
        git add tests && \
        git commit -m "add junit tests"

    [ ! -L env.sh ] && ln -s .env/env.sh .env.sh
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Build empty commit as base for branches 'b1-numbers' and 'b2-streams'.
function build_base() {
    # create empty commit and tag as "base" for the new branch 'b1-numbers'
    [ -z "$(git tag -l base)" ] && \
        git commit --allow-empty -m "branch commit (empty)" && \
        git tag base

    # set remote URL to repository to fetch branches
    [ -z "$(git remote -v | grep se1-play-repo)" ] && \
        git remote add se1-play-repo git@github.com:sgra64/se1-play.git
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Build branch 'b1-numbers' on-top of project 'se1-play'.
function build_numbers() {
    # switch to branch 'b1-numbers', create off base commit, if not exists
    [ "$(git show-ref refs/heads/b1-numbers)" ] && \
        git switch b1-numbers || git switch -c b1-numbers base

    # fetch branch 'b1-numbers' from remote repository 'se1-play-repo'
    git fetch se1-play-repo b1-numbers

    # merge content of branch 'b1-numbers' into the 'main' branch of the project with:
    # '--squash' combine all incoming commits into one local commit
    # '--allow-unrelated-histories' allows merging from a repository with no shared history
    # '--strategy-option theirs' resolves merge conflicts favoring incoming changes
    # 
    git merge se1-play-repo/b1-numbers \
        --squash \
        --allow-unrelated-histories \
        --strategy-option theirs
    # 
    # alternatively (mind the ' ' vs '/' between remote and branch):
    # git pull se1-play-repo b1-numbers \
    #     --squash --allow-unrelated-histories --strategy-option theirs
    # git commit -m "merge branch se1-play-repo/b1-numbers"
    # 
    git commit -m "merge branch se1-play-repo/b1-numbers"
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Build branch 'b1-numbers-sol' on-top of branch 'b1-numbers'.
function build_numbers_sol() {
    [ -z "$pwd" ] && echo "no password: export pwd=\"\"" && return 1

    # solution branch 'b1-numbers-sol' created off branch 'b1-numbers'
    [ "$(git show-ref refs/heads/b1-numbers-sol)" ] && \
        git switch b1-numbers-sol || git switch -c b1-numbers-sol

    # extract content of 'pack-b1-numbers-sol' into working tree
    curl https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/build/pack-b1-numbers-sol.tgz.uue | \
        uudecode -o - | ccrypt -dfK "$pwd" | \
        tar xfvz -

    # apply and remove extracted .patch files
    for patch in $(find src -name '*.patch'); do
        local patched_file="${patch/.patch/}"
        if [ -f "$patched_file" ]; then
            patch -p1 < "$patch"
            rm "$patch"
        else
            # collect 'NumbersImpl_[0-9]*' patches in 'tmp/NumbersImpl_patches.tar'
            # used later in build_numbers_completed()
            [ ! -d tmp ] && mkdir tmp
            [[ "$patch" =~ NumbersImpl_[0-9] ]] && \
                tar uvf tmp/NumbersImpl_patches.tar "$patch" && rm "$patch" || \
                echo "patched file $patched_file does not exist for patch: $patch"
        fi
    done

    # commit pack-b1-numbers update
    git add src
    git commit -m "b1-numbers solution update, NumbersImpl.java"

    # pull 'b1-numbers-tests' from remote
    git pull se1-play-repo b1-numbers-tests \
        --squash --allow-unrelated-histories --strategy-option theirs
    git commit -m "pull branch se1-play-repo/b1-numbers-tests"
}


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Complete branch 'b1-numbers' with solution commits, assumes existing branch
# 'b1-numbers-sol'.
# 
# Patch files were incrementally created by committing prior 'NumbersImpl.java'
# and completing the next function, then creating the diff to the prior commit
# or to the full implementation in branch 'b1-numbers-sol':
# - git diff > NumbersImpl_0_sum0.patch
# - git diff b1-numbers-sol -- src/numbers/NumbersImpl.java > NumbersImpl_0_sum0.patch
# 
function build_numbers_completed() {
    # 
    # switch to branch 'b1-numbers'
    git switch b1-numbers

    # fetch from branch 'b1-numbers-sol'
    git restore --source b1-numbers-sol -- src/module-info.java
    git restore --source b1-numbers-sol -- src/numbers/NumbersImplAlgorithms.java
    git add src
    git commit -m "add solution src"

    # extract 'NumbersImpl_[0-9]*' patches from .jar file created in build_numbers_sol()
    if [ -f tmp/NumbersImpl_patches.tar ]; then
        echo "--> extracting patches from 'tmp/NumbersImpl_patches.tar'"
        tar xvf tmp/NumbersImpl_patches.tar
        rm tmp/NumbersImpl_patches.tar          # remove .tar
        [ -z "$(ls tmp)" ] && rmdir tmp         # remove 'tmp' if empty
    else
        echo "no file: 'NumbersImpl_patches.tar'"
        return 1
    fi
    # 
    # test branch 'b1-numbers-sol' exists
    if [ "$(git show-ref refs/heads/b1-numbers-sol)" ]; then
        # 
        # restore 'Numbers.java' and 'NumbersImpl.java' from branch 'b1-numbers-sol'
        git restore --source b1-numbers-sol -- \
            src/numbers/Numbers.java \
            src/numbers/NumbersImpl.java
        # 
        # iterate over NumbersImpl_[0-9]* patches
        for patch in $(find src -name 'NumbersImpl_[0-9]*.patch'); do
            local name="${patch/.patch/}"       # remote traling '.patch'
            local func="${name/*_[0-9]_/}"      # remote up to last '_'
            local restore=""                    # restore files from branch 'b1-numbers-sol'
            local msg="$func() complete"
            # 
            echo "--> applying patch for: $func()"
            patch -p1 < "$patch"                # apply patch
            rm "$patch"                         # remove patch file
            # 
            case "$func" in
            sum_0) msg="sum() returns 0" ;;
            sum) restore="tests/numbers/Numbers_1_sum_Tests.java" ;;
            sum_positive_even_numbers) restore="tests/numbers/Numbers_2_sum_positive_even_Tests.java" ;;
            sum_recursive) restore="tests/numbers/Numbers_3_sum_recursion_Tests.java" ;;
            findFirst) restore="tests/numbers/Numbers_4_find_first_Tests.java" ;;
            findLast) restore="tests/numbers/Numbers_5_find_last_Tests.java" ;;
            findAll)
                restore="tests/numbers/Numbers_6_find_all_Tests.java \
                    tests/numbers/Matchers.java"
                ;;
            findSums)
                restore="tests/numbers/Numbers_7a_find_sums_Tests.java \
                    tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java"
                ;;
            findAllSums)
                restore="tests/numbers/Numbers_8a_find_all_sums_Tests.java \
                    tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java"
                ;;
            *) echo "no function: $func"; msg=""
            esac
            # 
            # restore files from branch 'b1-numbers-sol'
            [ "$restore" ] && git restore --source b1-numbers-sol -- $restore
            # 
            # commit changes
            [ "$msg" ] && echo "--> git commit -m \"$msg\"" && \
                git add $(find src tests -name '*.java') && \
                git commit -m "$msg"
        done
    else
        echo "no local branch: 'b1-numbers-sol'"
        return 1
    fi
    return 0
}


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Build branch 'b2-streams' on-top of project 'se1-play'.
function build_streams() {
    # switch to branch 'b2-streams', create off base commit, if not exists
    [ "$(git show-ref refs/heads/b2-streams)" ] && \
        git switch b2-streams || git switch -c b2-streams base

    # pull 'b2-streams' from remote
    git pull se1-play-repo b2-streams \
        --squash --allow-unrelated-histories --strategy-option theirs
    # 
    git commit -m "pull branch se1-play-repo/b2-streams"

    # pull 'b2-streams-tests' from remote
    git pull se1-play-repo b2-streams-tests \
        --squash --allow-unrelated-histories --strategy-option theirs
    # 
    git commit -m "pull branch se1-play-repo/b2-streams-tests"
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Build branch 'b2-streams-sol' on-top of branch 'b2-streams'.
function build_streams_sol() {
    [ -z "$pwd" ] && echo "no password: export pwd=\"\"" && return 1

    # solution branch 'b2-streams-sol' created off branch 'b2-streams'
    [ "$(git show-ref refs/heads/b2-streams-sol)" ] && \
        git switch b2-streams-sol || git switch -c b2-streams-sol

    # extract content of 'pack-b2-streams-sol' into working tree
    curl https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/build/pack-b2-streams-sol.tgz.uue | \
        uudecode -o - | ccrypt -dfK "$pwd" | \
        tar xfvz -

    # apply and remove extracted .patch files
    for patch in $(find src -name '*.patch'); do
        local patched_file="${patch/.patch/}"
        if [ -f "$patched_file" ]; then
            patch -p1 < "$patch"
            rm "$patch"
        # else
        #     # collect 'NumbersImpl_[0-9]*' patches in 'tmp/NumbersImpl_patches.tar'
        #     # used later in build_numbers_completed()
        #     [ ! -d tmp ] && mkdir tmp
        #     [[ "$patch" =~ NumbersImpl_[0-9] ]] && \
        #         tar uvf tmp/NumbersImpl_patches.tar "$patch" && rm "$patch" || \
        #         echo "patched file $patched_file does not exist for patch: $patch"
        fi
    done

    # commit pack-b1-numbers update
    git add src
    git commit -m "b2-streams solution update, StreamsImpl.java"
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Prune .git repository from duplicate remote branches fetched with submodules.
function prune_submodule() {
    # echo prune_submodule $1
    case "$1" in
    env) local mod="$1"; local mdir=".env" ;;
    vscode-settings) local mod="$1"; local mdir=".vscode" ;;
    libs) local mod="$1"; local mdir="$1" ;;
    esac
    # 
    if [ "$mdir" ]; then
        [ -d "$mdir" ] && local cd_from=$(pwd) && builtin cd "$mdir"
        # 
        for branch in $(git branch -a | tr -d '*' | sed -e '/->\|main/d'); do
            if [[ ! "$branch" =~ "$mod" ]]; then
                # echo "--> $branch"
                [[ "$branch" =~ "remotes" ]] && \
                    branch=$(sed -e 's!remotes/!!' <<< "$branch") && \
                    git branch -dr "$branch" ||
                    git branch -D "$branch"
            fi
        done
        # prune submodule
        git reflog expire --expire=now --all
        git gc --prune=now --aggressive
        [ "$cd_from" ] && builtin cd "$cd_from"
    fi
}
