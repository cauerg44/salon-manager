import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { ClientDTO } from "../models/client";


export function findAllClientsPaged(page: number, name: string, size = 12, sort = "name") {

  const config: AxiosRequestConfig = {
    url: "/clients",
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

export function findClientById(clientId: number) {

  const config: AxiosRequestConfig = {
    url: `/clients/${clientId}`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function create(request: ClientDTO) {

  const config: AxiosRequestConfig = {
    method: 'POST',
    url: "/clients",
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}

export function update(request: ClientDTO) {

  const config: AxiosRequestConfig = {
    method: 'PATCH',
    url: `/clients/${request.id}`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}