export type CredentialsDTO = {
  email: string;
  password: string;
}

export type RegisterRequestDTO = {
  name: string;
  email: string;
  password: string;
  specializationsIds: number[];
}