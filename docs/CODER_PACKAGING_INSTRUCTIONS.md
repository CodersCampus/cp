# coder packaging Instructions

## Table of Contents

> Anywhere you see **SpringWise** in the _documentation_ or _video_, think "**cp**"

1. [Starting cp](#1-starting-cp)

   - [Instructional Video: Starting cp](#-starting-cp-instructional-video)
  
2. [Setting Up cp](#2-setting-up-cp)


---

## Before Getting Set-Up
1. Ensure you have **Java JDK 17** in your IDE. If you don't, the **cp** app will not run. 
2. Eclipse IDE users need **Eclipse 2021-09 (4.21)** or later. If you don't, please install a newer version.


## 1. Starting cp

### GitHub
1. Be given access to contribute too https://github.com/CodersCampus/cp, private repository via (Pete).
2. Click on green `<> Clone` button.
3. Click `Open with GitHub Desktop`.
4. Browser will display pop up window `Open GitHub Desktop.app?`.
5. Click `Open GitHub Desktop.app` button.

### GitHub Dektop
6. In GitHub Desktop, **Clone a Repository** window opens to the **URL** tab.
7. Keep the _**Repository URL or GitHub username and repository**_ field as is.
8. You can keep the _**Local Path**_ as is or change it by pressing the `Choose` button and selecting a different location in Finder/Explorer to store the cp repository.
9. Next, click blue `Clone` button.
10. **Current Repository** should be `cp`, **Current Branch** should be `dev`, and you'll have the option to `Fetch origin`.

<img style="border-radius: 10px" width="700" alt="GitHub Desktop default" src="../src/main/resources/static/images/GitHub_Desktop_default.jpg">


  
### Opening cp
11. Now open **cp** repository 
12. If you are given the option in GitHub Desktop to `Open in Eclipse IDE...`, then click that button.

<img style="border-radius: 10px" width="700" alt="Open with Eclipse IDE" src="../src/main/resources/static/images/Open_with_Eclipse_IDE.jpg">

13. If you do not see that option, then open **Eclipse IDE** app, click on `File` > `Open Projects from File System...` > `Directory` > locate **cp** root folder, and click `Open`.

<img style="border-radius: 10px" width="600" alt="cp root folder" src="../src/main/resources/static/images/SpringWise_root_folder.jpg">


### Eclipse IDE
14. Eclipse will open and give you the prompt window "**Import Projects from File System or Archive**".
15. In the "**Import Source**" field, that should be left alone, as that's where you choose to store the **cp** repository, earlier in GitHub Desktop.
16. Click bottom right button `Finish`.
17. You may see "**Import as Maven project**", if you do, select **Maven**, as this is how you need to import this project.
18. **cp** should populate within the **Project Explorer** side panel of **Eclipse IDE**.

### Run cp
19. To run **cp** application you will traverse through the **cp** folder structure clicking the dropdown arrows as you go.
  - That path is, cp/src/main/java/com.coderscampus/cpApplication.java. 
  - Open CPApplication.java
  - Press the green `Run` button to run application or right click on main method of CPApplication.java, and select `Run As` > `Java Application`.
20. You should see the Spring Boot design in the console with a bunch of other content.

<img style="border-radius: 10px" width="700" alt="Running cp" src="../src/main/resources/static/images/Running_SpringWise.jpg">

21. Now navigate to browser of choice, we use Chrome, and type into the address bar `localhost:8080`, this is the local address the **cp** application is being served too.
22. **cp** application should be live on your browser.
23. If prompted to **Login**, then login to the application using a Gmail account.

### 📹 [Starting cp Instructional Video](https://youtu.be/N8m2b1AFvDo)

_- If you have any questions please follow up the Team at Coder Campus for Assistance_

---

## 2. Setting up cp

To set up **cp** on your computer, follow these guidelines:

- Use GitHub Desktop and your preferred IDE, or
- Use Terminal/Git BASH with your IDE.

> Choose the method you're most comfortable with to get **cp** operational.

### Step 1: Get the cp `dev` branch

---

#### GitHub Desktop Instructions

1. Open **GitHub Desktop**
2. Ensure the **Current Repository** is `cp`, if not click the drop down and select the `cp` repository.
3. Ensure **Current Branch** is the `dev` branch.
4. Click `Fetch origin`.
5. If you see `Pull`, after pressing `Fetch origin`, then click `Pull`.
6. Open IDE of choice
   - If using Eclipse IDE, right click root `cp` folder and click **Refresh** if it's not already on `dev` branch.

<img style="border-radius: 10px" width="900" alt="GitHub Desktop screenshot instructions" src="../src/main/resources/static/images/GitHub_Desktop.jpg">

#### Terminal/Git BASH Instructions

- cd into **cp** project folder and type the following git commands into your Terminal/Git BASH
```git
git status
```
```git
git fetch 
```
```
git pull
```
- Open IDE of choice

<img style="border-radius: 10px" width="700" alt="GitHub Desktop screenshot instructions" src="../src/main/resources/static/images/Terminal.jpg">


### Step 2: Open cp app and run it locally

---

#### Eclipse IDE Instructions

1. Navigate or open to **cp** application, inside of **Eclipse IDE**.
2. Right click on root folder of **cp** and click **Refresh**.
3. `[cp dev]` should appear to the right of your cp project.
4. Navigate to [localhost:8080](http://localhost:8080/) in your browser of choice, we suggest Google Chrome, and ensure it's running successfully.

<img style="border-radius: 10px" width="350" alt="GitHub Desktop screenshot instructions" src="../src/main/resources/static/images/Eclipse.jpg">

#### Eclipse Troubleshooting Steps
**Solution 1**
1. On the `pom.xml` file, `right click > Run As > Maven Clean`
2. `Run As > Maven Install`

_If the Installation fails:_
- **On Windows:** delete the h2 local database C:\Users\%user%\h2dataspringwise.mv
    - Repeat Solution 1, Steps 1 and 2.

#### [Open Project into VS Code via `code .` Command](VS_CODE_PATH_INSTRUCTIONS.md)


#### IntelliJ IDEA Instructions

1. Navigate or open the **cp** application, inside of **IntelliJ IDEA**.
2. Ensure that you see the **dev** branch selected.
3. Navigate to [localhost:8080](http://localhost:8080/) in your browser of choice, we suggest Google Chrome, and ensure it's running successfully.

<img style="border-radius: 10px" width="400" alt="GitHub Desktop screenshot instructions" src="../src/main/resources/static/images/IntelliJ.jpg">


### Issues
Encounter any setup issues with cp? Direct message Dave Naugler to resolve them. We'll set some time aside to get the issue resolved outside of regularly scheduled meetings.











