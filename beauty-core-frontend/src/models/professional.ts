import type { SpecialtyDTO } from "./specialty";

export type ProfessionalDTO = {
  id: number;
  name: string;
  isActive: boolean;
  isWorking: boolean;
  specializations: SpecialtyDTO[];
}