import { useState } from "react";
import {
  Button,
  Col,
  Container,
  CustomInput,
  FormGroup,
  Input,
  InputGroup,
  InputGroupAddon,
  InputGroupText,
  Row,
} from "reactstrap";

const Post = () => {
  const [type, setType] = useState("status");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [photo, setPhoto] = useState("");
  const [alert, setAlert] = useState(false);
  const textColor = { color: "#555" };

  const sharePost = () => {
    setAlert("");
    const status = {
      title,
      content,
      photo:
        photo === null || photo === undefined || photo === "" ? null : photo,
      type,
    };
    console.log(status);
  };

  return (
    <>
      <Container className="mb-4">
        {type !== "status" && type !== "feedback" && (
          <FormGroup>
            <InputGroup className="input-group-alternative mb-3">
              <InputGroupAddon addonType="prepend">
                <InputGroupText style={textColor}>
                  <i className="ni ni-ruler-pencil" style={textColor} />
                </InputGroupText>
              </InputGroupAddon>
              <Input
                placeholder={"Write a title for " + type}
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                style={textColor}
              />
            </InputGroup>
          </FormGroup>
        )}
        <FormGroup style={{ border: "1px solid #eee" }}>
          {photo && (
            <div style={{ textAlign: "center" }}>
              <img
                src={URL.createObjectURL(photo)}
                alt="Selected Photo"
                style={{
                  width: "150px",
                  height: "auto",
                  border: "1px solid #ccc",
                  padding: "2px",
                  marginBottom: "10px",
                  borderRadius: "20px",
                }}
              />
            </div>
          )}
          <InputGroup className="input-group-alternative">
            <InputGroupAddon addonType="prepend">
              <InputGroupText>
                <i className="ni ni-align-left-2" />
              </InputGroupText>
            </InputGroupAddon>
            <Input
              type="textarea"
              placeholder={"Write your " + type.toLowerCase() + " here."}
              value={content}
              onChange={(e) => setContent(e.target.value)}
              maxLength={300}
              style={{
                minHeight: "100px",
                ...textColor,
              }}
            />
          </InputGroup>
        </FormGroup>
        <Row className="mb-3">
          <Col>
            <FormGroup>
              <InputGroup className="input-group-alternative">
                <InputGroupAddon addonType="prepend">
                  <InputGroupText>
                    <i className="ni ni-image" style={textColor} />
                  </InputGroupText>
                </InputGroupAddon>
                <Input
                  type="file"
                  id="photo"
                  accept="image/*"
                  onChange={(e) => setPhoto(e.target.files[0])}
                  style={{ display: "none" }}
                />

                <label
                  htmlFor="photo"
                  style={{
                    display: "inline-block",
                    padding: "6px 12px",
                    cursor: "pointer",
                    backgroundColor: "#f9f9f9",
                    borderRadius: "4px",
                    transition: "background-color 0.3s",
                  }}
                >
                  {photo ? "Change picture" : "Select a photo"}
                </label>
                <div
                  className="file-name"
                  style={{
                    marginLeft: "5px",
                    marginTop: "5px",
                    color: "#555",
                    fontSize: "14px",
                  }}
                ></div>
              </InputGroup>
            </FormGroup>
          </Col>
          <Col>
            <InputGroup className="input-group-alternative">
              <InputGroupAddon addonType="prepend">
                <InputGroupText>
                  <i className="ni ni-bold-down" style={textColor} />
                </InputGroupText>
              </InputGroupAddon>
              <CustomInput
                type="select"
                id="gender"
                value={type}
                onChange={(e) => setType(e.target.value)}
                style={textColor}
              >
                <option value="status">Status</option>
                <option value="feedback">Feedback</option>
                <option value="article">Article</option>
                <option value="firstaid">First Aid</option>
                <option value="faq">FAQ</option>
              </CustomInput>
            </InputGroup>
          </Col>
        </Row>
        {alert && (
          <div className="alert alert-danger">You must wirte a title</div>
        )}
        <Button style={{ width: "100%" }} color="primary" onClick={sharePost}>
          Post your {type}
        </Button>
      </Container>
    </>
  );
};
export default Post;
