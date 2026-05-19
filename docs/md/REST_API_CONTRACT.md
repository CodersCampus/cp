# REST API Contract

## Purpose

This contract defines the first-pass REST API shape for migrating the current Thymeleaf UI to a React frontend. It documents endpoint naming, request and response payloads, auth behavior, ownership rules, error handling, pagination, and acceptance criteria for the future backend REST implementation.

The current Thymeleaf screen inventory is documented in `docs/md/THYMELEAF_SCREEN_AUDIT.md`. This contract keeps the existing feature areas but moves their future API surface under `/api`.

## 1. Base API Rules

- All new REST endpoints must live under `/api`.
- API endpoints must consume and produce JSON.
- API endpoints must not return Thymeleaf template names.
- API endpoints must not return browser redirects such as `redirect:/github`.
- API controllers should live beside the existing Thymeleaf controllers instead of replacing them immediately.
- Existing service classes should be reused where possible.
- Business logic should remain in services, not REST controllers.
- Entity ownership must be checked server-side before returning, updating, or deleting user-owned records.
- Request bodies must not accept nested `Student` objects from the frontend. The backend should associate records with the current session user.

## 2. Auth And Ownership Rules

The current app stores Firebase/OAuth-derived user information in the HTTP session through `POST /send-oauth`. During the first REST migration phase, the API should continue using the session `uid` as the authenticated user identity.

Future React code should treat session identity as server-owned state:

- The frontend must not submit `uid` to create or update user-owned records.
- The backend should read `uid` from the HTTP session.
- If the session has no `uid`, return `401 Unauthorized`.
- If a record exists but belongs to another user, return `403 Forbidden`.
- If a record does not exist, return `404 Not Found`.

The existing `POST /send-oauth` route may remain during migration. A later auth cleanup ticket may introduce:

```text
POST /api/auth/session
DELETE /api/auth/session
GET /api/auth/me
```

That auth cleanup is outside the scope of this contract ticket.

## 3. Standard Response Shapes

### Success Response

Single-resource endpoints return the resource object directly:

```json
{
  "id": 1,
  "studentId": 7,
  "url": "https://github.com/example",
  "handle": true
}
```

List endpoints return an array for the first implementation:

```json
[
  {
    "id": 1,
    "studentId": 7,
    "url": "https://github.com/example",
    "handle": true
  }
]
```

If pagination is added, list responses should use the paged response shape defined in section 6.

### Error Response

All API errors should use the same JSON shape:

```json
{
  "message": "Validation failed",
  "errors": {
    "url": "Invalid URL"
  }
}
```

For non-field-specific errors:

```json
{
  "message": "Unauthorized",
  "errors": {}
}
```

## 4. Endpoint List

### Students

The student API is scoped to the current session user.

```text
GET   /api/students/me
PATCH /api/students/me
```

Read response:

```json
{
  "id": 1,
  "name": "Example Student",
  "assignmentNum": 12,
  "ide": "IntelliJ",
  "dateCreated": "2026-05-18T12:00:00Z",
  "willingToMentor": true,
  "mentee": "Example Mentee"
}
```

Patch request:

```json
{
  "assignmentNum": 12,
  "ide": "IntelliJ",
  "willingToMentor": true,
  "mentee": "Example Mentee"
}
```

### GitHub Profiles

```text
GET    /api/github-profiles
GET    /api/github-profiles/{id}
POST   /api/github-profiles
PUT    /api/github-profiles/{id}
DELETE /api/github-profiles/{id}
```

Request body for create/update:

```json
{
  "handle": true,
  "enhancedReadMe": true,
  "renamedAssignments": true,
  "pinnedRepos": true,
  "externalLinks": "Portfolio, LinkedIn",
  "image": true,
  "headline": true,
  "contactDetails": true,
  "url": "https://github.com/example"
}
```

Response body:

```json
{
  "id": 1,
  "studentId": 7,
  "handle": true,
  "enhancedReadMe": true,
  "renamedAssignments": true,
  "pinnedRepos": true,
  "externalLinks": "Portfolio, LinkedIn",
  "image": true,
  "headline": true,
  "contactDetails": true,
  "url": "https://github.com/example"
}
```

### LinkedIn Profiles

```text
GET    /api/linkedin-profiles
GET    /api/linkedin-profiles/{id}
POST   /api/linkedin-profiles
PUT    /api/linkedin-profiles/{id}
DELETE /api/linkedin-profiles/{id}
```

Request body for create/update:

```json
{
  "firstName": "Example",
  "lastName": "Student",
  "url": "https://linkedin.com/in/example",
  "banner": true,
  "about": true,
  "featuredPosts": true,
  "activity": true,
  "skills": true,
  "email": true,
  "biography": true,
  "education": true,
  "experience": true,
  "location": true,
  "image": true,
  "title": true
}
```

