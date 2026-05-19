import { type AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";

export function findAll() {

  const config: AxiosRequestConfig = {
    url: `/specializations`,
    withCredentials: true
  }

  return requestBackend(config);
}