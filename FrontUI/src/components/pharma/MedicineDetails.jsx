import React from "react";
import {
  Container,
  Row,
  Col,
  Card,
  CardBody,
  CardTitle,
  CardText,
} from "reactstrap";
import {
  FaMedkit,
  FaPrescriptionBottle,
  FaTags,
  FaInfo,
  FaExclamationTriangle,
  FaThumbsDown,
  FaIndustry,
  FaBarcode,
  FaCalendar,
  FaImage,
} from "react-icons/fa";
import { medicineDetails } from "assets/data/medicine/medicineDetails";
import { Link } from "react-router-dom";

const MedicineDetails = () => {
  return (
    <>
      <div>
        <Container className="d-flex align-items-center" fluid>
          <Row>
            <Col lg="7" md="10">
              <h1 className="display-2 text-white">
                {medicineDetails.commercialName}
              </h1>
              <p className="text-white mt-0 mb-5">
                {medicineDetails.description}
                <br></br>
                <Link
                  style={{
                    fontWeight: "bold",
                    backgroundColor: "#f1f1f1",
                    padding: "5px",
                    borderRadius: "3px",
                    opacity: 0.8,
                  }}
                  to={"/common/medicines"}
                >
                  Back to medicine list
                </Link>
              </p>
            </Col>
          </Row>
        </Container>
      </div>
      <Container className="mt-2" fluid>
        <Row>
          <Col sm="4">
            <Card
              style={{
                backgroundColor: "#111144",
                borderRadius: "20px",
              }}
            >
              <CardBody>
                <img
                  src={medicineDetails.photo}
                  alt={medicineDetails.commercialName}
                  style={{
                    borderRadius: "15px",
                    width: "100%",
                  }}
                />
              </CardBody>
            </Card>
          </Col>
          <Col sm="8">
            <Card
              style={{
                color: "white",
                backgroundColor: "#111144",
                borderRadius: "20px",
              }}
            >
              <CardBody>
                <CardTitle tag="h1" style={{ color: "white" }}>
                  Medicine Details
                </CardTitle>
                <CardText>
                  <CardText>
                    <FaMedkit /> <strong>Commercial Name:</strong>{" "}
                    {medicineDetails.commercialName}
                  </CardText>
                  <CardText>
                    <FaPrescriptionBottle /> <strong>Medicine Name:</strong>{" "}
                    {medicineDetails.medicineName}
                  </CardText>
                  <FaTags /> <strong>Classification:</strong>{" "}
                  {medicineDetails.classification}
                </CardText>
                <CardText>
                  <FaInfo /> <strong>Description:</strong>{" "}
                  {medicineDetails.description}
                </CardText>
                <CardText>
                  <FaExclamationTriangle /> <strong>Warnings:</strong>{" "}
                  {medicineDetails.warnings}
                </CardText>
                <CardText>
                  <FaThumbsDown /> <strong>Adverse Effects:</strong>{" "}
                  {medicineDetails.adverseEffects}
                </CardText>
                <CardText>
                  <FaIndustry /> <strong>Manufacturer:</strong>{" "}
                  {medicineDetails.manufacturer}
                </CardText>
                <CardText>
                  <FaBarcode /> <strong>National Drug Code:</strong>{" "}
                  {medicineDetails.nationalDrugCode}
                </CardText>
                <CardText>
                  <FaCalendar /> <strong>Expiration Date:</strong>{" "}
                  {medicineDetails.expirationDate}
                </CardText>
                <CardText>
                  <FaImage /> <strong>Alert Message:</strong>{" "}
                  {medicineDetails.alertMessage}
                </CardText>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default MedicineDetails;