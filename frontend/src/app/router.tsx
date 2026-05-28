import { createBrowserRouter } from 'react-router';

import { AppShell } from './shell/AppShell';
import { HomePage } from '../features/home/HomePage';
import { StudentProfilePage } from '../features/student/StudentProfilePage';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <AppShell />,
    children: [
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: 'student',
        element: <StudentProfilePage />,
      },
    ],
  },
]);
