import { Link } from "react-router-dom";
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  FormGroup,
  Form,
  Input,
  InputGroupAddon,
  InputGroupText,
  InputGroup,
  Row,
  Col,
} from "reactstrap";
import { useState } from "react";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [verified, setVerified] = useState(false);

  const handleLogin = () => {};

  return (
    <>
      <Col lg="5" md="7">
        <Card className="bg-secondary shadow border-0">
          <CardHeader className="bg-transparent">
            <div className="text-muted text-center mt-2">
              <h3 style={{ textTransform: "uppercase" }}>
                Login in to your account
              </h3>
            </div>
          </CardHeader>
          <CardBody className="px-lg-5 py-lg-5">
            {verified ? (
              <div className="alert alert-warning">
                Your account needs to be verified.{" "}
                <Link
                  style={{ fontWeight: "bold" }}
                  to={`/public/verify-email?email=${email}`}
                >
                  Click here{" "}
                </Link>
                to verify your account.
              </div>
            ) : null}
            <Form role="form">
              <FormGroup className="mb-3">
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-email-83" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Email"
                    type="email"
                    autoComplete="new-email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-lock-circle-open" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="Password"
                    type={showPassword ? "text" : "password"}
                    autoComplete="new-password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </InputGroup>
              </FormGroup>
              <div className="custom-control custom-control-alternative custom-checkbox">
                <input
                  className="custom-control-input"
                  id="customCheckRegister"
                  type="checkbox"
                  onChange={() => setShowPassword(!showPassword)}
                />
                <label
                  className="custom-control-label"
                  htmlFor="customCheckRegister"
                >
                  <span className="text-muted">Show password</span>
                </label>
              </div>
              <div className="text-right">
                <Button
                  className="my-2"
                  color="primary"
                  type="button"
                  onClick={handleLogin}
                >
                  Login
                </Button>
              </div>
            </Form>
            <Link to="/public/forgotten-password">
              <small>
                <b>Forgotten password</b>
              </small>
            </Link>
            <br></br>
            <small>
              Not registered yet?{" "}
              <Link to="/public/register-patient">
                <b>Create patient account</b>{" "}
              </Link>
              or{" "}
              <Link to="/public/register-doctor">
                {" "}
                <b>Join as a doctor</b>
              </Link>
            </small>
          </CardBody>
        </Card>
      </Col>
    </>
  );
};

export default Login;
