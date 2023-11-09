import { useEffect, useState } from "react";
import { statusData } from "assets/data/community/status";
import { Row } from "reactstrap";
import FaqItem from "./FaqItem";

const FAQ = () => {
  const [status, setStatus] = useState([]);
  const [total, setTotal] = useState(0);
  const [page, setPage] = useState([]);

  useEffect(() => {
    setTotal(10);
    setPage(0);
  }, []);

  useEffect(() => {
    let data = [...status];
    for (let i = 0; i < 5 && page * 5 + i < statusData.length; i++) {
      data[page * 5 + i] = statusData[page * 5 + i];
    }
    setStatus(data);
  }, [page]);

  return (
    <>
      {status.map((st) => {
        return <FaqItem st={st} />;
      })}
      {page * 5 + 5 >= total ? (
        <Row
          style={{
            justifyContent: "center",
            margin: "5px",
            marginTop: "10px",
            padding: "10px",
            fontWeight: "bold",
            backgroundColor: "#eee",
          }}
          onClick={() => {
            setPage(page + 1);
          }}
        >
          There are no FAQ's remaining.
        </Row>
      ) : (
        <Row
          style={{
            justifyContent: "center",
            margin: "5px",
            marginTop: "10px",
            padding: "10px",
            fontWeight: "bold",
            boxShadow: "rgba(99, 99, 99, 0.2) 0px 2px 8px 0px",
            cursor: "pointer",
          }}
          onClick={() => {
            setPage(page + 1);
          }}
        >
          Load more FAQ's
        </Row>
      )}
    </>
  );
};
export default FAQ;
