import React, { useState, useEffect } from "react";
import { Container, Row, Col, CardHeader, CardBody, Card } from "reactstrap";

const MedicineList = () => {
  return (
    <Container>
      <Row>
        <Col>
          <Card>
            <CardHeader>Medicine List</CardHeader>
            <CardBody>
              <div id="floating-container" className="floating-container"></div>
              <p>This is the foreground content. You can edit this later.</p>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default MedicineList;
