import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";


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