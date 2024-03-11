# New UI Template

_03/08/2024 - Kevin Gallaccio_
***

> This document will reference all components to be used in HTML sections of the `cp` app.


## Table of content:

- [HTML page](#html-page-template)
    * [Standard page](#standard-page)
    * [Landing page](#landing-page)
    * [Sidebar page](#sidebar-page)


- [Components](#components)
    * [Tables](#tables)
    * [Forms](#forms)
    * [Form fields](#form-fields)
        * [input fields](#input-fields)
        * [text area](#text-area)
        * [checkboxes](#checkboxes)
        * [buttons](#buttons)


- [Colors](#colors)


- [Typography](#typography)
    * [Display](#display)
    * [Headline](#headline)
    * [Title](#title)
    * [Body](#body)

<br>

***
<br>

## HTML Page Template

### Standard page

> Every Page Uses This Identical HTML Template

```HTML
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>
<body class="ui-background">
<fbauth-element headless>
    <div>
        <header th:replace="~{fragments/header :: header(${activePage})}"></header>
        <div class="ga-content-main">
            <main>

                <!-- * MAIN CONTENT GOES HERE * -->

            </main>
        </div>
        <div th:replace="~{fragments/footer :: footer}"></div>
    </div>
</fbauth-element>
<div th:replace="~{fragments/authscript :: script}"></div>
</body>
</html>
```

### Landing page

> This is a template for a homepage, displaying a message e.g. "**Welcome to coder packaging**"

```HTML
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>
<body layout="layout-secured">
<fbauth-element>
    <div layout="layout-home">
        <header th:replace="~{fragments/header :: header(${activePage})}"></header>
        <div class="ga-content-main">
            <body class="ui-background">
            <fbauth-element headless>
                <div>
                    <header th:replace="~{fragments/header :: header(${activePage})}">
                    </header>
                    <div class="welcome">
                        <div class="welcome-text display-small-bold-italic">
                            <!-- REPLACE BELOW TEXT -->
                            Welcome to coder packaging
                        </div>
                    </div>
                    <div th:replace="~{fragments/footer :: footer}">
                    </div>
                </div>
                <div th:replace="~{fragments/footer :: footer}"></div>
        </div>
</fbauth-element>
</body>
</html>
```

### Sidebar page

> For pages that include a sidebar, please use this template:

```HTML
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>
<body class="ui-background">
<fbauth-element headless>
    <div>
        <header th:replace="~{fragments/header :: header(${activePage})}"></header>
        <div layout="layout-sidebar">
            <div class="sidebar-grid-area">
                <section class="ui-panel-title title-small">

                    <!-- * SIDEBAR TITLE GOES HERE * -->

                </section>
                <nav class="ui-panel">
                    <ul class="ui-navbar-nav">

                        <!-- * SIDEBAR CONTENT GOES HERE * -->

                    </ul>
                </nav>
            </div>
            <div class="ga-content-main">
                <main>

                    <!-- * MAIN CONTENT GOES HERE * -->

                </main>
            </div>
            <div th:replace="~{fragments/footer :: footer}"></div>
        </div>
</fbauth-element>

<div th:replace="~{fragments/authscript :: script}"></div>
</body>
</html>
```

<br>

***

## Components

<br>

### Tables:

> This is the template for using tables (see `*/read` for reference)

```HTML 

<div class="ui-table" role="region" tabindex="0">
    <table>
        <thead>
        <tr>
            <th scope="col"><!-- Column 1 Title --></th>
            <th scope="col"><!-- Column 2 Title --></th>
            <th scope="col"><!-- Column 3 Title --></th>
            <th scope="col"><!-- Column 4 Title --></th>
            <th scope="col"><!-- Column 5 Title --></th>
            <th scope="col"><!-- Column 6 Title --></th>
            <th scope="col"><!-- Column 7 Title --></th>
            <th scope="col"><!-- Column 8 Title --></th>
            <th scope="col"><!-- Column 9 Title --></th>
            <th scope="col"><!-- Column 10 Title --></th>
            <th scope="col"><!-- Column 11 Title --></th>
        </tr>
        </thead>
        <tbody>
        <tr <!--th:each=" ENTER THYMELEAF REFERENCE e.g. student: ${students}"-->>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>
        <td><span <!-- th:text=" ENTER THYMELEAF REFERENCE e.g. ${student.name}"-->></span></td>

        <td> <!-- HAMBURGER UPDATE BUTTON BELOW (to be updated to Material UI) -->
            <a th:href="@{/student/update/{studentId}(studentId=${student.id})}"> 
                    <span>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-view-list" viewBox="0 0 16 16"> 
                            <path d="M3 4.5h10a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-3a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1H3zM1 2a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13A.5.5 0 0 1 1 2zm0 12a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13A.5.5 0 0 1 1 14z"/>
                        </svg>
                    </span>
            </a>
        </td>
        </tr>
        </tbody>
    </table>
</div>
```

<br>

### Forms:

> All forms should be built using this template

```HTML
<!-- POPULATE action="" ATTRIBUTE BELOW e.g. student/create -->
<form method="post" class="ui-form" action="     ">

    <!-- TO FETCH STUDENT UID: -->
    <input name="uid" type="hidden" id="uid"/>

    <!-- FORM FIELDS GOES HERE -->

    <!-- SUBMIT BUTTON GOES HERE -->

</form>
``` 

<br>

### Form fields

> All fields that can be included in forms

<br>

#### Input fields:

<img src="images/ui-input-fields.png">

```HTML

<div class="ui-input-field">
    <label class="ui-label">

        <!-- LABEL GOES HERE (e.g. Next Assignment)-->

    </label>
    <div class="ui-text-input">

        <!--
            - type="" attribute can be:
            text, number, email, color, date, datetime-local, file, password etc...
            depending on the value that is expected

            - Replace id="" attribute to the VALUE that should be pre-populated
            if the field should not be pre-populate, remove id="" attribute.
            
            - you can add a custom placeholder="" attribute if no value is
            pre-populated

            - Replace th:field="" by the value that should be submitted
         -->

        <input class="ui-value" type="text" id="display-name" th:field="${student.name}"/>
    </div>
</div>
```

<br>

#### Text area:

<img src="images/ui-text-area.png">

```HTML

<div class="ui-input-field">
    <label class="ui-label">

        <!-- LABEL GOES HERE (e.g. Text Area Test:-->

    </label>
    <div class="ui-text-input">

        <!--
            - cols="" and rows="" can be adjusted
            
            - You can customize the placeholder text
            
            - Replace th:field="" by the value that should be submitted 
         -->

        <textarea class="ui-value" cols="20" rows="5"
                  name="comments" placeholder="write something"
                  th:field="${checkin.comments}"></textarea>
    </div>
</div>
```

<br>

#### Checkboxes:

<img src="images/ui-checkboxes.png">

```HTML
<label class="ui-checkbox">
    <!-- Replace th:field="" by the value that should be submitted-->
    <input type="checkbox" th:field="${checkin.blockers}"/>
    <span>
        
    <!-- Enter text here (e.g. I Have a Blocker-->
        
    </span>
</label>
```

#### Buttons:

- Primary buttons:
  <br><br>
  <img src="images/primary-light-button.png"> (light mode)
  <br><img src="images/primary-button.png"> (dark mode)

> This is the go-to button that needs to be used primarily

```HTML

<button class="ui-nav-item ui-button" type="submit">
    <span>Your Text Here</span>
</button>
```

- Secondary buttons:
  <br><br>
  <img src="images/secondary-light-button.png">(light mode)
  <br><img src="images/secondary-button.png">(dark mode)

> Secondary buttons need to be used when there are two very different choices

```HTML

<button class="ui-nav-item ui-button-secondary" type="">
    <span>Your Text Here</span>
</button>
```

- Danger buttons:
  <br><br>
  <img src="images/danger-button.png">

> "Danger" buttons need to be used to **Stop**, **Delete** or any dangerous prompts
> these have the same color on light and dark modes.

```HTML

<button class="ui-nav-item ui-button-danger" type="submit">
    <span>Your Text Here</span>
</button>
```

<br>

***

## Colors
>CSS variables need to be used inside **stylesheet** (e.g. `background-color: var(--primary);`) or can be added 
> in-line on **HTML pages** (e.g. `<div style="color: var(--secondary);"`)
> 

| Color Name          | CSS variable                 | Light                                                 | Dark                                                 | Usage                                                            |
|---------------------|------------------------------|-------------------------------------------------------|------------------------------------------------------|------------------------------------------------------------------|
| Primary             | `var(--primary)`             | <img src="images/colors/light-primary.png">           | <img src="images/colors/dark-primary.png">           | Used for main text                                               |
| Secondary           | `var(--secondary)`           | <img src="images/colors/light-secondary.png">         | <img src="images/colors/dark-secondary.png">         | Used for highlighted text                                        |
| Accent              | `var(--accent)`              | <img src="images/colors/light-accent.png">            | <img src="images/colors/dark-accent.png">            | Used for headers backgrounds                                     |
| Background          | `var(--background)`          | <img src="images/colors/light-background.png">        | <img src="images/colors/dark-background.png">        | Used for main background                                         |
| Background Accent   | `var(--background-accent)`   | <img src="images/colors/light-background-accent.png"> | <img src="images/colors/dark-background-accent.png"> | Used for elevated sections on background                         |
| Gradient            | `var(--gradient)`            | <img src="images/colors/light-gradient.png">          | <img src="images/colors/dark-gradient.png">          | Used for headlines (in italic)                                   |
| Live Session        | `var(--live-session)`        | <img src="images/colors/live-session.png">            | <img src="images/colors/live-session.png">           | Used indicate live session is on. Also used for "danger" buttons |
| Live Session Accent | `var(--live-session-accent)` | <img src="images/colors/live-session-accent.png">     | <img src="images/colors/live-session-accent.png">    | Same as above but as the accent or background color              |
| Table Even          | `var(--table-even)`          | <img src="images/colors/light-table-even.png">        | <img src="images/colors/dark-table-even.png">        | Used for even rows in tables. (same as background accent)        |
| Table Odd           | `var(--table-odd)`           | <img src="images/colors/light-table-odd.png">         | <img src="images/colors/dark-table-odd.png">         | Used for odd rows in tables.                                     |

<br>

***

## Typography
>All fonts follow [**Material 3** guidelines](https://m3.material.io/styles/typography/type-scale-tokens) for font-size, line-height and letter-spacing
>
> When designing HTML, please add the corresponding class to a tag for example:   
>`<h1 class="headline-medium-bold-italic"></h1>`
>
> You can add as many classes as you wish on in-line HTML:   
> `<h1 class="ui-title display-small-bold"></h1>`
> 
>    Fonts are built responsive for different types of screen as follows:   
>    **Desktop**: 1024px and up   
>    **Tablet**: between 480px and 1024px   
>    **Smartphone**: less than 480px   
>    _feel free to adjust these values in the media queries_
### Display
>Display styles are reserved for **short, important text or numerals.**   
They work best on large screens.

<img src="images/text/display-small.png">

Code: `class="display-small"`

<img src="images/text/display-small-bold.png">

Code: `class="display-small-bold"`

<img src="images/text/display-small-bold-italic.png">

Code: `class="display-small-bold-italic"`

<br>
<br>

### Headline
>Headlines are best-suited for **short, high-emphasis text on smaller screens.**   
These styles can be good for marking primary passages of text or important regions of content.

<img src="images/text/headline-large.png">

Code: `class="headline-large"`

<img src="images/text/headline-large-light.png">

Code: `class="headline-large-light"`

<img src="images/text/headline-medium.png">

Code: `class="display-headline-medium"`

<img src="images/text/headline-medium-bold-italic.png">

Code: `class="headline-medium-bold-italic"`

<img src="images/text/headline-medium-thin.png">

Code: `class="headline-medium-thin"`

<img src="images/text/headline-small.png">

Code: `class="headline-small"`

<img src="images/text/headline-small-bold.png">

Code: `class="headline-small-bold"`

<img src="images/text/headline-xs.png">

Code: `class="headline-xs"`

<br>
<br>

### Title
>Titles are smaller than headline styles, and should be used for **medium-emphasis text** that remains relatively short.    
> For example, consider using title styles to divide secondary passages of text or secondary regions of content.

<img src="images/text/title-large.png">

Code: `class="title-large"`

<img src="images/text/title-large-thin.png">

Code: `class="title-large-thin"`


<br>
<br>

### Body
>Body styles are used for longer passages of text in the app.

<img src="images/text/body-large.png">

Code: `class="body-large"`

<img src="images/text/body-large-bold.png">

Code: `class="body-large-bold"`

<img src="images/text/body-medium.png">

Code: `class="body-medium"`

<img src="images/text/body-medium-bold.png">

Code: `class="body-medium-bold"`

<img src="images/text/body-small.png">

Code: `class="body-small"`


