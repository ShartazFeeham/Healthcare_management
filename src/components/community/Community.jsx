import { Col, Container, Row } from "reactstrap";

const Community = () => {
  return (
    <>
      <>
        <div
          className="header pb-5 pt-5 pt-lg-8 d-flex align-items-center"
          style={{
            height: "100px",
            backgroundImage:
              "url(" + require("../../assets/img/cover/health5.jpg") + ")",
            backgroundSize: "cover",
            backgroundPosition: "center top",
          }}
        >
          <span className="mask bg-gradient-default opacity-8" />
          <Container className="d-flex align-items-center" fluid>
            <Row>
              <h1 className="display-2 opacity-7" style={{ color: "white" }}>
                Community
              </h1>
            </Row>
          </Container>
        </div>
      </>
      <Container className="mt--7" fluid></Container>
    </>
  );
};

export default Community;
