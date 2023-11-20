import { Card, CardHeader, CardBody, Row, Col } from "reactstrap";
import DoctorProfileAvailability from "./DoctorProfileAvailability";
import { useEffect, useState } from "react";
import doctorTreatmentsCount from "assets/data/doctorprofile/doctorTreatmentCount";
import doctoReviewCount from "assets/data/doctorprofile/doctorReviewsCount";

export const DoctorProfileBio = ({ doctorData }) => {
  const [reviewCount, setReviewCount] = useState();
  const [treatmentsCount, setTreatmentsCount] = useState();

  useEffect(() => {
    setTreatmentsCount(doctorTreatmentsCount);
    setReviewCount(doctoReviewCount);
  }, [reviewCount, treatmentsCount]);

  return (
    <>
      <Card className="card-profile shadow">
        <Row className="justify-content-center">
          <Col className="order-lg-2" lg="6">
            <div className="card-profile-image">
              <a href="#pablo" onClick={(e) => e.preventDefault()}>
                <img
                  className="rounded-circle"
                  style={{
                    height: "200px",
                    width: "200px",
                    backgroundImage: `url(${doctorData.profilePhoto})`,
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                  }}
                />
              </a>
            </div>
          </Col>
        </Row>
        <CardHeader className="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4"></CardHeader>
        <CardBody className="pt-0 pt-md-4">
          <Row>
            <div className="col">
              <div className="card-profile-stats d-flex justify-content-center mt-md-5">
                <div>
                  <span className="heading">{treatmentsCount}</span>
                  <span className="description">Treatments</span>
                </div>
                <div>
                  <span className="heading">{doctorData.experience}</span>
                  <span className="description">Years of Experience</span>
                </div>
                <div>
                  <span className="heading">{reviewCount}</span>
                  <span className="description">Feedbacks</span>
                </div>
              </div>
            </div>
          </Row>
          <div className="text-center">
            <h3>{`Dr. ${doctorData.firstName} ${doctorData.lastName}`}</h3>
            <div className="h5 mt-4">
              <i className="ni business_briefcase-24 mr-2" />
              {doctorData.specializations} specialist
            </div>
            <div>
              <i className="ni education_hat mr-2" />@
              {doctorData.qualifications[0].institution}
            </div>
            <hr className="my-4" />
            <p>{doctorData.bio}</p>
          </div>

          <DoctorProfileAvailability doctorId={doctorData.doctorId} />
        </CardBody>
      </Card>
    </>
  );
};
