import type { ClientDTO } from "./client";
import type { ProfessionalDTO } from "./professional";
import type { ServiceDTO } from "./service-dto";

export type AppointmentDTO = {
  id: number;
  professional: ProfessionalDTO;
  client: ClientDTO;
  appointmentStatus: string;
  services: ServiceDTO[];
  discount: number;
  totalValue: number;
  remainingValue: number;
  isPaid: boolean;
}