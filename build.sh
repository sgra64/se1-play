# trap: disable zsh from outputting ANSI escape characters in sub-processes such
# as in: wc $(find tmp -name '*.py') or colors="${colors:3}"
# setopt no_match: disable 'no matches found:' message in zsh
is_zsh=$(type setopt 2>/dev/null)
[ "$is_zsh" ] && trap "" DEBUG && setopt no_nomatch

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
# @param $1 stage to build: 'se1_play', 'numbers', 'streams'
# @param $2 folder to build project in
# 
function build() {
    [ -z "$1" ] && local build_dir="se1-play" || local build_dir="$1"

    build_se1_play "$build_dir"
    # 
    # within 'se1-play':
    # - build_numbers
    # - build_numbers_sol

    [ "$is_zsh" ] && trap "echo -ne '\e[m'" DEBUG    # zsh: resume formatting
}

# build 'b1-numbers' on-top of 'se1-play'
function build_numbers() {
    # tag last commit of 'se1-play' and create new branch 'b1-numbers'
    [ -z "$(git tag -l base)" ] && git tag base
    [ "$(git show-ref refs/heads/b1-numbers)" ] && git switch b1-numbers || \
        git switch -c b1-numbers

    # set remote URL to repository to fetch branches
    git remote add se1-play-repo git@github.com:sgra64/se1-play.git

    # fetch branch 'b1-numbers' from remote repository 'se1-play-repo'
    git fetch se1-play-repo b1-numbers

    # merge content of branch 'b1-numbers' into the 'main' branch of the project
    # '-m' specifies the message for the resulting merge commit
    # '--allow-unrelated-histories' allows merging from a repository with no shared history
    # '--strategy-option theirs' resolves merge conflicts favoring incoming changes
    # 
    git merge se1-play-repo/b1-numbers --squash \
        --allow-unrelated-histories --strategy-option theirs
    # 
    git commit -m "merge branch se1-play-repo/b1-numbers"

    git fetch se1-play-repo b1-numbers-tests
    git merge se1-play-repo/b1-numbers-tests --squash \
        --allow-unrelated-histories --strategy-option theirs
    # 
    git commit -m "merge branch se1-play-repo/b1-numbers-tests"
}

function build_numbers_sol() {
    [ -z "$pwd" ] && echo "no password: export pwd=\"\"" && return 1

    curl https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/build/pack-b1-numbers.tgz.uue | \
        uudecode -o - | ccrypt -dfK "$pwd" | \
        tar xfvz -

    for patch in $(find src -name '*.patch'); do
        local patched_file="${patch/.patch/}"
        # apply patch if patched file exists and remove patch
        [ -f "$patched_file" ] && \
            patch -p1 < "$patch" && rm "$patch" ||
            echo "patched file $patched_file does not exist for patch: $patch"
    done

    # commit solution
    git add src
    git commit -m "b1-numbers solution applied"
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
        git add ".gitignore" && \
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

    [ ! -L env.sh ] && ln -s .env/env.sh env.sh
}

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

# sed -n '/@@ src.module-info.java @BEGIN/,/@@ src.module-info.java @END/p' < README.md \
#         | sed -e '1,/java/d' -e '/```/,$d'

#[ ! -d "src/application" ] && \
#    mkdir -p "src/application" && \
#    touch src/module-info.java && \
#    touch src/application/package-info.java && \
#    touch src/application/Application.java && \
#    [ -f ../se1-play-src.tar ] && tar xvf ../se1-play-src.tar
