import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";


export function findAllAppointmentsByStatus(appointmentStatus: string, size = 12) {

  const config: AxiosRequestConfig = {
    url: "/appointments",
    withCredentials: true,
    params: {
      appointmentStatus,
      size
    }
  }

  return requestBackend(config);
}

export function findAllAppointmentsNotPaid(size = 12) {

  const config: AxiosRequestConfig = {
    url: "/appointments/not-paid",
    withCredentials: true,
    params: {
      size
    }
  }

  return requestBackend(config);
}