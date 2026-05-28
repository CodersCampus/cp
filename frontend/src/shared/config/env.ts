function readEnv(name: string, fallback: string) {
  const value = (import.meta.env as Record<string, string | boolean | undefined>)[name];
  return typeof value === 'string' && value.length > 0 ? value : fallback;
}

export const env = {
  apiBaseUrl: readEnv('VITE_API_BASE_URL', '/api'),
  appTitle: readEnv('VITE_APP_TITLE', 'Coders Campus Portal'),
  backendUrl: readEnv('VITE_BACKEND_URL', 'http://localhost:8080'),
};
