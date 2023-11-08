import { Card, CardHeader, CardBody, Row, Col, CardTitle } from "reactstrap";
import { useEffect, useState } from "react";
import patientDataAchievements from "assets/data/patientprofile/patientAchievements";

export const PatientProfileAchievements = ({ patientId }) => {
  const [patientData, setPatientData] = useState(null);
  useEffect(() => {
    setPatientData(patientDataAchievements);
  }, [patientData]);

  function getLevel(dif) {
    const lowerDif = dif.toLowerCase();

    if (lowerDif === "hard")
      return (
        <span className="text-danger mr-2">
          <i className="fa fa-arrow-up" /> {dif.toUpperCase()}
        </span>
      );
    else if (lowerDif === "moderate")
      return (
        <span className="text-info mr-2">
          <i className="fa fa-arrow-up" /> {dif.toUpperCase()}
        </span>
      );
    else
      return (
        <span className="text-success mr-2">
          <i className="fa fa-arrow-up" /> {dif.toUpperCase()}
        </span>
      );
  }

  return (
    <>
      {patientData && (
        <div>
          <Card className="card-profile shadow">
            <CardHeader className="text-center border-0 pt-md-4 pb-0 pb-md-1">
              <h2
                className="mb-0"
                style={{ margin: "5px", padding: "15px", fontWeight: "bold" }}
              >
                <b>Achievements</b>
              </h2>
            </CardHeader>
            <CardBody className="pt-0 pt-md-4">
              {patientData.achievements.map((achievement, index) => {
                return (
                  <Card className="card-stats mt-2 mb-xl-0">
                    <CardBody>
                      <Row>
                        <div className="col">
                          <CardTitle
                            tag="h5"
                            className="text-uppercase text mb-0"
                          >
                            <b>
                              <i className="ni ni-user-run text-success" />
                            </b>{" "}
                            Completed in <b>{achievement.completedIn}</b> days
                          </CardTitle>
                          <span className="h2 font-weight-bold mb-0">
                            {achievement.title}
                          </span>
                        </div>
                        <Col className="col-auto">
                          <a
                            className="avatar avatar-sm"
                            href="#pablo"
                            id="tooltip742438047"
                            onClick={(e) => e.preventDefault()}
                          >
                            <img
                              className="rounded-circle"
                              src={achievement.logoUrl}
                            />
                          </a>
                        </Col>
                      </Row>
                      <p className="mt-3 mb-0 text-muted text-sm">
                        {getLevel(achievement.difficulty)}{" "}
                        <span className="text-nowrap">
                          Achievement date: {achievement.completionDate}
                        </span>
                      </p>
                    </CardBody>
                  </Card>
                );
              })}
            </CardBody>
          </Card>
        </div>
      )}
    </>
  );
};
