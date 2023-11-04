import patientDataBio from "assets/data/patientprofile/patientBio";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Card, CardHeader, CardBody, Row, Col } from "reactstrap";

export const PatientProfileBio = ({ patientId }) => {
  const [patientData, setPatientData] = useState(null);
  useEffect(() => {
    setPatientData(patientDataBio);
  }, [patientData]);

  return (
    <>
      <Card className="bg-secondary shadow">
        <CardHeader className="bg-white border-0">
          <Row className="align-items-center">
            <Col xs="8">
              <h3 className="mb-0">
                <b>PATIENT PROFILE</b>
              </h3>
            </Col>
            <Col className="text-right" xs="4">
              <Link to={"/health/edit-profile"} className="btn btn-primary">
                Edit profile
              </Link>
            </Col>
          </Row>
        </CardHeader>
        <CardBody>
          <h6 className="heading-small text-muted mb-4">Primary Info</h6>
          {patientData && (
            <div className="pl-lg-4">
              <Row>
                <Col lg="6">
                  <b>Patient ID: {patientId}</b>
                </Col>
                <Col lg="6">
                  Email: <b>{patientData.email}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  First Name: <b>{patientData.firstName}</b>
                </Col>
                <Col lg="6">
                  Last Name: <b>{patientData.lastName}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  Gender: <b>{patientData.gender}</b>
                </Col>
                <Col lg="6">
                  Blood group: <b>{patientData.bloodGroup}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  Phone: <b>{patientData.phone}</b>
                </Col>
                <Col lg="6">
                  Address: <b>{patientData.address}</b>
                </Col>
              </Row>
            </div>
          )}
        </CardBody>
      </Card>
    </>
  );
};
