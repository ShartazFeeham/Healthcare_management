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

const CreateMedicine = () => {
  const [commercialName, setCommercialName] = useState("");
  const [medicineName, setMedicineName] = useState("");
  const [classification, setClassification] = useState("");
  const [description, setDescription] = useState("");
  const [dosageForm, setDosageForm] = useState("");
  const [strengthVolume, setStrengthVolume] = useState("");
  const [strengthWeight, setStrengthWeight] = useState("");
  const [warnings, setWarnings] = useState("");
  const [adverseEffects, setAdverseEffects] = useState("");
  const [manufacturer, setManufacturer] = useState("");
  const [nationalDrugCode, setNationalDrugCode] = useState("");
  const [expirationDate, setExpirationDate] = useState("");
  const [photo, setPhoto] = useState(null);
  const [alertMessage, setAlertMessage] = useState("");

  const handlePhotoUpload = (e) => {
    const file = e.target.files[0];
    setPhoto(file);
  };

  const validate = () => {
    if (
      !commercialName ||
      !medicineName ||
      !classification ||
      !description ||
      !dosageForm ||
      !strengthVolume ||
      !strengthWeight ||
      !warnings ||
      !adverseEffects ||
      !manufacturer ||
      !nationalDrugCode ||
      !expirationDate ||
      !photo
    ) {
      setAlertMessage("Please fill in all fields.");
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      });
      return false;
    }
    setAlertMessage(null);
    return true;
  };

  const handleCreateMedicine = () => {
    if (validate()) {
      const medicineData = {
        commercialName,
        medicineName,
        classification,
        description,
        dosageForm,
        strengthVolume,
        strengthWeight,
        warnings,
        adverseEffects,
        manufacturer,
        nationalDrugCode,
        expirationDate,
        photo,
      };
      console.log("Medicine Data:", medicineData);
    }
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
                      <b>CREATE A NEW MEDICINE</b>
                    </h3>
                    <Link to={"/health/medicines"}>
                      <b>Go back to medicines list</b>
                    </Link>
                  </Col>
                </Row>
              </CardHeader>
              <CardBody>
                <Form>
                  <h6 className="heading-small text-muted mb-4">
                    Medicine Info
                  </h6>
                  {alertMessage && (
                    <div className="alert alert-danger" role="alert">
                      {alertMessage}
                    </div>
                  )}
                  <FormGroup row>
                    <Label sm={4} for="commercialName">
                      Commercial name:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-medkit" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="commercialName"
                          value={commercialName}
                          onChange={(e) => setCommercialName(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="medicineName">
                      Medicine name:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-prescription-bottle" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="medicineName"
                          value={medicineName}
                          onChange={(e) => setMedicineName(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="classification">
                      Classification:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-tags" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="classification"
                          value={classification}
                          onChange={(e) => setClassification(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="description">
                      Description:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-info" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="textarea"
                          id="description"
                          value={description}
                          onChange={(e) => setDescription(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4}>Dosage Form:</Label>
                    <Col sm={8}>
                      <div className="d-flex justify-content-between align-items-center">
                        <CustomInput
                          type="radio"
                          id="tablet"
                          label="Tablet"
                          checked={dosageForm === "tablet"}
                          onChange={() => setDosageForm("tablet")}
                        />
                        <CustomInput
                          type="radio"
                          id="capsule"
                          label="Capsule"
                          checked={dosageForm === "capsule"}
                          onChange={() => setDosageForm("capsule")}
                        />
                        <CustomInput
                          type="radio"
                          id="syrup"
                          label="Syrup"
                          checked={dosageForm === "syrup"}
                          onChange={() => setDosageForm("syrup")}
                        />
                      </div>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="strengthVolume">
                      Strength in volume:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-flask" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="strengthVolume"
                          value={strengthVolume}
                          onChange={(e) => setStrengthVolume(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="strengthWeight">
                      Strength in weight:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-balance-scale" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="strengthWeight"
                          value={strengthWeight}
                          onChange={(e) => setStrengthWeight(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="warnings">
                      Warnings:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-exclamation-triangle" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="textarea"
                          id="warnings"
                          value={warnings}
                          onChange={(e) => setWarnings(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="adverseEffects">
                      Adverse effects:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-thumbs-down" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="textarea"
                          id="adverseEffects"
                          value={adverseEffects}
                          onChange={(e) => setAdverseEffects(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="manufacturer">
                      Manufacturer:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-industry" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="manufacturer"
                          value={manufacturer}
                          onChange={(e) => setManufacturer(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="nationalDrugCode">
                      National drug code:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-barcode" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="text"
                          id="nationalDrugCode"
                          value={nationalDrugCode}
                          onChange={(e) => setNationalDrugCode(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="expirationDate">
                      Expiration date:
                    </Label>
                    <Col sm={8}>
                      <InputGroup>
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="fa fa-calendar" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="date"
                          id="expirationDate"
                          value={expirationDate}
                          onChange={(e) => setExpirationDate(e.target.value)}
                        />
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Label sm={4} for="photo">
                      Medicine Photo:
                    </Label>
                    <Col sm={8}>
                      {photo ? (
                        <div style={{ textAlign: "center" }}>
                          <img
                            src={URL.createObjectURL(photo)}
                            alt="Selected Photo"
                            style={{
                              width: "150px",
                              height: "auto",
                              border: "1px solid #ccc",
                              padding: "2px",
                              marginBottom: "10px",
                              borderRadius: "20px",
                            }}
                          />
                        </div>
                      ) : null}
                      <InputGroup className="input-group-alternative">
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="ni ni-image" />
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="file"
                          id="photo"
                          accept="image/*"
                          onChange={handlePhotoUpload}
                          style={{ display: "none" }}
                        />

                        <label
                          htmlFor="photo"
                          style={{
                            border: "1px solid #ccc",
                            display: "inline-block",
                            padding: "6px 12px",
                            cursor: "pointer",
                            backgroundColor: "#f9f9f9",
                            borderRadius: "4px",
                            transition: "background-color 0.3s",
                          }}
                        >
                          {photo ? "Change Photo" : "Upload your photo"}
                        </label>
                        <div
                          className="file-name"
                          style={{
                            marginLeft: "5px",
                            marginTop: "5px",
                            color: "#555",
                            fontSize: "14px",
                          }}
                        ></div>
                      </InputGroup>
                    </Col>
                  </FormGroup>
                  <Button color="primary" onClick={handleCreateMedicine}>
                    Create Medicine
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

export default CreateMedicine;
