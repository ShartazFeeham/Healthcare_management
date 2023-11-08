import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Col, Row } from "reactstrap";

const StatusItem = ({ status }) => {
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
      <div style={{ margin: "10px" }}>
        <Link to={link} style={{ fontWeight: "bold" }}>
          {status.authorName}
        </Link>
      </div>
    );
  };

  function getReaction(r) {
    // Like default
    let icon = "fas fa-thumbs-up";
    let color = "#4287f5";

    // Dislike
    if (r == 2) {
      icon = "fa fa-thumbs-down";
      color = "#f542ec";
    }

    // Love
    if (r == 3) {
      icon = "fa-solid fa-heart";
      color = "#dd0202";
    }

    // Haha
    if (r == 4) {
      icon = "fa-solid fa-face-grin-squint";
      color = "#18a103";
    }

    // Wow
    if (r == 5) {
      icon = "fa-solid fa-face-surprise";
      color = "#5c03a1";
    }

    // Sad
    if (r == 6) {
      icon = "fa-regular fa-face-sad-tear";
      color = "#111";
    }

    // Angry
    if (r == 7) {
      icon = "fas fa-angry";
      color = "#ff0000";
    }

    return <i className={icon} style={{ color: color, margin: "4px" }}></i>;
  }

  const [showReactions, setShowReactions] = useState(false);

  const toggleReactions = () => {
    setShowReactions(!showReactions);
  };

  const reactPost = (r) => {
    console.log("Post id: " + status.postId + " reaction: " + r);
    setShowReactions(false);
  };

  const renderReactions = () => {
    if (showReactions) {
      return (
        <div
          style={{
            position: "absolute",
            backgroundColor: "white",
            boxShadow: "0 0 10px rgba(0, 0, 0, 0.2)",
            zIndex: 1,
            display: "flex", // Display the reactions horizontally
            alignItems: "center", // Center the reactions vertically
          }}
        >
          {Array.from({ length: 7 }, (_, i) => (
            <div
              key={i}
              style={{
                cursor: "pointer",
                marginRight: "5px",
                fontSize: "large",
              }}
              onClick={(e) => {
                reactPost(i);
              }}
            >
              {getReaction(i + 1)}
            </div>
          ))}
        </div>
      );
    }
    return null;
  };

  if (status !== null) {
    return (
      <div
        style={{
          border: "1px solid #ddf",
          margin: "5px",
          padding: "5px",
          borderRadius: "5px",
          boxShadow: "rgba(149, 157, 165, 0.2) 0px 8px 24px",
          backgroundColor: "white",
        }}
      >
        {getUser(status.authorId)}
        {status.photo && (
          <img
            src={status.photo}
            alt="Status Photo"
            style={{
              maxWidth: "98%",
              display: "block",
              margin: "0 auto",
              maxHeight: "600px",
              borderRadius: "5px",
            }}
          />
        )}
        <div
          style={{
            padding: "10px",
            borderBottom: "1px solid #eee",
          }}
        >
          {status.content > 100 ? (
            <>
              {status.content.substring(0, 100)}... <span>See more</span>
            </>
          ) : (
            status.content
          )}
          <div style={{ marginTop: "10px" }}>{status.date}</div>
        </div>
        <div style={{ marginLeft: "10px" }}></div>
        <Row
          style={{
            borderBottom: "1px solid #eee",
            marginLeft: "5px",
            marginRight: "5px",
          }}
        >
          <Col style={{}}>
            <div>
              {status.reactions.map((r) => getReaction(r))}
              {status.reactionsCount === 0
                ? "No reactions yet"
                : status.reactionsCount + " reactions"}
            </div>
          </Col>
          <Col style={{ textAlign: "right" }}>
            <div>
              {status.commentsCount === 0
                ? "No comments yet"
                : status.commentsCount + " comments"}
            </div>
          </Col>
        </Row>
        <Row style={{ justifyContent: "center", textAlign: "center" }}>
          <Col
            style={{
              padding: "10px",
              fontWeight: "bold",
              borderRight: "1px solid #eee",
              cursor: "pointer",
            }}
            onMouseEnter={toggleReactions}
            onMouseLeave={toggleReactions}
          >
            Like
            {showReactions && renderReactions()}
          </Col>
          <Col
            style={{ padding: "10px", fontWeight: "bold", cursor: "pointer" }}
          >
            Comment
          </Col>
        </Row>
      </div>
    );
  } else {
    return <div>Loading...</div>;
  }
};

export default StatusItem;
