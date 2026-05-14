# Thymeleaf Screen Audit

## Scope

This audit inventories the current Thymeleaf screens, forms, tables, filters, actions, redirects, and controller methods in the app.

## Summary

- Total page templates: 22
- Feature areas with CRUD-style screens: `student`, `github`, `linkedin`, `resume`, `finalproject`, `networkingperson`, `networkingresource`
- Shared layout fragments: `fragments/*`
- Active page-level filters/search/sort controls: none found

## Key Findings

1. `Student` has a visible `Create New` nav link, but there is no `GET /student/create` controller method and no `student/create.html` template.
2. `student/update.html` calls `assignUid()` on the submit button, but that function does not exist in the codebase.
3. `GitHub`, `LinkedIn`, and `Resume` hide their `Create New` button only when the global list is non-empty, not when the current user already has a record.
4. Screen scoping is inconsistent:
   - `GitHub`, `LinkedIn`, `Resume`, and `Finalproject` list all records.
   - `Networkingperson`, `Networkingresource`, and the main student read screen are scoped to the current user.

## Screen Inventory

| Route | Template | Entity/Table | UI Surface | Actions | Redirects / Notes | Controller Methods |
| --- | --- | --- | --- | --- | --- | --- |
| `/` | `dashboard.html` | `Student` placeholder model; OAuth may create/update `Student` | Welcome banner only | none on page | `POST /send-oauth` stores auth data in session and may create/update `Student` | `SpringProjectController.getDashboard()`, `SpringProjectController.getOauth()` |
| `/student` | `student/read.html` | `StudentDTO` backed by `Student` | Single-row table with `name`, `assignmentNum`, placeholder columns, edit icon | nav to missing create page; nav to read-all; edit | User-scoped by session `uid` | `StudentController.home()` |
| `/student/readall` | `student/readall.html` | `StudentDTO` backed by `Student` | Multi-row table of all students | nav to missing create page; nav to individual read | Global list of students | `StudentController.readAll()` |
| `/student/update/{id}` | `student/update.html` | `StudentDTO` / `Student` | Form with hidden `uid`, hidden `id`, `name` readonly, `assignmentNum`, `ide`, `willingToMentor`, disabled `dateCreated`; delete form | update, delete, back | Update redirects to `/student`; delete redirects to `/student` | `StudentController.fetch()`, `StudentController.update()`, `StudentController.delete()` |
| `/student/create` | none | Intended `StudentDTO` / `Student` | no page exists | only POST endpoints exist | broken nav target from student screens | `StudentController.create()`, `StudentController.createFromJson()` |
| `/github` | `github/read.html` | `GitHub` joined to `Student` via `student_id` | Table with booleans for completion fields, `externalLinks`, `url`, edit icon | conditional create link, edit | Global list, not user-scoped | `GitHubController.home()` |
| `/github/create` | `github/create.html` | `GitHub` | Form with hidden `uid`, 7 boolean toggles, `externalLinks`, `url`, error state | create, nav to read | Redirects to `/github` if current uid already has a record; invalid URL stays on page | `GitHubController.getCreate()`, `GitHubController.create()` |
| `/github/update/{id}` | `github/update.html` | `GitHub` | Same form fields as create plus delete | update, delete, nav to create | Unauthorized access redirects to `/github`; invalid URL stays on page | `GitHubController.fetch()`, `GitHubController.update()`, `GitHubController.delete()` |
| `/linkedin` | `linkedin/read.html` | `LinkedIn` joined to `Student` via `student_id` | Table with `firstName`, `lastName`, `url`, 12 boolean profile fields, edit icon | conditional create link, edit | Global list, not user-scoped | `LinkedInController.home()` |
| `/linkedin/create` | `linkedin/create.html` | `LinkedIn` | Form with hidden `uid`, `firstName`, `lastName`, `url`, 12 boolean toggles, error state | create, nav to read | Redirects to `/linkedin` if current uid already has a record; invalid URL stays on page | `LinkedInController.getCreate()`, `LinkedInController.create()` |
| `/linkedin/update/{id}` | `linkedin/update.html` | `LinkedIn` | Same form fields as create plus delete | update, delete, nav to create | Unauthorized access redirects to `/linkedin`; invalid URL stays on page | `LinkedInController.fetch()`, `LinkedInController.update()`, `LinkedInController.delete()` |
| `/resume` | `resume/read.html` | `Resume` joined to `Student` via `student_id` | Table with `phoneNumber`, `email`, `city`, `state`, `linkedIn`, `gitHub`, `summary`, `skills`, `workExperience`, `education`, `projects`, edit icon | conditional create link, edit | Global list, not user-scoped | `ResumeController.home()` |
| `/resume/create` | `resume/create.html` | `Resume` | Form with hidden `uid` and 11 text fields | create, nav to read | Redirects to `/resume` if current uid already has a record | `ResumeController.getCreate()`, `ResumeController.create()` |
| `/resume/update/{id}` | `resume/update.html` | `Resume` | Same form fields as create plus delete | update, delete, nav to create | Unauthorized access redirects to `/resume` | `ResumeController.fetch()`, `ResumeController.update()`, `ResumeController.delete()` |
| `/finalproject` | `finalproject/read.html` | `Finalproject` joined to `Student` via `student_id` | Table with `student.name`, `projectName`, `proposal`, `description`, `crud`, `tables`, `views`, edit icon | create link, edit | Global list; description tooltip on hover | `FinalprojectController.home()` |
| `/finalproject/create` | `finalproject/create.html` | `Finalproject` | Form with hidden `uid`, `projectName`, `proposal`, `description`, `crud`, `tables`, `views`, error state | create | Invalid URL stays on page; success redirects to `/finalproject` | `FinalprojectController.getCreate()`, `FinalprojectController.create()` |
| `/finalproject/update/{id}` | `finalproject/update.html` | `Finalproject` | Same form fields as create plus delete | update, delete, nav to create | Unauthorized access redirects to `/finalproject`; invalid URL stays on page | `FinalprojectController.fetch()`, `FinalprojectController.update()`, `FinalprojectController.delete()` |
| `/networkingperson` | `networkingperson/read.html` | `Networkingperson` joined to `Student` via `student_id` | Table with `name`, `linkedinUrl`, `techStack`, `firstContactDate`, `lastContactDate`, `otherNotesAboutPerson`, edit icon | create link, edit | User-scoped by session `uid` | `NetworkingpersonController.home()` |
| `/networkingperson/create` | `networkingperson/create.html` | `Networkingperson` | Form with hidden `uid`, `name`, `linkedinUrl`, `techStack`, `firstContactDate`, `lastContactDate`, `otherNotesAboutPerson` | create, nav to read | Success redirects to `/networkingperson` | `NetworkingpersonController.getCreate()`, `NetworkingpersonController.create()` |
| `/networkingperson/update/{id}` | `networkingperson/update.html` | `Networkingperson` | Same form fields as create plus delete | update, delete, nav to create | Unauthorized access redirects to `/networkingperson` | `NetworkingpersonController.fetch()`, `NetworkingpersonController.update()`, `NetworkingpersonController.delete()` |
| `/networkingresource` | `networkingresource/read.html` | `Networkingresource` joined to `Student` via `student_id` | Table with `resourceName`, `type`, `cost`, `geographicScope`, `notes`, edit icon | create link, edit | User-scoped by session `uid` | `NetworkingresourceController.home()` |
| `/networkingresource/create` | `networkingresource/create.html` | `Networkingresource` | Form with hidden `uid`, `resourceName`, `type`, `cost`, `geographicScope`, `notes` | create, nav to read | Success redirects to `/networkingresource` | `NetworkingresourceController.getCreate()`, `NetworkingresourceController.create()` |
| `/networkingresource/update/{id}` | `networkingresource/update.html` | `Networkingresource` | Same form fields as create plus delete | update, delete, nav to create | Unauthorized access redirects to `/networkingresource` | `NetworkingresourceController.fetch()`, `NetworkingresourceController.update()`, `NetworkingresourceController.delete()` |

