import { createContext } from "react";

export type ContextProfessionalLoggedType = {
  contextProfessionalLogged: string;
  setContextProfessionalLogged: (contextProfessionalLogged: string) => void;
}

export const ContextProfessionalLogged = createContext<ContextProfessionalLoggedType>({
  contextProfessionalLogged: '',
  setContextProfessionalLogged: () => { }
});