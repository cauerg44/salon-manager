import axios from "axios";
import { BASE_URL } from "../utils/system";

export function findAll() {
  return axios.get(`${BASE_URL}/specializations`);
}