## Page-Level Forms

### Student

- `student/update.html`
  - hidden: `uid`, `id`
  - fields: `name` (readonly), `assignmentNum`, `ide`, `willingToMentor`, `dateCreated` (disabled)
  - actions:
    - `POST /student/update`
    - `POST /student/delete`

### GitHub

- `github/create.html`
- `github/update.html`
  - hidden: `uid` on create; `id` on update
  - fields:
    - `handle`
    - `enhancedReadMe`
    - `renamedAssignments`
    - `pinnedRepos`
    - `externalLinks`
    - `image`
    - `headline`
    - `contactDetails`
    - `url`
  - actions:
    - `POST /github/create`
    - `POST /github/update`
    - `POST /github/delete`

### LinkedIn

- `linkedin/create.html`
- `linkedin/update.html`
  - hidden: `uid` on create; `id` on update
  - fields:
    - `firstName`
    - `lastName`
    - `url`
    - `banner`
    - `about`
    - `featuredPosts`
    - `activity`
    - `skills`
    - `email`
    - `biography`
    - `education`
    - `experience`
    - `location`
    - `image`
    - `title`
  - actions:
    - `POST /linkedin/create`
    - `POST /linkedin/update`
    - `POST /linkedin/delete`

### Resume

- `resume/create.html`
- `resume/update.html`
  - hidden: `uid` on create; `id` on update
  - fields:
    - `phoneNumber`
    - `email`
    - `city`
    - `state`
    - `linkedIn`
    - `gitHub`
    - `summary`
    - `skills`
    - `workExperience`
    - `education`
    - `projects`
  - actions:
    - `POST /resume/create`
    - `POST /resume/update`
    - `POST /resume/delete`

