# New HTML Page Template

## Every Page Uses This Identical HTML (except home page)

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
<!--                      UPDATE PAGE SPECIFIC NAV LINK HERE -->
                        <li class="nav-item"><a class="nav-link active" aria-current="page"
                                                href="/whereverFIX ME/">FIXME</a>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="ga-content-main">
            <main>
                
<!--                ADD PAGE SPECIFIC CODE HERE -->

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


