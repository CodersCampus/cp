# New HTML Page Template

> Every Page Uses This Identical HTML Template (except home page)

For Thymeleaf assistance, please refer to our [Thymeleaf Resources](../docs/RESOURCES.md#thymeleaf)

## HTML Page Template

```HTML
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>
<body layout="layout-secured">
<fbauth-element>
    <div layout="layout-sidebar">
        <header th:replace="~{fragments/header :: header(${activePage})}"></header>
        <div class="sidebar-grid-area">
            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <!-- UPDATE PAGE SPECIFIC NAV LINK HERE -->
                    </ul>
                </div>
            </nav>
        </div>
        <div class="ga-content-main">
            <main>
                <!-- ADD PAGE SPECIFIC CODE HERE -->
            </main>
        </div>
        <div th:replace="~{fragments/footer :: footer}"></div>
    </div>
</fbauth-element>
<script>
    const fbauth = document.querySelector('fbauth-element');
    const uid = document.getElementById('uid');
    const displayName = document.getElementById('display-name');
    fbauth.addEventListener('user-changed', (event) => {
        const {newValue} = event.detail;
        console.log("SETTING UID TO " + newValue.uid + " AND DISPLAY NAME TO " + newValue.displayName);
        if (newValue) {
            displayName.value = newValue.displayName;
            uid.value = newValue.uid;
        } else {
            displayName.value = '';
            uid.value = '';
        }
    });
</script>
</body>
</html>
```

## Controller Template

Within `blank_Controller.java` add the following lines of code, on the line above the return path, for each of the methods below.
> Make sure to update the _pageTitle_ value for each corresponding Controller. 

`@GetMapping("/")`

**home** method
```Java
        model.addAttribute("pageTitle", "Checkin Read");
		model.put("isCheckin", true);
```

`@GetMapping("/create")`

**getCreate** method
```Java
        model.addAttribute("pageTitle", "Checkin Create");
		model.put("isCheckin", true);
```

`@GetMapping("/update/{id}")`

**fetch** method
```Java
        model.addAttribute("pageTitle", "Checkin Update");
		model.put("isCheckin", true);
```



