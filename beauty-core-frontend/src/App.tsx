import { Navigate, Route, Routes } from "react-router-dom";
import ClientHome from "./routes/ClientHome";
import Home from "./routes/ClientHome/Home";
import General from "./routes/ClientHome/General";
import Specializations from "./routes/ClientHome/Specializations";
import SpecializationsListing from "./routes/ClientHome/Specializations/SpecializationsListing";
import Login from "./routes/ClientHome/Login";
import { unstable_HistoryRouter as HistoryRouter } from 'react-router-dom';
import { history } from './utils/history';
import ClientsListing from "./routes/ClientHome/Clients/ClientsListing";
import Clients from "./routes/ClientHome/Clients";
import Services from "./routes/ClientHome/Services";
import ServicesListing from "./routes/ClientHome/Services/ServicesListing";
import FinancialReports from "./routes/ClientHome/FinancialReports";
import TotalProfitInLive from "./routes/ClientHome/FinancialReports/TotalProfitInLive";
import Appointments from "./routes/ClientHome/Appointments";
import AppointmentsInWaiting from "./routes/ClientHome/Appointments/AppointmentsInWaiting";
import AppointmentsInProgress from "./routes/ClientHome/Appointments/AppointmentsInProgress";
import AppointmentsFinished from "./routes/ClientHome/Appointments/AppointmentsFinished";
import AppointmentsCanceled from "./routes/ClientHome/Appointments/AppointmentsCanceled";
import AppointmentsNotPaid from "./routes/ClientHome/Appointments/AppointmentsNotPaid";
import Professionals from "./routes/ClientHome/Professionals";
import ProfessionalsListing from "./routes/ClientHome/Professionals/ProfessionalsListing";
import ProfessionalTotalProfit from "./routes/ClientHome/FinancialReports/ProfessionalTotalProfit";
import Profile from "./routes/ClientHome/Profile";
import ProfessionalForm from "./routes/ClientHome/Professionals/ProfessionalForm";
import SpecialtyForm from "./routes/ClientHome/Specializations/SpecialtyForm";
import ClientForm from "./routes/ClientHome/Clients/ClientForm";

export default function App() {

  return (
    <>
      <HistoryRouter history={history}>
        <Routes>
          <Route path="/" element={<ClientHome />}>
            <Route index element={<Home />} />
            <Route path="home" element={<Home />} />
            <Route path="profile" element={<Profile />} />
            <Route path="login" element={<Login />} />
            <Route path="general" element={<General />} />

            <Route path="appointments" element={<Appointments />}>
              <Route path="in-waiting" element={<AppointmentsInWaiting />} />
              <Route path="in-progress" element={<AppointmentsInProgress />} />
              <Route path="finished" element={<AppointmentsFinished />} />
              <Route path="canceled" element={<AppointmentsCanceled />} />
              <Route path="not-paid" element={<AppointmentsNotPaid />} />
            </Route>

            <Route path="professionals" element={<Professionals />}>
              <Route path="listing" element={<ProfessionalsListing />} />
              <Route path="create" element={<ProfessionalForm />} />
              <Route path="edit/:professionalId" element={<ProfessionalForm />} />
            </Route>

            <Route path="services" element={<Services />}>
              <Route path="listing" element={<ServicesListing />} />
            </Route>

            <Route path="specializations" element={<Specializations />}>
              <Route path="listing" element={<SpecializationsListing />} />
              <Route path="create" element={<SpecialtyForm />} />
              <Route path="edit/:specialtyId" element={<SpecialtyForm />} />
            </Route>

            <Route path="clients" element={<Clients />}>
              <Route path="listing" element={<ClientsListing />} />
              <Route path="create" element={<ClientForm />} />
              <Route path="edit/:clientId" element={<ClientForm />} />
            </Route>

            <Route path="financial-reports" element={<FinancialReports />}>
              <Route path="total-profit-in-live" element={<TotalProfitInLive />} />
              <Route path="professional-total-profit-in-live" element={<ProfessionalTotalProfit />} />
            </Route>
          </Route>
          <Route path="*" element={<Navigate to={"/"} />} />
        </Routes>
      </HistoryRouter>
    </>
  )
}