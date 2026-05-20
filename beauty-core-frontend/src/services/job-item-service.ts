import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";


export function findAllServices() {

  const config: AxiosRequestConfig = {
    url: "/services",
    withCredentials: true,
  }

  return requestBackend(config);
}