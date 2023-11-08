import React, { useEffect, useState } from "react";
import { useLocation, Route, Routes, Navigate } from "react-router-dom";
import { Container, Row, Col } from "reactstrap";
import PublicNavbar from "components/Navbars/PublicNavbar";
import PublicFooter from "components/Footers/PublicFooter";
import routes from "routes.js";
import CommonNavbar from "components/Navbars/CommonNavbar";
import FloatingImages from "./FloatingImages";

const Common = (props) => {
  const mainContent = React.useRef(null);
  const location = useLocation();
  const [windowHeight, setWindowHeight] = useState(window.innerHeight);

  useEffect(() => {
    const handleResize = () => {
      setWindowHeight(window.innerHeight);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  useEffect(() => {
    document.body.classList.add("bg-default");
    return () => {
      document.body.classList.remove("bg-default");
    };
  }, []);

  useEffect(() => {
    document.documentElement.scrollTop = 0;
    document.scrollingElement.scrollTop = 0;
    mainContent.current.scrollTop = 0;
  }, [location]);

  const getRoutes = (routes) => {
    return routes.map((prop, key) => {
      if (prop.layout === "/common") {
        return (
          <Route path={prop.path} element={prop.component} key={key} exact />
        );
      } else {
        return null;
      }
    });
  };

  return (
    <>
      <FloatingImages />
      <div className="main-content" ref={mainContent}>
        <div className="py-7 py-lg-8"></div>
        <CommonNavbar />

        <Container
          className="mt--8 pb-5"
          style={{
            minHeight: `${windowHeight * 0.75}px`,
            opacity: 0.9,
          }}
        >
          <Row className="justify-content-center">
            <Routes>
              {getRoutes(routes)}
              <Route
                path="*"
                element={<Navigate to="/public/login" replace />}
              />
            </Routes>
          </Row>
        </Container>
      </div>
      <PublicFooter />
    </>
  );
};

export default Common;
