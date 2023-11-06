import patientDataHealth from "assets/data/patientprofile/patientHealth";
import { useEffect, useState } from "react";
import { Card, CardBody, Row, Col } from "reactstrap";

export const PatientProfileHealth = ({ patientId }) => {
  const [patientData, setPatientData] = useState(null);
  useEffect(() => {
    setPatientData(patientDataHealth);
  }, [patientData]);

  return (
    <>
      {patientData && (
        <Card className="bg-secondary shadow">
          <CardBody>
            <h6 className="heading-small text-muted mb-4">Health Info</h6>
            <div className="pl-lg-4">
              <Row>
                <Col lg="6">
                  Height: <b>{patientData.height}</b>
                </Col>
                <Col lg="6">
                  Weight: <b>{patientData.weight}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  Age: <b>{patientData.age}</b>
                </Col>
                <Col lg="6">
                  Occupation: <b>{patientData.occupation}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  Blood Pressure: <b>{patientData.bloodPressure}</b>
                </Col>
                <Col lg="6">
                  Diabetes: <b>{patientData.diabetes}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  Asthma: <b>{patientData.asthma}</b>
                </Col>
                <Col lg="6">
                  Allergies: <b>{patientData.allergies.join(", ")}</b>
                </Col>
              </Row>
              <Row>
                <Col lg="6">
                  Drinking: <b>{patientData.drinking}</b>
                </Col>
                <Col lg="6">
                  Smoking: <b>{patientData.smoking}</b>
                </Col>
              </Row>
            </div>
          </CardBody>
        </Card>
      )}
    </>
  );
};
