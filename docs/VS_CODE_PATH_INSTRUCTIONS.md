# Open Project into VS Code via `code .` Command

### Install VS Code
- **Install Visual Studio Code:** If you haven't already, download and install it from [Visual Studio Code: Download](https://code.visualstudio.com/download)
    - Choose the correct download for your OS.

## For Windows
1. **Add to PATH:** During installation, there's an option to "Add to PATH". Make sure this is checked. 
If you've already installed it without checking this option, you can add it manually by searching for 
'Edit the system environment variables' -> Environment Variables -> Edit PATH, 
and then adding the path to the VS Code bin folder.
   - From here you should be able to `cd` into any project and open that project into VS Code by typing `code .` from your BASH/terminal.

## For macOS
1. **Add Command to PATH:**
   - Open Visual Studio Code
   - Press `Cmd + Shift + P` to open the Command Palette.
   - Type `Shell Command: Install 'code' command in PATH` and select it
   - Restart your terminal
   - From here you should be able to `cd` into any project and open that project into VS Code by typing `code .` from your terminal.

## For Linux
1. **Symlink to PATH:** Usually, if installed via a package manager, it should automatically add `code` to your PATH. 
If not, you can create a symbolic link to the `code` executable in a directory that is in your PATH, like `/usr/local/bin`.

## Open Project into VS Code
From here you should be able to use the `code .` command in your terminal to open Visual Studio Code in your projects directory.
