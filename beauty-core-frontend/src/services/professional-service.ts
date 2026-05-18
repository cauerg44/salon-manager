import { requestBackend } from "../utils/requests";
import * as authService from './auth-service.ts';

export function findProfessionalLogged() {

  const headers = {
    Authorization: "Bearer " + authService.getAccessToken()
  }

  return requestBackend({ url: `v1/professionals/me`, headers });
}