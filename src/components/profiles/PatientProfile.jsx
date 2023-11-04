import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Col, Container, Row } from "reactstrap";
import { PatientProfileAchievements } from "./PatientProfileAchievements";
import { PatientProfileBio } from "./PatientProfileBio";
import { PatientProfilePhoto } from "./PatientProfilePhoto";
import patientDataBio from "assets/data/patientprofile/patientBio";
import { PatientProfileHealth } from "./PatientProfileHealth";
import { PatientProfileTreatment } from "./PatientProfileTreatment";

const PatientProfile = () => {
  const { patientId } = useParams();
  const [patientData, setPatientData] = useState(null);
  useEffect(() => {
    setPatientData(patientDataBio);
  }, [patientData]);

  useEffect(() => {
    console.log("Patient ID:", patientId);
  }, [patientId]);

  return (
    <>
      <div
        className="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
        style={{
          minHeight: "400px",
          backgroundImage:
            "url(" + require("../../assets/img/cover/patient2.jpg") + ")",
          backgroundSize: "cover",
          backgroundPosition: "center top",
        }}
      >
        <span className="mask bg-gradient-default opacity-6" />
        <Container className="d-flex align-items-center" fluid>
          <Row>
            <Col lg="7" md="10">
              {patientData && (
                <h1 className="display-2 text-white">
                  {patientData.firstName} {patientData.lastName}
                </h1>
              )}
              <p className="text-white mt-0 mb-5">
                Prioritize your health like never before. Our app empowers you
                to effortlessly browse through doctor and patient profiles, take
                charge of your health data, and secure appointments for your
                well-being.
              </p>
            </Col>
          </Row>
        </Container>
      </div>
      <Container className="mt--7" fluid>
        <Row>
          <Col className="order-xl-2 mb-5 mb-xl-0" xl="4">
            {patientData && (
              <PatientProfilePhoto
                patientId={patientId}
                photo={patientData.profilePhoto}
                firstName={patientData.firstName}
                lastName={patientData.lastName}
              />
            )}
          </Col>
          <Col className="order-xl-1 mb-2" xl="8">
            <PatientProfileBio patientId={patientId} />
          </Col>
        </Row>
        <Row>
          <Col className="order-xl-2 mb-5 mb-xl-0" xl="4">
            <PatientProfileAchievements patientId={patientId} />
          </Col>
          <Col className="order-xl-1" xl="8">
            <PatientProfileHealth patientId={patientId} />
            <PatientProfileTreatment patientId={patientId} />
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default PatientProfile;
