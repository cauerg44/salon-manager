import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";

export function findProfessionalLogged() {

  const config: AxiosRequestConfig = {
    url: `/professionals/me`,
    withCredentials: true
  }

  return requestBackend(config);
}