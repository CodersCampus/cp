import { NavLink, Outlet } from 'react-router';

import { env } from '../../shared/config/env';

const navItems = [
  { to: '/', label: 'Overview', end: true },
  { to: '/student', label: 'Student Profile' },
];

export function AppShell() {
  return (
    <div className="app-shell">
      <header className="hero">
        <div className="hero__copy">
          <p className="eyebrow">React migration scaffold</p>
          <h1>{env.appTitle}</h1>
          <p className="hero__lede">
            Vite runs the React client as a separate app inside this repository
            so the existing Spring and Thymeleaf flow can stay live while the UI
            migrates route by route.
          </p>
        </div>
        <div className="hero__panel">
          <p>Runtime configuration</p>
          <dl>
            <div>
              <dt>API base URL</dt>
              <dd>{env.apiBaseUrl}</dd>
            </div>
            <div>
              <dt>Backend URL</dt>
              <dd>{env.backendUrl}</dd>
            </div>
          </dl>
        </div>
      </header>

      <div className="content-shell">
        <nav aria-label="Primary" className="side-nav">
          {navItems.map((item) => (
            <NavLink
              key={item.to}
              className={({ isActive }) =>
                isActive ? 'side-nav__link side-nav__link--active' : 'side-nav__link'
              }
              end={item.end}
              to={item.to}
            >
              {item.label}
            </NavLink>
          ))}
        </nav>

        <main className="page-content">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
