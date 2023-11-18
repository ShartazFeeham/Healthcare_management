import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Col, Row } from "reactstrap";
import "./article-items.css";

const ArticleItem = ({ st }) => {
  const [status, setStatus] = useState(st);
  const authorName = "Anonymous user";

  const getUser = (authorId) => {
    let link = "/health/";

    if (authorId[0] === "P") {
      link += "patients/" + authorId;
    } else if ((authorId[0] === "P") === "D") {
      link += "doctors/" + authorId;
    } else {
      link = "#";
    }

    return (
      <div className="user-info">
        <Link to={link} className="user-link">
          <i className="fa-solid fa-pen"></i> {status.authorName}
        </Link>
      </div>
    );
  };

  if (status !== null) {
    return (
      <div className="article-item-container">
        <Link to={"/health/posts/" + status.postId}>
          {status.photo ? (
            <img src={status.photo} alt="Status Photo" />
          ) : (
            <img
              src={process.env.PUBLIC_URL + "/community/postdefault.jpg"}
              alt="Alternative Photo"
              className="alt-photo"
            />
          )}
          <h1 className="article-title">{status.title}</h1>
        </Link>
        <Row>
          <Col>{getUser(status.authorId)}</Col>
          <Col className="article-info">
            <i className="fa-solid fa-clock"></i> {status.date}
          </Col>
        </Row>
      </div>
    );
  } else {
    return <div>Loading...</div>;
  }
};

export default ArticleItem;
