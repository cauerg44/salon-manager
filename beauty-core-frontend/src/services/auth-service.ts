import type { AxiosRequestConfig } from "axios";
import type { CredentialsDTO } from "../models/auth";
import { requestBackend } from "../utils/requests";

export function loginRequest(loginData: CredentialsDTO) {

  const headers = {
    "Content-Type": "application/json"
  }

  const config: AxiosRequestConfig = {
    method: "POST",
    url: "/v1/auth/login",
    data: loginData,
    headers
  }

  return requestBackend(config);
}