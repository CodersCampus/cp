import { apiClient } from '../../shared/api/client';
import type { Student } from '../../shared/types/student';

export function getCurrentStudent() {
  return apiClient.get<Student>('/students/me');
}
