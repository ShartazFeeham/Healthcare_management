import React, { useState, useEffect } from "react";
import {
  Container,
  Row,
  Col,
  CardHeader,
  CardBody,
  Card,
  Input,
  FormGroup,
  Label,
  Button,
  Table,
  Pagination,
  PaginationItem,
  PaginationLink,
  InputGroup,
  InputGroupAddon,
  InputGroupText,
} from "reactstrap";
import { medicinesList } from "assets/data/medicine/medInfoList";
import { Link } from "react-router-dom";

const UpdateDisease = () => {
  const [medicines, setMedicines] = useState(medicinesList.data);
  const [totalItems] = useState(medicinesList.size);
  const [currentPage, setCurrentPage] = useState(1);
  const [filterPath, setFilterPath] = useState("");

  const [search, setSearch] = useState("");
  const [sortType, setSortType] = useState("none");
  const [expirationFilter, setExpirationFilter] = useState("all");
  const [manufacturerFilter, setManufacturerFilter] = useState("");

  const itemsPerPage = 10;

  useEffect(() => {
    const path = `medicines/filter?sort=${sortType}&expiration=${expirationFilter}&manufacturer=${
      manufacturerFilter || "null"
    }`;
    setFilterPath(path);
  }, [sortType, expirationFilter, manufacturerFilter]);

  const handleFilter = () => {
    console.log(filterPath);
  };

  const handleSearch = (e) => {
    if (e.key !== "Enter") {
      return;
    }
    console.log("Searching...");
  };

  const rbcss = {
    boxShadow: "0 0 5px rgba(0, 0, 0, 0.2)",
    padding: "5px",
    alignItems: "center",
    marginLeft: "10px",
    marginRight: "20px",
    borderRadius: "5px",
    cursor: "pointer",
    color: "white",
  };

  return (
    <Container>
      <Row>
        <Col>
          <Card
            style={{
              color: "white",
              backgroundColor: "#111144",
              borderRadius: "20px",
            }}
          >
            <CardHeader
              style={{
                backgroundColor: "#111144",
                borderRadius: "20px",
              }}
            >
              <h2 style={{ color: "white" }}>Medicine List</h2>
            </CardHeader>
            <CardBody>
              <Row className="mb-3 justify-content-center">
                <Col sm={8}>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend">
                      <InputGroupText>
                        <i
                          className="fa fa-search"
                          style={{
                            color: "black",
                            fontWeight: "bold",
                            fontSize: "large",
                          }}
                        />
                      </InputGroupText>
                    </InputGroupAddon>
                    <Input
                      type="text"
                      placeholder="Search in medicines"
                      style={{
                        color: "#444",
                        fontWeight: "bold",
                      }}
                      onChange={(e) => setSearch(e.target.value)}
                      value={search}
                      onKeyDown={handleSearch}
                    />
                  </InputGroup>
                </Col>
              </Row>
              <FormGroup
                style={{
                  display: "flex",
                  justifyContent: "center",
                }}
              >
                <div
                  style={{
                    padding: "5px",
                    alignItems: "center",
                    marginLeft: "10px",
                    marginRight: "20px",
                    borderRadius: "5px",
                    cursor: "pointer",
                    fontWeight: "bold",
                  }}
                >
                  Sort
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="sortType"
                      value="none"
                      onChange={() => setSortType("none")}
                      checked={sortType === "none"}
                    />
                    None
                  </Label>
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="sortType"
                      value="medicineName"
                      onChange={() => setSortType("medicineName")}
                      checked={sortType === "medicineName"}
                    />
                    Medicine Name
                  </Label>
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="sortType"
                      value="dosageForm"
                      onChange={() => setSortType("dosageForm")}
                      checked={sortType === "dosageForm"}
                    />
                    Dosage Form
                  </Label>
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="sortType"
                      value="expirationDate"
                      onChange={() => setSortType("expirationDate")}
                      checked={sortType === "expirationDate"}
                    />
                    Expiration Date
                  </Label>
                </div>
              </FormGroup>
              <FormGroup style={{ display: "flex", justifyContent: "center" }}>
                <div
                  style={{
                    padding: "5px",
                    alignItems: "center",
                    marginLeft: "10px",
                    marginRight: "20px",
                    borderRadius: "5px",
                    cursor: "pointer",
                    color: "white",
                    fontWeight: "bold",
                  }}
                >
                  Filter
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="expirationFilter"
                      value="all"
                      onChange={() => setExpirationFilter("all")}
                      checked={expirationFilter === "all"}
                    />
                    All
                  </Label>
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="expirationFilter"
                      value="expired"
                      onChange={() => setExpirationFilter("expired")}
                      checked={expirationFilter === "expired"}
                    />
                    Expired
                  </Label>
                </div>
                <div style={rbcss}>
                  <Label check>
                    <Input
                      type="radio"
                      name="expirationFilter"
                      value="non-expired"
                      onChange={() => setExpirationFilter("non-expired")}
                      checked={expirationFilter === "non-expired"}
                    />
                    Non-Expired
                  </Label>
                </div>
              </FormGroup>
              <Row className="mb-3 justify-content-center">
                <Col sm={8}>
                  <Input
                    style={{ color: "black" }}
                    type="text"
                    placeholder="Write a manufacturer name"
                    onChange={(e) => setManufacturerFilter(e.target.value)}
                    value={manufacturerFilter}
                  />
                </Col>
              </Row>
              <div
                style={{
                  width: "100%",
                  textAlign: "center",
                  marginBottom: "15px",
                }}
              >
                <Button color="primary" onClick={handleFilter}>
                  Filter
                </Button>
              </div>
              <Table striped responsive>
                <tbody style={{ color: "white" }}>
                  <tr>
                    <th>Med ID</th>
                    <th>C. Name</th>
                    <th>M. Name</th>
                    <th>Type</th>
                    <th>Strength</th>
                    <th>Manufacturer</th>
                    <th>Exp. date</th>
                  </tr>
                  {medicines
                    .slice(
                      (currentPage - 1) * itemsPerPage,
                      currentPage * itemsPerPage
                    )
                    .map((medicine) => (
                      <tr key={medicine.medicineId}>
                        <td>
                          <i class="fa-solid fa-capsules"></i>{" "}
                          <Link
                            to={"/common/medicines/" + medicine.medicineId}
                            style={{ color: "white", fontWeight: "bold" }}
                          >
                            {medicine.medicineId}
                          </Link>
                        </td>
                        <td>{medicine.commercialName}</td>
                        <td>{medicine.medicineName}</td>
                        <td>{medicine.dosageForm}</td>
                        <td>{medicine.strengthVolume}</td>
                        <td>{medicine.manufacturer}</td>
                        <td>{medicine.expirationDate}</td>
                      </tr>
                    ))}
                </tbody>
              </Table>
              <Pagination>
                <PaginationItem disabled={currentPage === 1}>
                  <PaginationLink
                    previous
                    onClick={() => setCurrentPage(currentPage - 1)}
                  />
                </PaginationItem>
                {Array.from(
                  { length: Math.ceil(totalItems / itemsPerPage) },
                  (_, i) => (
                    <PaginationItem key={i} active={i + 1 === currentPage}>
                      <PaginationLink onClick={() => setCurrentPage(i + 1)}>
                        {i + 1}
                      </PaginationLink>
                    </PaginationItem>
                  )
                )}
                <PaginationItem
                  disabled={
                    currentPage === Math.ceil(totalItems / itemsPerPage)
                  }
                >
                  <PaginationLink
                    next
                    onClick={() => setCurrentPage(currentPage + 1)}
                  />
                </PaginationItem>
              </Pagination>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default UpdateDisease;
