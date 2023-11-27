import { Button, Col, Row } from "reactstrap";

const Index = (props) => {
  return (
    <>
      <Row className="align-items-center">
        <Col>
          <img
            src={require("../../assets/img/home/patient2.png")}
            alt="Doctors"
          />
        </Col>
        <Col>
          <Row>
            <Col>
              <div>
                <h1
                  style={{
                    color: "white",
                    textShadow: "2px 2px 4px #888888",
                    borderBottom: "1px solid gray",
                  }}
                >
                  We care most about the patients happiness.
                </h1>
                <p
                  style={{ color: "white", textShadow: "2px 2px 4px #888888" }}
                >
                  Our team of highly skilled doctors, specializing in various
                  fields, is dedicated to providing exceptional care. With
                  expertise in specific medical domains, we ensure personalized
                  and effective treatments for our patients.
                </p>
              </div>
            </Col>
          </Row>
          <Row>
            <Col>
              <Col>
                <Button className="m-2">Browse doctors</Button>
                <Button className="m-2">Browse doctors</Button>
              </Col>
            </Col>
          </Row>
        </Col>
      </Row>
      <Row className="align-items-center">
        <Col>
          <Row>
            <Col>
              <div>
                <h1
                  style={{
                    color: "white",
                    textShadow: "2px 2px 4px #888888",
                    borderBottom: "1px solid gray",
                  }}
                >
                  Get treatment from our highly skilled doctors
                </h1>
                <p
                  style={{ color: "white", textShadow: "2px 2px 4px #888888" }}
                >
                  Our team of highly skilled doctors, specializing in various
                  fields, is dedicated to providing exceptional care. With
                  expertise in specific medical domains, we ensure personalized
                  and effective treatments for our patients.
                </p>
              </div>
            </Col>
          </Row>
          <Row>
            <Col>
              <Col>
                <Button className="m-2">Browse doctors</Button>
                <Button className="m-2">Browse doctors</Button>
              </Col>
            </Col>
          </Row>
        </Col>
        <Col>
          <img src={require("../../assets/img/home/docs1.png")} alt="Doctors" />
        </Col>
      </Row>
    </>
  );
};

export default Index;
