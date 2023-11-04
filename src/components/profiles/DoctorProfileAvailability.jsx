import React, { useState } from "react";
import { Button, Col, Row, Table } from "reactstrap";

const DoctorProfileAvailability = ({ availability }) => {
  const [onsite, setOnsite] = useState(false);
  const [cut, setCut] = useState(true);

  const tableData = (data) => {
    if (cut)
      return data.map(
        (item, index) =>
          index < 3 && (
            <tr key={index}>
              <td>{item.date}</td>
              <td>
                <ul>
                  {item.timeslots.map((timeslot, i) => (
                    <li key={i}>
                      {timeslot.fromTime} - {timeslot.toTime}
                    </li>
                  ))}
                </ul>
              </td>
            </tr>
          )
      );
    else
      return data.map((item, index) => (
        <tr key={index}>
          <td>{item.date}</td>
          <td>
            <ul>
              {item.timeslots.map((timeslot, i) => (
                <li key={i}>
                  {timeslot.fromTime} - {timeslot.toTime}
                </li>
              ))}
            </ul>
          </td>
        </tr>
      ));
  };

  return (
    <div style={{ marginTop: "10px" }}>
      <h4>Availability</h4>
      <div className="d-flex justify-content-center align-items-center h-100">
        <div className="text-center">
          <Button
            color={onsite ? "primary" : "outline-primary"}
            className="m-2"
            onClick={() => setOnsite(true)}
          >
            On-site
          </Button>
          <Button
            color={onsite ? "outline-primary" : "primary"}
            className="m-2"
            onClick={() => setOnsite(false)}
          >
            Telemedicine
          </Button>
        </div>
      </div>
      {onsite ? (
        <>
          {availability.onsite.length > 0 ? (
            <div>
              <Table
                className="align-items-center table-flush"
                style={{ width: "100%" }}
              >
                <thead className="thead-light">
                  <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Timeslots</th>
                  </tr>
                </thead>
                <tbody>{tableData(availability.onsite)}</tbody>
              </Table>
              <div
                style={{
                  textAlign: "center",
                  margin: "10px",
                  boxShadow: "rgba(0, 0, 0, 0.16) 0px 1px 4px",
                  padding: "5px",
                  cursor: "pointer",
                }}
                onClick={() => setCut(!cut)}
              >
                {cut ? "Show all" : "Show less"}
              </div>
            </div>
          ) : (
            <div
              style={{
                margin: "5px",
                padding: "10px",
                border: "1px solid #eee",
                borderRadius: "5px",
              }}
            >
              Not available onsite
            </div>
          )}
        </>
      ) : (
        <>
          <>
            {availability.telemedicine.length > 0 ? (
              <div>
                <Table
                  className="align-items-center table-flush"
                  style={{ width: "100%" }}
                >
                  <thead className="thead-light">
                    <tr>
                      <th scope="col">Date</th>
                      <th scope="col">Timeslots</th>
                    </tr>
                  </thead>
                  <tbody>{tableData(availability.telemedicine)}</tbody>
                </Table>
                <div
                  style={{
                    textAlign: "center",
                    margin: "10px",
                    boxShadow: "rgba(0, 0, 0, 0.16) 0px 1px 4px",
                    padding: "5px",
                    cursor: "pointer",
                  }}
                  onClick={() => setCut(!cut)}
                >
                  {cut ? "Show all" : "Show less"}
                </div>
              </div>
            ) : (
              <div
                style={{
                  margin: "5px",
                  padding: "10px",
                  border: "1px solid #eee",
                  borderRadius: "5px",
                }}
              >
                Not available for telemedicine
              </div>
            )}
          </>
        </>
      )}
    </div>
  );
};

export default DoctorProfileAvailability;
