const migrationAreas = [
  'Student profile',
  'GitHub profile checklist',
  'LinkedIn profile checklist',
  'Resume builder',
  'Final project tracker',
  'Networking contacts and resources',
];

export function HomePage() {
  return (
    <section className="stack">
      <div className="section-card">
        <p className="eyebrow">Build tooling</p>
        <h2>Vite over in-app bundling</h2>
        <p>
          The frontend lives in a dedicated <code>frontend/</code> workspace.
          That keeps React dependencies isolated from Maven, gives fast local
          feedback, and avoids a risky all-at-once replacement of the current
          Thymeleaf screens.
        </p>
      </div>

      <div className="section-grid">
        <article className="section-card">
          <p className="eyebrow">Included now</p>
          <ul className="checklist">
            <li>Vite + React + TypeScript scaffold</li>
            <li>Browser routing with a shell layout</li>
            <li>Environment-based API configuration</li>
            <li>Shared fetch client for the Spring backend</li>
          </ul>
        </article>

        <article className="section-card">
          <p className="eyebrow">Migration targets</p>
          <ul className="checklist">
            {migrationAreas.map((area) => (
              <li key={area}>{area}</li>
            ))}
          </ul>
        </article>
      </div>
    </section>
  );
}
