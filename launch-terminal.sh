# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# initialize new terminal (bash, zsh/Mac), script is called in settings.json
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# 
# probe java is installed
if [[ $(type java &>/dev/null; echo $?) == 0 ]]; then
    # 
    # source $HOME/.bashrc or $HOME/.zshrc (Mac)
    [[ "$SHELL" =~ ^.*zsh$ ]] && \
        source ${HOME}/.zshrc || source ${HOME}/.bashrc
    # 
    # source project when new terminal is opened
    [[ -f .env/env.sh ]] && source .env/env.sh
fi