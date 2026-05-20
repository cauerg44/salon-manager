import { Navigate, Route, Routes } from "react-router-dom";
import ClientHome from "./routes/ClientHome";
import Home from "./routes/ClientHome/Home";
import General from "./routes/ClientHome/General";
import Specializations from "./routes/ClientHome/Specializations";
import SpecializationsListing from "./routes/ClientHome/Specializations/SpecializationsListing";
import Login from "./routes/ClientHome/Login";
import { unstable_HistoryRouter as HistoryRouter } from 'react-router-dom';
import { history } from './utils/history';
import { useState } from "react";
import { ContextProfessionalLogged } from "./utils/context-professional-logged";
import ClientsListing from "./routes/ClientHome/Clients/ClientsListing";
import Clients from "./routes/ClientHome/Clients";
import Services from "./routes/ClientHome/Services";
import ServicesListing from "./routes/ClientHome/Services/ServicesListing";

export default function App() {

  const [contextProfessionalLogged, setContextProfessionalLogged] = useState<string>('');

  return (
    <>
      <ContextProfessionalLogged.Provider value={{ contextProfessionalLogged, setContextProfessionalLogged }} >
        <HistoryRouter history={history}>
          <Routes>
            <Route path="/" element={<ClientHome />}>
              <Route index element={<Home />} />
              <Route path="home" element={<Home />} />
              <Route path="login" element={<Login />} />
              <Route path="general" element={<General />} />

              <Route path="services" element={<Services />}>
                <Route path="listing" element={<ServicesListing />} />
              </Route>

              <Route path="specializations" element={<Specializations />}>
                <Route path="listing" element={<SpecializationsListing />} />
              </Route>
              <Route path="clients" element={<Clients />}>
                <Route path="listing" element={<ClientsListing />} />
              </Route>
            </Route>
            <Route path="*" element={<Navigate to={"/"} />} />
          </Routes>
        </HistoryRouter>
      </ContextProfessionalLogged.Provider>
    </>
  )
}