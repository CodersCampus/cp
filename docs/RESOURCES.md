# Resources

## Job Search
[otta](https://otta.com/) - Only relevant roles. At the most exciting startups. Discover your top recommendations now.

[ZipRecruiter](https://www.ziprecruiter.com/) - Rated #1 job site in the U.S

---



## Slack Channels to connect with
[tech-community-slacks](https://github.com/thisdot/tech-community-slacks#list-of-slack-channels-for-local-communities-by-region) - Slack channels broken up into regions for developers to connect with other people in tech to build their network.

---

[//]: # (## Trevors Books)

[//]: # ( - Jab, Jab, Jab, Right Hook by Gary Vaynerchuk)

[//]: # ()
[//]: # (---)


## Video Production Tools
[Loom](https://www.loom.com/) - Easily record and share AI-powered video messages with your teammates and customers to supercharge productivity

[Descript](https://www.descript.com/) - Descript is the simple, powerful, and fun way to edit.

---
## LinkedIn

[How to Write a Killer LinkedIn Recommendation in Under 2 Min](https://www.linkedin.com/pulse/how-write-killer-linkedin-recommendation-under-2-min-ryan-delon/) - This article will help you save time and make the process of writing a professional recommendation for your Coders Campus peers much smoother.

---

# Thymeleaf Documentation Resources

- [Thymeleaf Page Layouts](https://www.thymeleaf.org/doc/articles/layouts.html)
- [Thymeleaf Fragments](https://www.baeldung.com/spring-thymeleaf-fragments)
- [Thymeleaf API docs](https://www.thymeleaf.org/apidocs/thymeleaf/3.0.0.RELEASE/help-doc.html) (Backup documentation. Would rarely use.)

## Current Thymeleaf example in SpringWise

footer.html
```html
<footer class="mt-5">
  <div class="container text-center">
    <p class="navbar-text col-md-12 col-sm-12 col-xs-12 text-muted credit">
      Copyright &copy; 2023 Coders Campus!
    </p>
  </div>
</footer>
```
Located at...

```markdown
SpringWise > src > main > resources > templates > fragments > footer.html
```

Added to each HTML page where the footer would regularly go
```html
<div th:replace="~{fragments/footer :: footer}"></div>
```
---