import React, { useState } from "react";
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  FormGroup,
  Form,
  Input,
  InputGroupAddon,
  InputGroupText,
  InputGroup,
  Row,
  Col,
  Label,
  CustomInput,
} from "reactstrap";

const RegisterDoctor = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [gender, setGender] = useState("");
  const [specialization, setSpecialization] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [nid, setNid] = useState("");
  const [residence, setResidence] = useState("");
  const [bio, setBio] = useState("");
  const [qualifications, setQualifications] = useState([
    { name: "", institution: "", year: "" },
  ]);
  const [certifications, setCertifications] = useState([
    { name: "", issuingOrganization: "", expirationDate: "" },
  ]);

  const handleAddQualification = () => {
    setQualifications([
      ...qualifications,
      { name: "", institution: "", year: "" },
    ]);
  };

  const handleAddCertification = () => {
    setCertifications([
      ...certifications,
      { name: "", issuingOrganization: "", expirationDate: "" },
    ]);
  };

  const handleRegister = () => {
    // Perform data validation here
    // Prepare the JSON object with the entered data
    const doctorData = {
      firstName,
      lastName,
      email,
      password,
      phoneNumber,
      gender,
      specialization,
      dateOfBirth,
      nid,
      residence,
      bio,
      qualifications,
      certifications,
      // Add other fields as needed
    };
    console.log(JSON.stringify(doctorData, null, 2)); // Print the JSON to the console
  };

  return (
    <>
      <Col lg="6" md="8">
        <Card className="bg-secondary shadow border-0">
          <CardBody className="px-lg-5 py-lg-5">
            <div className="text-center text-muted mb-4">
              <h3 style={{ textTransform: "uppercase" }}>
                Register as a new doctor
              </h3>
            </div>
            <Form role="form">
              <Row>
                <Col md="6">
                  <FormGroup>
                    <InputGroup className="input-group-alternative">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="ni ni-circle-08" />
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="First name"
                        type="text"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                      />
                    </InputGroup>
                  </FormGroup>
                </Col>
                <Col md="6">
                  <FormGroup>
                    <InputGroup className="input-group-alternative">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="ni ni-circle-08" />
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="Last name"
                        type="text"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                      />
                    </InputGroup>
                  </FormGroup>
                </Col>
              </Row>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-email-83" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Email"
                    type="email"
                    autoComplete="new-email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-lock-circle-open" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Password"
                    type="password"
                    autoComplete="new-password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-mobile-button" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Phone Number"
                    type="tel"
                    value={phoneNumber}
                    onChange={(e) => setPhoneNumber(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <Label for="gender">Gender</Label>
                <CustomInput
                  type="select"
                  id="gender"
                  value={gender}
                  onChange={(e) => setGender(e.target.value)}
                >
                  <option value="">Select</option>
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                  <option value="other">Other</option>
                </CustomInput>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-settings" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Specialization"
                    type="text"
                    value={specialization}
                    onChange={(e) => setSpecialization(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-calendar-grid-58" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Date of Birth"
                    type="text"
                    value={dateOfBirth}
                    onChange={(e) => setDateOfBirth(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-badge" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="NID"
                    type="text"
                    value={nid}
                    onChange={(e) => setNid(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-square-pin" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Residence"
                    type="text"
                    value={residence}
                    onChange={(e) => setResidence(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-align-left-2" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Bio"
                    type="text"
                    value={bio}
                    onChange={(e) => setBio(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <div>
                <h5>Qualifications</h5>
                {qualifications.map((qualification, index) => (
                  <div key={index}>
                    <InputGroup className="input-group-alternative">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="ni ni-hat-3" />
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        className="mb-5"
                        placeholder="Name"
                        type="text"
                        value={qualification.name}
                        onChange={(e) => {
                          const updatedQualifications = [...qualifications];
                          updatedQualifications[index].name = e.target.value;
                          setQualifications(updatedQualifications);
                        }}
                      />
                    </InputGroup>
                    <Row>
                      <Col md="8">
                        <InputGroup className="input-group-alternative">
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="ni ni-building" />
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input
                            placeholder="Institution"
                            type="text"
                            value={qualification.institution}
                            onChange={(e) => {
                              const updatedQualifications = [...qualifications];
                              updatedQualifications[index].institution =
                                e.target.value;
                              setQualifications(updatedQualifications);
                            }}
                          />
                        </InputGroup>
                      </Col>
                      <Col md="4">
                        <InputGroup className="input-group-alternative">
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="ni ni-calendar-grid-58" />
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input
                            placeholder="Year"
                            type="text"
                            value={qualification.year}
                            onChange={(e) => {
                              const updatedQualifications = [...qualifications];
                              updatedQualifications[index].year =
                                e.target.value;
                              setQualifications(updatedQualifications);
                            }}
                          />
                        </InputGroup>
                      </Col>
                    </Row>
                    <br></br>
                  </div>
                ))}
                <Button
                  color="primary"
                  type="button"
                  onClick={handleAddQualification}
                  outline
                >
                  <i className="ni ni-fat-add" /> Add more qualification
                </Button>
                <br></br>
                <br></br>
              </div>
              <div>
                <h5>Certifications</h5>
                {certifications.map((certification, index) => (
                  <div key={index}>
                    <InputGroup className="input-group-alternative">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="ni ni-badge" />
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        className="mb-1"
                        placeholder="Name"
                        type="text"
                        value={certification.name}
                        onChange={(e) => {
                          const updatedCertifications = [...certifications];
                          updatedCertifications[index].name = e.target.value;
                          setCertifications(updatedCertifications);
                        }}
                      />
                    </InputGroup>
                    <Row>
                      <Col md="8">
                        <InputGroup className="input-group-alternative">
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="ni ni-building" />
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input
                            placeholder="Issuing Organization"
                            type="text"
                            value={certification.issuingOrganization}
                            onChange={(e) => {
                              const updatedCertifications = [...certifications];
                              updatedCertifications[index].issuingOrganization =
                                e.target.value;
                              setCertifications(updatedCertifications);
                            }}
                          />
                        </InputGroup>
                      </Col>
                      <Col md="4">
                        <InputGroup className="input-group-alternative">
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="ni ni-calendar-grid-58" />
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input
                            placeholder="Expiration Date"
                            type="text"
                            value={certification.expirationDate}
                            onChange={(e) => {
                              const updatedCertifications = [...certifications];
                              updatedCertifications[index].expirationDate =
                                e.target.value;
                              setCertifications(updatedCertifications);
                            }}
                          />
                        </InputGroup>
                      </Col>
                    </Row>
                    <br></br>
                  </div>
                ))}
                <Button
                  color="primary"
                  type="button"
                  onClick={handleAddCertification}
                  outline
                >
                  <i className="ni ni-fat-add" /> Add more certification
                </Button>
              </div>

              <br></br>
              <div className="text-center">
                <Button
                  className="mb-3"
                  color="primary"
                  type="button"
                  onClick={handleRegister}
                >
                  Create account
                </Button>
              </div>
            </Form>
          </CardBody>
        </Card>
      </Col>
    </>
  );
};

export default RegisterDoctor;
