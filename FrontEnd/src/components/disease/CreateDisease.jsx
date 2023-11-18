import React, { useState } from "react";
import { Link } from "react-router-dom";
import {
  Container,
  Row,
  Col,
  CardHeader,
  CardBody,
  Card,
  Form,
  FormGroup,
  Input,
  InputGroup,
  InputGroupAddon,
  InputGroupText,
  Button,
  CustomInput,
  Modal,
  Label,
} from "reactstrap";
import specializationList from "assets/data/enums/specializations";

const CreateDisease = () => {
  const [name, setName] = useState("");
  const [alertMessage, setAlertMessage] = useState("");
  const [prevention, setPrevention] = useState("");
  const [diagnoses, setDiagnoses] = useState("");
  const [treatment, setTreatment] = useState("");
  const [docSpec, setDocSpec] = useState("");
  const [symptoms, setSymptoms] = useState([""]);
  const textColor = { color: "#555" };

  const handleARemoveQualification = () => {
    if (symptoms.length > 0) {
      const updatedSymptoms = symptoms.slice(0, symptoms.length - 1);
      setSymptoms(updatedSymptoms);
    }
  };

  const handleAddQualification = () => {
    setSymptoms([...symptoms, ""]);
  };

  const validate = () => {};

  const handleCreateDisease = () => {
    return 5;
  };

  return (
    <>
      <div
        className="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
        style={{
          minHeight: "150px",
          backgroundImage:
            "url(" + require("../../assets/img/cover/health1.jpg") + ")",
          backgroundSize: "cover",
          backgroundPosition: "center top",
        }}
      >
        <span className="mask bg-gradient-default opacity-6" />
      </div>
      <Container className="mt--7" fluid>
        <Row>
          <Col className="order-xl-1 center" xl="8">
            <Card className="bg-secondary shadow">
              <CardHeader className="bg-white border-0">
                <Row className="align-items-center">
                  <Col xs="8">
                    <h3 className="mb-0">
                      <b>ADD A NEW DISEASE INFO</b>
                    </h3>
                    <Link to={"/health/medicines"}>
                      <b>Go back to disease list</b>
                    </Link>
                  </Col>
                </Row>
              </CardHeader>
              <CardBody>
                <Form>
                  <h6 className="heading-small text-muted mb-4">
                    Disease Info
                  </h6>
                  {alertMessage && (
                    <div className="alert alert-danger" role="alert">
                      {alertMessage}
                    </div>
                  )}
                  <FormGroup row>
                    <Label sm={4} for="commercialName">
                      Disease name:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-medkit" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          style={{ color: "#555" }}
                          type="text"
                          id="name"
                          value={name}
                          onChange={(e) => setName(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="description">
                      Preventions:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-info" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          style={{ color: "#555" }}
                          type="textarea"
                          id="description"
                          value={prevention}
                          onChange={(e) => setPrevention(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="description">
                      Diagnoses:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-info" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          style={{ color: "#555" }}
                          type="textarea"
                          id="description"
                          value={diagnoses}
                          onChange={(e) => setDiagnoses(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="description">
                      Treatments:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-info" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          style={{ color: "#555" }}
                          type="textarea"
                          id="description"
                          value={treatment}
                          onChange={(e) => setTreatment(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="description">
                      Doctor specialization:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-info" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <CustomInput
                          style={{ color: "#555" }}
                          type="select"
                          id="specialization"
                          value={docSpec}
                          onChange={(e) => setDocSpec(e.target.value)}
                        >
                          <option value="" disabled>
                            Select Doctor Specialization
                          </option>
                          {specializationList.map((spec, index) => (
                            <option key={index} value={spec}>
                              {spec}
                            </option>
                          ))}
                        </CustomInput>
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="">
                      Symptopms:
                    </Label>
                  </FormGroup>

                  <div>
                    {symptoms.map((sym, index) => (
                      <div key={index}>
                        <InputGroup className="input-group-alternative mb-1">
                          <InputGroupAddon addonType="prepend">
                            <InputGroupText>
                              <i className="ni ni-hat-3" style={textColor} />
                            </InputGroupText>
                          </InputGroupAddon>
                          <Input
                            placeholder="Type a symptom"
                            type="text"
                            value={sym}
                            onChange={(e) => {
                              const updatedSymptoms = [...symptoms];
                              updatedSymptoms[index] = e.target.value;
                              setSymptoms(updatedSymptoms);
                            }}
                            style={textColor}
                          />
                        </InputGroup>

                        <br></br>
                      </div>
                    ))}
                    <Row>
                      <Col>
                        <Button
                          color="primary"
                          type="button"
                          onClick={handleAddQualification}
                          outline
                        >
                          <i className="ni ni-fat-add" /> Add more symptom
                        </Button>
                      </Col>
                      <Col>
                        {symptoms.length > 1 && (
                          <Button
                            color="warning"
                            type="button"
                            onClick={handleARemoveQualification}
                            outline
                          >
                            <i className="ni ni-fat-remove" /> Remove last
                            symptom
                          </Button>
                        )}
                      </Col>
                    </Row>

                    <br></br>
                    <br></br>
                  </div>

                  <Button color="primary" onClick={handleCreateDisease}>
                    Create Disease
                  </Button>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default CreateDisease;
