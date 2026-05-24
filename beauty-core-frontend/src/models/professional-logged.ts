import type { RoleDTO } from "./role";

export type ProfessionalLoggedDTO = {
  id: number;
  name: string;
  email: string;
  roles: RoleDTO[];
}