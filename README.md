# Project: *se1-play*

The next commit adds a [.env.sh](.env.sh) file to setup or
*"source"* the Java development entvironment.

This includes:

- environment variables, e.g. `CLASSPATH`, `JDK_JAVAC_OPTIONS`, etc.,

- project files, e.g. `.classpath`, `.project`, etc.

- libraries or links to libraries needed by the project,
    e.g. `libs -> ./branches/libs`

- functions (e.g. `mk`, `show`) that compile: `mk compile` and
    run: `mk run` code or to run tests: `mk compile-tests run-tests`.

The term: *"sourcing the project"* or just *"sourcing"* refers to the
`source`-command that executes the `.env.sh` file.

```sh
<se1-play>              # project directory
 |
 +--.gitignore                  # files for git to ignore
 +--.env.sh     <--new          # .env file to source the entvironment
 +-- README.md                  # this markup file
 |
 +-<.vscode>                    # VSCode project files
    +--settings.json            # project-specific VSCode configuration
    +--launch.json              # launch and debug configurations
    +--launch_terminal.sh       # script to launch terminals in VSCode
```
