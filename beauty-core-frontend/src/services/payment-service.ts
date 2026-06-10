import type { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { PaymentDTO } from "../models/payment";

export function createPayment(appointmentId: number, request: PaymentDTO) {

  const config: AxiosRequestConfig = {
    method: "POST",
    url: `/payments/${appointmentId}`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}