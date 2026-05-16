import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import ClientHome from "./routes/ClientHome";
import Home from "./routes/ClientHome/Home";
import General from "./routes/ClientHome/General";


export default function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ClientHome />}>
            <Route index element={<Home />} />
            <Route path="home" element={<Home />} />
            <Route path="general" element={<General />} />
          </Route>
          <Route path="*" element={<Navigate to={"/"} />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}