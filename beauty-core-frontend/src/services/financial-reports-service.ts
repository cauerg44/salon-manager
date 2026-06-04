import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { TotalProfitFilteredRequest } from "../models/financial-report";

export function getTotalProfitFiltered(request: TotalProfitFilteredRequest) {

  const config: AxiosRequestConfig = {
    url: `/financial-reports/total-profit-filtered?start=${request.start}&end=${request.end}`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function getTotalProfitInLive() {

  const config: AxiosRequestConfig = {
    url: "/financial-reports/total-profit-in-live",
    withCredentials: true
  }

  return requestBackend(config);
}

export function getProfessionalTotalProfitInLive(professionalId: number) {

  const config: AxiosRequestConfig = {
    url: `/financial-reports/${professionalId}/professional-total-profit-in-live`,
    withCredentials: true,
    params: {
      professionalId,
    }
  }

  return requestBackend(config);
}