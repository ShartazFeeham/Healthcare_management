import React, { useEffect, useState } from "react";
import { Card, CardBody, Row, Col } from "reactstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faStethoscope,
  faCalendar,
  faComment,
  faPills,
  faDiagnoses,
  faChartLine,
} from "@fortawesome/free-solid-svg-icons";

import patientDataTreatments from "assets/data/patientprofile/patientTreatments";

export const PatientProfileTreatment = () => {
  const [treatmentData, setTreatmentData] = useState(null);
  useEffect(() => {
    setTreatmentData(patientDataTreatments);
  }, [treatmentData]);

  const renderTreatmentCard = (treatment, index) => (
    <Card className="mb-2" key={index}>
      <CardBody>
        <Row className="mb-2">
          <Col lg="12">
            <h4>
              <FontAwesomeIcon icon={faStethoscope} /> {treatment.diseaseName}
            </h4>
          </Col>
        </Row>
        <Row>
          <Col lg="6">
            <p>
              <FontAwesomeIcon icon={faCalendar} /> Issue Date:{" "}
              <b>{treatment.issueDate}</b>
            </p>
          </Col>
          <Col lg="6">
            <p>
              <FontAwesomeIcon icon={faCalendar} /> Closing Date:{" "}
              <b>{treatment.closingDate}</b>
            </p>
          </Col>
        </Row>
        <Row>
          <Col lg="12">
            <p>
              <FontAwesomeIcon icon={faComment} /> Doctor Comment:{" "}
              <b>{treatment.doctorComment}</b>
            </p>
          </Col>
        </Row>
        <Row>
          <Col lg="12">
            <p>
              <FontAwesomeIcon icon={faPills} /> Medicines:{" "}
              <b>
                {treatment.medicines
                  .map((medicine) => medicine.name)
                  .join(", ")}
              </b>
            </p>
          </Col>
        </Row>
        <Row>
          <Col lg="12">
            <p>
              <FontAwesomeIcon icon={faDiagnoses} /> Diagnoses:{" "}
              <b>
                {treatment.diagnoses
                  .map((diagnosis) => diagnosis.name)
                  .join(", ")}
              </b>
            </p>
          </Col>
        </Row>
        <Row>
          <Col lg="6">
            <p>
              <FontAwesomeIcon icon={faChartLine} /> Progression:{" "}
              <b>{treatment.progression}</b>
            </p>
          </Col>
        </Row>
      </CardBody>
    </Card>
  );

  return (
    <>
      <Card className="bg-secondary shadow mt-2">
        <CardBody>
          <div className="pl-lg-4">
            <h6 className="heading-small text-muted mb-4">Treatment Info</h6>
          </div>
          {treatmentData && (
            <div>
              {treatmentData.map((treatment, index) =>
                renderTreatmentCard(treatment, index)
              )}
            </div>
          )}
        </CardBody>
      </Card>
    </>
  );
};
