import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";

export function findAllServices() {

  const config: AxiosRequestConfig = {
    url: "/services",
    withCredentials: true,
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