Response body includes `id` and `studentId` plus the request fields.

### Resumes

```text
GET    /api/resumes
GET    /api/resumes/{id}
POST   /api/resumes
PUT    /api/resumes/{id}
DELETE /api/resumes/{id}
```

Request body for create/update:

```json
{
  "phoneNumber": "555-555-5555",
  "email": "student@example.com",
  "city": "Austin",
  "state": "TX",
  "linkedIn": "https://linkedin.com/in/example",
  "gitHub": "https://github.com/example",
  "summary": "Short professional summary",
  "skills": "Java, Spring, SQL",
  "workExperience": "Work history",
  "education": "Education history",
  "projects": "Project summary"
}
```

Response body includes `id` and `studentId` plus the request fields.

### Final Projects

```text
GET    /api/final-projects
GET    /api/final-projects/{id}
POST   /api/final-projects
PUT    /api/final-projects/{id}
DELETE /api/final-projects/{id}
```

Request body for create/update:

```json
{
  "projectName": "Career Tracker",
  "proposal": "https://example.com/proposal",
  "description": "Tracks student career prep progress",
  "crud": "Students, resumes, contacts",
  "tables": "student, resume, networkingperson",
  "views": "Dashboard, list, create, update"
}
```

Response body includes `id` and `studentId` plus the request fields.

### Networking People

```text
GET    /api/networking-people
GET    /api/networking-people/{id}
POST   /api/networking-people
PUT    /api/networking-people/{id}
DELETE /api/networking-people/{id}
```

Request body for create/update:

```json
{
  "name": "Example Person",
  "linkedinUrl": "https://linkedin.com/in/example-person",
  "techStack": "Java, React",
  "firstContactDate": "2026-05-01",
  "lastContactDate": "2026-05-18",
  "otherNotesAboutPerson": "Met during a local meetup"
}
```

Response body includes `id` and `studentId` plus the request fields.

### Networking Resources

```text
GET    /api/networking-resources
GET    /api/networking-resources/{id}
POST   /api/networking-resources
PUT    /api/networking-resources/{id}
DELETE /api/networking-resources/{id}
```

Request body for create/update:

```json
{
  "resourceName": "Local Java Meetup",
  "type": "Meetup",
  "cost": "Free",
  "geographicScope": "Austin",
  "notes": "Monthly event"
}
```

Response body includes `id` and `studentId` plus the request fields.

## 5. Standard Status Codes

```text
200 OK           Successful read or update
201 Created      Successful create
204 No Content   Successful delete
400 Bad Request  Malformed request or invalid field values
401 Unauthorized Missing authenticated session user
403 Forbidden    Authenticated user cannot access this record
404 Not Found    Requested record does not exist
409 Conflict     Current user already has a one-to-one record when creating another
500 Server Error Unexpected server failure
```

Validation examples:

- Invalid GitHub URL: `400 Bad Request`
- Invalid LinkedIn URL: `400 Bad Request`
- Invalid final project proposal URL: `400 Bad Request`
- Attempting to create a second GitHub, LinkedIn, or Resume record for the same student: `409 Conflict`

## 6. Pagination, Sorting, And Filtering

The current Thymeleaf screens do not expose search, filter, sort, or pagination controls. The first REST implementation may return plain arrays.

For endpoints that can grow large, use these optional query parameters:

```text
GET /api/networking-people?page=0&size=20&sort=name,asc
GET /api/networking-resources?page=0&size=20&sort=resourceName,asc
```

Paged response shape:

```json
{
  "content": [],
  "page": 0,
  "size": 20,
  "totalElements": 0,
  "totalPages": 0
}
```

Default paging rules:

- `page` is zero-based.
- Default `size` is `20`.
- Maximum `size` is `100`.
- Invalid sort fields return `400 Bad Request`.

## 7. Acceptance Criteria

Issue 917 is resolved when this contract is reviewed and accepted as the implementation target for future React and REST migration tickets.

- REST endpoint naming convention is documented.
- Request and response body shapes are documented for each current entity.
- Standard API error response format is documented.
- Auth/session ownership rules are documented.
- Pagination, sorting, and filtering decisions are documented.
- Existing Thymeleaf page routes are clearly separated from future `/api` routes.
- Future REST implementation tickets can reference this document as their source of truth.

## Follow-Up Implementation Tickets

This document does not implement the REST controllers. Recommended follow-up tickets:

- Add REST controllers for `/api/students/me`.
- Add REST controllers for GitHub, LinkedIn, Resume, Final Project, Networking People, and Networking Resources.
- Add shared API exception handling.
- Add request/response DTOs that avoid exposing JPA entity relationships directly.
- Add MockMvc tests for API status codes and ownership checks.
- Add OpenAPI/Swagger generation or a maintained OpenAPI YAML file once the team decides whether the contract should be generated from code or written contract-first.
