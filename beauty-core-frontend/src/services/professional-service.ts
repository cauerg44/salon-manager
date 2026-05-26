import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";

export function findProfessionalLogged() {

  const config: AxiosRequestConfig = {
    url: `/professionals/me`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function findAllProfessionals(page: number, name: string, size = 12, sort = "name") {

  const config: AxiosRequestConfig = {
    url: `/professionals`,
    withCredentials: true,
    params: {
      page,
      name,
      size,
      sort
    }
  }

  return requestBackend(config);
}

export function deactivateProfessional(professionalId: number) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/professionals/${professionalId}/deactivate`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function activateProfessional(professionalId: number) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/professionals/${professionalId}/activate`,
    withCredentials: true
  }

  return requestBackend(config);
}