### Finalproject

- `finalproject/create.html`
- `finalproject/update.html`
  - hidden: `uid` on create; `id` on update
  - fields:
    - `projectName`
    - `proposal`
    - `description`
    - `crud`
    - `tables`
    - `views`
  - actions:
    - `POST /finalproject/create`
    - `POST /finalproject/update`
    - `POST /finalproject/delete`

### Networkingperson

- `networkingperson/create.html`
- `networkingperson/update.html`
  - hidden: `uid` on create; `id` on update
  - fields:
    - `name`
    - `linkedinUrl`
    - `techStack`
    - `firstContactDate`
    - `lastContactDate`
    - `otherNotesAboutPerson`
  - actions:
    - `POST /networkingperson/create`
    - `POST /networkingperson/update`
    - `POST /networkingperson/delete`

### Networkingresource

- `networkingresource/create.html`
- `networkingresource/update.html`
  - hidden: `uid` on create; `id` on update
  - fields:
    - `resourceName`
    - `type`
    - `cost`
    - `geographicScope`
    - `notes`
  - actions:
    - `POST /networkingresource/create`
    - `POST /networkingresource/update`
    - `POST /networkingresource/delete`

## Tables Rendered on Screens

- `student/read.html`
  - one-row student table
- `student/readall.html`
  - all-students table
- `github/read.html`
  - all GitHub records
- `linkedin/read.html`
  - all LinkedIn records
- `resume/read.html`
  - all Resume records
- `finalproject/read.html`
  - all Finalproject records
- `networkingperson/read.html`
  - current-user networking contacts
- `networkingresource/read.html`
  - current-user networking resources

## Filters

No active page-level filters, search fields, sort controls, or backend query parameters were found in the Thymeleaf screens/controllers audited here.

## Entity / Table Mapping

These screens are backed by the following JPA entities:

- `Student`
- `GitHub`
- `LinkedIn`
- `Resume`
- `Finalproject`
- `Networkingperson`
- `Networkingresource`

Notes:

- None of these entities declare `@Table`, so the physical table names are inferred by JPA from the entity names.
- All feature entities except `Student` include a `student_id` association to `Student`.
- `Student` screens use `StudentDTO` in the controller layer, but persist through the `Student` entity.

## Controller Surface Summary

- `SpringProjectController`
  - `GET /`
  - `POST /send-oauth`
- `StudentController`
  - `GET /student`
  - `GET /student/readall`
  - `POST /student/create`
  - `POST /student/create` consuming JSON
  - `GET /student/update/{id}`
  - `POST /student/update`
  - `POST /student/delete`
- `GitHubController`
  - `GET /github`
  - `GET /github/create`
  - `POST /github/create`
  - `GET /github/update/{id}`
  - `POST /github/update`
  - `POST /github/delete`
- `LinkedInController`
  - `GET /linkedin`
  - `GET /linkedin/create`
  - `POST /linkedin/create`
  - `GET /linkedin/update/{id}`
  - `POST /linkedin/update`
  - `POST /linkedin/delete`
- `ResumeController`
  - `GET /resume`
  - `GET /resume/create`
  - `POST /resume/create`
  - `GET /resume/update/{id}`
  - `POST /resume/update`
  - `POST /resume/delete`
- `FinalprojectController`
  - `GET /finalproject`
  - `GET /finalproject/create`
  - `POST /finalproject/create`
  - `GET /finalproject/update/{id}`
  - `POST /finalproject/update`
  - `POST /finalproject/delete`
- `NetworkingpersonController`
  - `GET /networkingperson`
  - `GET /networkingperson/create`
  - `POST /networkingperson/create`
  - `GET /networkingperson/update/{id}`
  - `POST /networkingperson/update`
  - `POST /networkingperson/delete`
- `NetworkingresourceController`
  - `GET /networkingresource`
  - `GET /networkingresource/create`
  - `POST /networkingresource/create`
  - `GET /networkingresource/update/{id}`
  - `POST /networkingresource/update`
  - `POST /networkingresource/delete`
