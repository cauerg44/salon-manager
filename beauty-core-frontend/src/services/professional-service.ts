import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";

export function findProfessionalLogged() {

  const config: AxiosRequestConfig = {
    url: `v1/professionals/me`,
    withCredentials: true
  }

  return requestBackend(config);
}