import React, { useState } from "react";
import { Container, Row, Col, CardHeader, CardBody, Card } from "reactstrap";

const MedicineList = () => {
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
                      <b>MEDICINE LIST</b>
                    </h3>
                  </Col>
                </Row>
              </CardHeader>
              <CardBody></CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default MedicineList;
