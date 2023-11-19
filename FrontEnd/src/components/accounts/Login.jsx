import { Link, useNavigate } from "react-router-dom";
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
import { endPoints } from "scripts/constants";
import getDeviceCode from "scripts/firebasesetup";
import AxiosInstance from "scripts/axioInstance";

const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [verified, setVerified] = useState(false);
  const [showOtp, setShowOtp] = useState(false);
  const [otp, setOtp] = useState();
  const [warning, setWarning] = useState("");
  const [isOtpSent, setIsOtpSent] = useState(false);
  const [timer, setTimer] = useState(30);

  const handleSendOTP = () => {
    const url = "http://localhost:5100/access/generate-otp/" + email;
    AxiosInstance.post(url, "")
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        setWarning(error.response.data.message);
      });
    setIsOtpSent(true);
    const countdown = setInterval(() => {
      setTimer((prevTimer) => prevTimer - 1);
    }, 1000);
    setTimeout(() => {
      clearInterval(countdown);
      setIsOtpSent(false);
      setTimer(30);
    }, 30000);
  };

  const handleLogin = () => {
    const url = "http://localhost:5100/access/login";
    // const deviceToken = getDeviceCode();
    if (!email || !password) {
      setWarning("Fill up your email and password correctly.");
      return;
    }
    setWarning("");
    let loginReq = {
      identity: email,
      password: password,
      otp: !otp || otp == 0 ? 0 : otp,
    };
    AxiosInstance.post(url, loginReq)
      .then((result) => {
        if (result.status === 200) {
          const token = result.data.bearerToken;
          const role = result.data.role;
          const email = result.data.email;
          const username = result.data.username;
          localStorage.setItem("token", token);
          localStorage.setItem("role", role);
          localStorage.setItem("email", email);
          localStorage.setItem("username", username);

          navigate("/");
        }
      })
      .catch((error) => {
        console.log(error);
        if (error.response.status == 406) {
          setShowOtp(true);
        }
        setWarning(error.response.data.message);
      });
  };

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
              {warning && <div className="alert alert-danger">{warning}</div>}
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
              <div className="custom-control custom-control-alternative custom-checkbox mb-3">
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
              {showOtp && (
                <FormGroup className="mb-3">
                  <InputGroup className="input-group-alternative">
                    <InputGroupAddon addonType="prepend">
                      <InputGroupText>
                        <i className="ni ni-key-25" />
                      </InputGroupText>
                    </InputGroupAddon>
                    <Input
                      placeholder="OTP"
                      type="number"
                      value={otp}
                      onChange={(e) => setOtp(e.target.value)}
                    />
                  </InputGroup>
                  <div className="text-left">
                    <Button
                      className="mt-3"
                      color="primary"
                      type="button"
                      onClick={handleSendOTP}
                      disabled={isOtpSent}
                    >
                      {isOtpSent
                        ? `OTP Sent. Resend OTP in ${timer}s`
                        : "Send OTP"}
                    </Button>
                  </div>
                </FormGroup>
              )}

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
