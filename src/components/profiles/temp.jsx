import React, { useState } from "react";
import { Form } from "react-router-dom";
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  Container,
  Row,
  Col,
  Input,
  Table,
} from "reactstrap";

const DoctorProfile = () => {
  const [isEdit, setIsEdit] = useState(false);
  const [doctorData, setDoctorData] = useState({
    doctorId: "DMH001",
    firstName: "Mehrab",
    lastName: "Hasan",
    email: "mehrab.hasan@example.com",
    phone: "123-456-7890",
    gender: "Male",
    dateOfBirth: "1980-05-15",
    profilePhoto:
      "https://pbs.twimg.com/profile_images/1614339164534706181/Z9cn1yja_400x400.jpg",
    nidNo: "1234567890",
    residence: "123 Main Street, City, Country",
    bio: "I am a dedicated and experienced doctor with a strong commitment to providing quality healthcare. I specialize in cardiology and have been serving patients for over 15 years.",
    experience: "15 years",
    license: "MD123456",
    specializations: "Cardiology, Internal Medicine",
    qualifications: [
      {
        degree: "Doctor of Medicine",
        institute: "Medical College",
        year: "2005",
      },
      {
        degree: "Bachelor of Science in Biology",
        institute: "University of Science",
        year: "2001",
      },
    ],
    certifications: [
      {
        degree: "Cardiology Certification",
        institute: "Cardiology Institute",
        year: "2010",
      },
      {
        degree: "Internal Medicine Certification",
        institute: "Internal Medicine Institute",
        year: "2006",
      },
    ],
    treatmentsCount: 5000,
    feedbacksCount: 55,
    feedbacks: [
      {
        patientId: "P003",
        comment:
          "Dr. Hasan is the best cardiologist I've ever met. Highly recommended.",
        rating: 5,
      },
      {
        patientId: "P004",
        comment: "Excellent service! Dr. Hasan is knowledgeable and caring.",
        rating: 4,
      },
      {
        patientId: "P005",
        comment:
          "I'm very satisfied with the treatment I received from Dr. Mehrab Hasan.",
        rating: 5,
      },
      {
        patientId: "P006",
        comment:
          "Dr. Hasan's expertise in cardiology is unparalleled. I'm grateful for his care.",
        rating: 5,
      },
      {
        patientId: "P007",
        comment:
          "I had a wonderful experience with Dr. Hasan. He's a great doctor.",
        rating: 4,
      },
    ],
    availability: [
      {
        date: "2023-11-14",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "04:00 PM", toTime: "05:00 PM" },
        ],
      },
      {
        date: "2023-11-15",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
      {
        date: "2023-11-16",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
      {
        date: "2023-11-17",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "03:00 PM", toTime: "04:00 PM" },
        ],
      },
      {
        date: "2023-11-18",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "01:00 PM", toTime: "02:00 PM" },
        ],
      },
      {
        date: "2023-11-19",
        timeslots: [
          { fromTime: "11:00 AM", toTime: "12:00 PM" },
          { fromTime: "03:00 PM", toTime: "04:00 PM" },
        ],
      },
      {
        date: "2023-11-20",
        timeslots: [
          { fromTime: "10:00 AM", toTime: "11:00 AM" },
          { fromTime: "04:00 PM", toTime: "05:00 PM" },
        ],
      },
      {
        date: "2023-11-21",
        timeslots: [
          { fromTime: "09:00 AM", toTime: "10:00 AM" },
          { fromTime: "02:00 PM", toTime: "03:00 PM" },
        ],
      },
    ],
  });

  const handleEditClick = () => {
    setIsEdit(!isEdit);
  };

  const renderTextOrInput = (label, value, field) => {
    return isEdit ? (
      <Input
        className="form-control-alternative"
        value={value}
        onChange={(e) => {
          setDoctorData({ ...doctorData, [field]: e.target.value });
        }}
      />
    ) : (
      <span>
        <strong>{label}:</strong> {value}
      </span>
    );
  };

  const calculateAverageRating = (feedbacks) => {
    if (feedbacks.length === 0) return 0;

    const totalRating = feedbacks.reduce(
      (total, feedback) => total + feedback.rating,
      0
    );
    return (totalRating / feedbacks.length).toFixed(1);
  };

  return (
    <Container className="mt-7" fluid>
      <Row>
        <Col xl="4" className="mb-5 mb-xl-0">
          <Card className="card-profile shadow">
            <Row className="justify-content-center">
              <Col lg="6" className="order-lg-2">
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
                      alt="Doctor's Profile"
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
                      <span className="heading">
                        {doctorData.treatmentsCount}
                      </span>
                      <span className="description">Treatments</span>
                    </div>
                    <div>
                      <span className="heading">{doctorData.experience}</span>
                      <span className="description">Years of Experience</span>
                    </div>
                    <div>
                      <span className="heading">
                        {doctorData.feedbacksCount}
                      </span>
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
                  {doctorData.qualifications[0].institute}
                </div>
                <hr className="my-4" />
                <p>{doctorData.bio}</p>
              </div>
            </CardBody>
          </Card>
        </Col>
        <Col xl="8" className="order-xl-1">
          <Card className="bg-secondary shadow">
            <CardHeader className="bg-white border-0">
              <Row className="align-items-center">
                <Col xs="8">
                  <h3 className="mb-0">
                    <b>DOCTOR PROFILE</b>
                  </h3>
                </Col>
                <Col className="text-right" xs="4">
                  <Button color="primary" onClick={handleEditClick}>
                    {isEdit ? "Save Changes" : "Edit Profile"}
                  </Button>
                </Col>
              </Row>
            </CardHeader>
            <CardBody>
              {isEdit ? (
                <Form>{/* Edit mode fields */}</Form>
              ) : (
                <div>
                  <h6 className="heading-small text-muted mb-4">
                    Account Info
                  </h6>
                  <div className="pl-lg-4">
                    <Row>
                      <Col lg="6">
                        {renderTextOrInput(
                          "Doctor ID",
                          doctorData.doctorId,
                          "doctorId"
                        )}
                      </Col>
                      <Col lg="6">
                        {renderTextOrInput("Email", doctorData.email, "email")}
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="6">
                        {renderTextOrInput(
                          "First Name",
                          doctorData.firstName,
                          "firstName"
                        )}
                      </Col>
                      <Col lg="6">
                        {renderTextOrInput(
                          "Last Name",
                          doctorData.lastName,
                          "lastName"
                        )}
                      </Col>
                    </Row>
                  </div>
                  <hr className="my-4" />
                  <h6 className="heading-small text-muted mb-4">
                    Qualifications and Certifications
                  </h6>
                  <div className="pl-lg-4">
                    <Row>
                      <Col lg="6">
                        {renderTextOrInput(
                          "Degree",
                          doctorData.qualifications[0].degree,
                          "qualifications[0].degree"
                        )}
                      </Col>
                      <Col lg="6">
                        {renderTextOrInput(
                          "Year",
                          doctorData.qualifications[0].year,
                          "qualifications[0].year"
                        )}
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="6">
                        {renderTextOrInput(
                          "Degree",
                          doctorData.certifications[0].degree,
                          "certifications[0].degree"
                        )}
                      </Col>
                      <Col lg="6">
                        {renderTextOrInput(
                          "Year",
                          doctorData.certifications[0].year,
                          "certifications[0].year"
                        )}
                      </Col>
                    </Row>
                  </div>
                  <hr className="my-4" />
                  <h6 className="heading-small text-muted mb-4">
                    Personal Info
                  </h6>
                  <div className="pl-lg-4">
                    <Row>
                      <Col md="12">
                        {renderTextOrInput(
                          "Address",
                          doctorData.residence,
                          "residence"
                        )}
                      </Col>
                    </Row>
                  </div>
                </div>
              )}
            </CardBody>
          </Card>
        </Col>
      </Row>
      <Row>
        <Col>
          <Card className="bg-secondary shadow">
            <CardHeader className="bg-white border-0">
              <h3 className="mb-0">Feedbacks</h3>
            </CardHeader>
            <CardBody>
              <Table responsive>
                <thead className="thead-light">
                  <tr>
                    <th>Patient ID</th>
                    <th>Comment</th>
                    <th>Rating</th>
                  </tr>
                </thead>
                <tbody>
                  {doctorData.feedbacks.map((feedback, index) => (
                    <tr key={index}>
                      <td>{feedback.patientId}</td>
                      <td>{feedback.comment}</td>
                      <td>{feedback.rating}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
              <p>
                Average Rating: {calculateAverageRating(doctorData.feedbacks)}
              </p>
            </CardBody>
          </Card>
        </Col>
      </Row>
      <Row>
        <Col>
          <Card className="bg-secondary shadow">
            <CardHeader className="bg-white border-0">
              <h3 className="mb-0">Availability</h3>
            </CardHeader>
            <CardBody>
              <Table responsive>
                <thead className="thead-light">
                  <tr>
                    <th>Date</th>
                    <th>Timeslots</th>
                  </tr>
                </thead>
                <tbody>
                  {doctorData.availability.map((slot, index) => (
                    <tr key={index}>
                      <td>{slot.date}</td>
                      <td>
                        {slot.timeslots.map((timeslot, i) => (
                          <span key={i}>
                            {timeslot.fromTime} - {timeslot.toTime}
                            {i < slot.timeslots.length - 1 ? ", " : ""}
                          </span>
                        ))}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default DoctorProfile;
