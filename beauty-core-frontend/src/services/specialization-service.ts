import { type AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import type { SpecialtyDTO } from "../models/specialty";

export function findAll() {

  const config: AxiosRequestConfig = {
    url: `/specializations`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function findSpecialtyById(specialtyId: number) {

  const config: AxiosRequestConfig = {
    url: `/specializations/${specialtyId}`,
    withCredentials: true
  }

  return requestBackend(config);
}

export function create(request: SpecialtyDTO) {

  const config: AxiosRequestConfig = {
    method: "POST",
    url: `/specializations`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}

export function updateSpecialty(request: SpecialtyDTO) {

  const config: AxiosRequestConfig = {
    method: "PATCH",
    url: `/specializations/${request.id}`,
    withCredentials: true,
    data: request
  }

  return requestBackend(config);
}

export function deleteById(specialtyId: number) {

  const config: AxiosRequestConfig = {
    method: "DELETE",
    url: `/specializations/${specialtyId}`,
    withCredentials: true
  }

  return requestBackend(config);
}