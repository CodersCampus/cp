# SpringWise Instructions

## Table of Contents
1️⃣ [Starting SpringWise](#starting-springwise-)
   ✅ [Instructional Video Starting SpringWise](#starting-springwise-instructional-video)
2️⃣ [Setting Up SpringWise](#setting-up-springwise)

---

## Starting SpringWise 

### GitHub
1. Be given access to contribute too https://github.com/CodersCampus/SpringWise, private repository via (Pete)
2. Click on green `<> Clone` button
3. Click `Open with GitHub Desktop`
4. Browser will display pop up window `Open GitHub Desktop.app?`
5. Click `Open GitHub Desktop.app` button

### GitHub Dektop
6. GitHub Desktop **Clone a Repository** window opens to the **URL** tab
7. Keep the **Repository URL or GitHub username and repository** field as is
8. You can keep the **Local Path** as is or change it by pressing `Choose` button and selecting a different location to store the SpringWise repository
9. Click blue `Clone` button 
10. Current Repository should be `SpringWise`, Current Branch should be `dev`, and you'll have the option to `Fetch origin`.

<img style="border-radius: 10px" width="700" alt="GitHub Desktop default" src="../src/main/resources/static/images/GitHub_Desktop_default.jpg">


  
### Opening SpringWise
11. Now open SpringWise repository 
12. If you are given the option in GitHub Desktop to `Open in Eclipse IDE...`, then click that button.

<img style="border-radius: 10px" width="700" alt="Open with Eclipse IDE" src="../src/main/resources/static/images/Open_with_Eclipse_IDE.jpg">

13. If not, open Eclipse IDE app, click on `File` > `Open Projects from File System...` > `Directory` > locate SpringWise root folder, and click `Open`

<img style="border-radius: 10px" width="600" alt="SpringWise root folder" src="../src/main/resources/static/images/SpringWise_root_folder.jpg">


### Eclipse IDE
14. Eclipse will open and give you the prompt window "Import Projects from File System or Archive"
15. In the "Import Source" field, that should be left alone, as that's where you choose to store the SpringWise repository earlier.
16. Click bottom right button `Finish`
17. You may see "**Import as Maven project**", if you do, select **Maven**, as this is how you need to import this project.
18. SpringWise should populate within the **Project Explorer** side panel of Eclipse IDE

### Run SpringWise
19. To run SpringWise application you will traverse through the SpringWise folder structure clicking the dropdown arrows as you go.
That path is, SpringWise/src/main/java/com.coderscampus/SpringWiseApplication.java. Open SpringWiseApplication.java, and press the green `Run` button to run application 
or right click on main method and select `Run As` > `Java Application`
20. You should see the Spring Boot design print into the console with a bunch of other console context.

<img style="border-radius: 10px" width="700" alt="Running SpringWise" src="../src/main/resources/static/images/Running_SpringWise.jpg">

21. Now navigate to browser of choice, we use Chrome, and type into the address bar `localhost:8080`, this is the local address the application is being served too.
22. SpringWise application should be live on your browser.


### [Starting SpringWise Instructional Video](https://youtu.be/N8m2b1AFvDo)

_- If you have any questions please follow up the Team at Coder Campus for Assistance_

---

## Setting up SpringWise

To set up SpringWise on your computer, follow these guidelines:

- Use GitHub Desktop and your preferred IDE, or
- Use Terminal/Git BASH with your IDE.

_Choose the method you're most comfortable with to get SpringWise operational._

### Step 1: Get the SpringWise `dev` branch

---

#### GitHub Desktop Instructions

1. Open **GitHub Desktop**
2. Ensure the **Current Repository** is `SpringWise`, if not click the drop down and select `SpringWise` repository
3. Ensure **Current Branch** is the `dev` branch
4. Click `Fetch origin`
5. If you see `Pull`, after pressing `Fetch origin`, then click `Pull`.
6. Open IDE of choice
   - If using Eclipse IDE, right click root `SpringWise` folder and click **Refresh** if it's not already on `dev` branch

<img style="border-radius: 10px" width="900" alt="GitHub Desktop screenshot instructions" src="../src/main/resources/static/images/GitHub_Desktop.jpg">

#### Terminal/Git BASH Instructions

- cd into **SpringWise** project folder and type the following git commands into your Terminal/Git BASH
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


### Step 2: Open SpringWise app and run it locally

---

#### Eclipse IDE Instructions

1. Navigate or open to SpringWise application, inside of **Eclipse IDE**
2. Right click on root folder of SpringWise and click **Refresh**
3. `[SpringWise dev]` should appear to the right of your SpringWise project
4. Navigate to [localhost:8080](http://localhost:8080/) in your browser of choice, we suggest Google Chrome, and ensure it's running successfully

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

1. Navigate or open the SpringWise application, inside of **IntelliJ IDEA**
2. Ensure that you see the **dev** branch selected
3. Navigate to [localhost:8080](http://localhost:8080/) in your browser of choice, we suggest Google Chrome, and ensure it's running successfully

<img style="border-radius: 10px" width="400" alt="GitHub Desktop screenshot instructions" src="../src/main/resources/static/images/IntelliJ.jpg">


### Issues
Encounter any setup issues with SpringWise? Direct message Dave Naugler to resolve them. We'll set some time aside to get the issue resolved outside of regularly scheduled meetings.











