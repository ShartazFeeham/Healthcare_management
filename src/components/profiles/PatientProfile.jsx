import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Container, Row, Col } from "reactstrap";
import { DoctorProfileDetails } from "./DoctorProfileDetails";
import { DoctorProfileBio } from "./DoctorProfileBio";
import doctorProfileData from "assets/data/doctorProfile";
import { DoctorProfileReviews } from "./DoctorProfileReviews";

const PatientProfile = () => {
  const { patientId } = useParams();

  useEffect(() => {
    console.log("Patient ID:", patientId);
  }, [patientId]);

  const [isEdit, setIsEdit] = useState(false);
  const [doctorData, setDoctorData] = useState(doctorProfileData);

  const handleEditClick = () => {
    setIsEdit(!isEdit);
  };

  return (
    <>
      <>
        <div
          className="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
          style={{
            minHeight: "400px",
            backgroundImage:
              "url(" + require("../../assets/img/cover/health5.jpg") + ")",
            backgroundSize: "cover",
            backgroundPosition: "center top",
          }}
        >
          <span className="mask bg-gradient-default opacity-6" />
          <Container className="d-flex align-items-center" fluid>
            <Row>
              <Col lg="7" md="10">
                <h1 className="display-2 text-white">Dr. Meherab Hasan</h1>
                <p className="text-white mt-0 mb-5">
                  Explore our doctor's extensive profile, bio, specialization,
                  years of experience, and convenient telemedicine services.
                  Schedule an appointment and receive the finest healthcare
                  on-site or remotely. Your well-being is our priority.
                </p>
              </Col>
            </Row>
          </Container>
        </div>
      </>
      <Container className="mt--7" fluid>
        <Row>
          <Col className="order-xl-2 mb-5 mb-xl-0" xl="4">
            <DoctorProfileBio doctorData={doctorData} />
          </Col>
          <Col className="order-xl-1" xl="8">
            <DoctorProfileDetails
              doctorData={doctorData}
              handleEditClick={handleEditClick}
              isEdit={isEdit}
            />
            <DoctorProfileReviews doctorId={doctorData.doctorId} />
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default PatientProfile;
