# Code Formatting Guide
This document provides step-by-step instructions on how to use the .editorconfig file located in the root of the project folder in different Integrated Development Environments (IDEs).

<span style="color: red; font-weight: bold">Be sure to format your code before you commit to avoid unnecessary confusion during code reviews.</span>

---

## Table of Contents
- [Eclipse](#eclipse)
- [IntelliJ IDEA](#intellij-idea)
- [Visual Studio Code](#visual-studio-code)

---

### Eclipse
To use the `.editorconfig` file as your formatting option in Eclipse, you will need to install the `EditorConfig` plugin. Here’s how:

1. Open Eclipse.
2. Go to `Help` `->` `Eclipse Marketplace...`.
3. In the Eclipse Marketplace dialog, search for `EditorConfig`.
4. Click `Go`, and from the search results, find `EditorConfig` and click Install.
5. On the next dialog, tick the `"I accept the terms of the license agreement"` radio button, and then click `Finish`.
6. Next, the `Trust Authorities` dialog will open. Click the `Select All` button and then the `Trust Selected` button.
7. The `Trust Artifacts` dialog will open next. Again, click `Select All` followed by the `Trust Selected` button.
8. Finally, the `Software Updates` dialog should open. Select the `Restart Now` button. If it doesn't open, just 

Eclipse will now automatically use the `.editorconfig` file in the root of the project for code formatting.

---

### IntelliJ IDEA
IntelliJ IDEA supports `.editorconfig` files out of the box. Here’s how to enable it:

1. Open IntelliJ IDEA.
2. Open your project.
3. Go to `File` `->` `Settings` (On Mac, `IntelliJ IDEA` `->` `Preferences`).
4. In the Settings window, navigate to `Editor` `->` `Code Style`.
5. Check `Enable EditorConfig support`.
6. Click `OK`.

IntelliJ IDEA will now automatically use the `.editorconfig` file in the root of the project for code formatting.

---

### Visual Studio Code
Visual Studio Code supports `.editorconfig` files through the `EditorConfig for VS Code` extension. Here’s how to install and use it:

1. Open Visual Studio Code.
2. Go to the Extensions view (`View` `->` `Extensions`).
3. Search for `EditorConfig for VS Code` and install it.
4. Reload Visual Studio Code after the installation is complete.

Visual Studio Code will now automatically use the `.editorconfig` file in the root of the project for code formatting.
