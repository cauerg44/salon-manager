import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";

export function getTotalProfitInLive() {

  const config: AxiosRequestConfig = {
    url: "/financial-reports/total-profit-in-live",
    withCredentials: true
  }

  return requestBackend(config);
}