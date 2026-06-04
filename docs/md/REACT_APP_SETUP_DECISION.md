# React App Setup Decision

## Decision

The React frontend should live as a separate app in `frontend/` inside this repository, not inside Spring's `src/main/resources`.

## Why

- The current application is still actively using Thymeleaf templates.
- A separate Vite app lets the team migrate screens incrementally instead of replacing the server-rendered UI all at once.
- Frontend dependencies, scripts, and tooling stay isolated from Maven and the Java source tree.
- Vite provides faster frontend feedback loops than bundling React directly into the Spring static asset pipeline during the initial migration.

## Development Model

- Spring Boot remains the backend and source of truth for auth and data.
- The Vite dev server runs the React app locally on port `5173` by default.
- Vite proxies `/api` and `/send-oauth` requests to Spring Boot on `http://localhost:8080` by default.
- The React app consumes backend JSON APIs without requiring browser CORS changes in development.

## Future Options

Once the React UI is mature, the team can choose one of these deployment models:

1. Build `frontend/` and copy the static output into Spring for a single deployable artifact.
2. Deploy the React app separately and point it at the Spring API with environment-specific base URLs.

This ticket sets up the project for either direction without locking the team into one too early.
