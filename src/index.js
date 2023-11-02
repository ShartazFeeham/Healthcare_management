import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";

import "assets/plugins/nucleo/css/nucleo.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "assets/scss/argon-dashboard-react.scss";
import Logged from "layouts/Logged";
import Public from "layouts/Public";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/health/*" element={<Logged />} />
      <Route path="/public/*" element={<Public />} />
      {/* I can add page not found page here! */}
      {/* <Route path="*" element={<Navigate to="/health/index" replace />} /> */}
    </Routes>
  </BrowserRouter>
);
