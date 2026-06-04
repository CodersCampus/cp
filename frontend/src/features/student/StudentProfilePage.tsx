import { useEffect, useState } from 'react';

import { getCurrentStudent } from './studentApi';
import type { Student } from '../../shared/types/student';

type LoadState =
  | { status: 'loading' }
  | { status: 'error'; message: string }
  | { status: 'ready'; student: Student };

export function StudentProfilePage() {
  const [state, setState] = useState<LoadState>({ status: 'loading' });

  useEffect(() => {
    let cancelled = false;

    async function loadStudent() {
      try {
        const student = await getCurrentStudent();

        if (!cancelled) {
          setState({ status: 'ready', student });
        }
      } catch (error) {
        if (!cancelled) {
          setState({
            status: 'error',
            message:
              error instanceof Error ? error.message : 'Unable to load student profile.',
          });
        }
      }
    }

    void loadStudent();

    return () => {
      cancelled = true;
    };
  }, []);

  return (
    <section className="stack">
      <div className="section-card">
        <p className="eyebrow">API integration</p>
        <h2>Student profile bootstrap</h2>
        <p>
          This route calls <code>GET /api/students/me</code> using the shared API
          client. It provides a concrete first slice for the broader React
          migration.
        </p>
      </div>

      {state.status === 'loading' && (
        <div className="section-card">
          <p>Loading the current student profile...</p>
        </div>
      )}

      {state.status === 'error' && (
        <div className="section-card section-card--danger">
          <p className="eyebrow">Request failed</p>
          <p>{state.message}</p>
          <p className="muted">
            If the backend route is not implemented yet, this confirms the
            frontend wiring is ready and waiting on the API endpoint.
          </p>
        </div>
      )}

      {state.status === 'ready' && (
        <div className="section-card">
          <dl className="data-grid">
            <div>
              <dt>Name</dt>
              <dd>{state.student.name}</dd>
            </div>
            <div>
              <dt>Assignment #</dt>
              <dd>{state.student.assignmentNum ?? 'Not set'}</dd>
            </div>
            <div>
              <dt>Preferred IDE</dt>
              <dd>{state.student.ide ?? 'Not set'}</dd>
            </div>
            <div>
              <dt>Willing to mentor</dt>
              <dd>{state.student.willingToMentor ? 'Yes' : 'No'}</dd>
            </div>
            <div>
              <dt>Mentee</dt>
              <dd>{state.student.mentee ?? 'Not set'}</dd>
            </div>
            <div>
              <dt>Created</dt>
              <dd>{state.student.dateCreated ?? 'Unknown'}</dd>
            </div>
          </dl>
        </div>
      )}
    </section>
  );
}
