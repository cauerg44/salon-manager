import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import ClientHome from "./routes/ClientHome";
import Home from "./routes/ClientHome/Home";


export default function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ClientHome />}>
            <Route index element={<Home />} />
            <Route path="home" element={<Home />} />
          </Route>
          <Route path="*" element={<Navigate to={"/"} />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}