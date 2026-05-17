import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import ClientHome from "./routes/ClientHome";
import Home from "./routes/ClientHome/Home";
import General from "./routes/ClientHome/General";
import Specializations from "./routes/ClientHome/Specializations";
import SpecializationsListing from "./routes/ClientHome/Specializations/SpecializationsListing";
import Login from "./routes/ClientHome/Login";

export default function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ClientHome />}>
            <Route index element={<Home />} />
            <Route path="home" element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="general" element={<General />} />
            <Route path="specializations" element={<Specializations />}>
              <Route path="listing" element={<SpecializationsListing />} />
            </Route>
          </Route>
          <Route path="*" element={<Navigate to={"/"} />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}