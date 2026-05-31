import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { ServiceDTO } from "../models/service-dto";

export function findAllServices() {

  const config: AxiosRequestConfig = {
    url: "/services",
    withCredentials: true,
  }

  return requestBackend(config);
}

export function findById(serviceId: number) {

  const config: AxiosRequestConfig = {
    url: `/services/${serviceId}`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function create(request: ServiceDTO) {

  const config: AxiosRequestConfig = {
    method: "POST",
    url: `/services`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}

export function update(request: ServiceDTO) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/services/${request.id}`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}

export function deleteServiceById(serviceId: number) {

  const config: AxiosRequestConfig = {
    method: "DELETE",
    url: `/services/${serviceId}`,
    withCredentials: true
  }

  return requestBackend(config);
}