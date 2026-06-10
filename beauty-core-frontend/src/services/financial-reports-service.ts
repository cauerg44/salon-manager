import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { TotalProfitFilteredRequest } from "../models/financial-report";

export function getTotalProfitFiltered(request: TotalProfitFilteredRequest) {

  const config: AxiosRequestConfig = {
    url: `/financial-reports/summary?start=${request.start}&end=${request.end}`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function getTotalProfitInLive() {

  const config: AxiosRequestConfig = {
    url: "/financial-reports/profit",
    withCredentials: true
  }

  return requestBackend(config);
}

export function getProfessionalTotalProfitInLive(professionalId: number) {

  const config: AxiosRequestConfig = {
    url: `/financial-reports/${professionalId}/profit`,
    withCredentials: true,
    params: {
      professionalId,
    }
  }

  return requestBackend(config);
}