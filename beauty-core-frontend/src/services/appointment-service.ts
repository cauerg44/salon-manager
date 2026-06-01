import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { AppointmentDTO } from "../models/appointment";

export function startAppointment(appointmentId: number) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/appointments/${appointmentId}/start`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function cancelAppointment(appointmentId: number) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/appointments/${appointmentId}/cancel`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function findAppointmentById(appointmentId: number) {

  const config: AxiosRequestConfig = {
    url: `/appointments/${appointmentId}`,
    withCredentials: true
  }

  return requestBackend(config);
}

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

export function create(request: AppointmentDTO) {

  const config: AxiosRequestConfig = {
    method: "POST",
    url: "/appointments",
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}

export function update(request: AppointmentDTO) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/appointments/${request.id}`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}