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
import { useState, useEffect } from "react";

const ForgottenPassword = () => {
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [isOtpSent, setIsOtpSent] = useState(false);
  const [timer, setTimer] = useState(30);
  const [showPassword, setShowPassword] = useState(false);

  const handleSendOTP = () => {
    setIsOtpSent(true);
    // Add logic to send OTP here
    // You can also start a timer to count down
    const countdown = setInterval(() => {
      setTimer((prevTimer) => prevTimer - 1);
    }, 1000);
    setTimeout(() => {
      clearInterval(countdown);
      setIsOtpSent(false);
      setTimer(30);
    }, 30000);
  };

  useEffect(() => {
    if (timer === 0) {
      setIsOtpSent(false);
    }
  }, [timer]);

  const isResetButtonDisabled = !otp || !password || !confirmPassword;

  const handleResetPassword = () => {
    // Add logic to reset password here
  };

  return (
    <>
      <Col lg="5" md="7">
        <Card className="bg-secondary shadow border-0">
          <CardHeader className="bg-transparent">
            <div className="text-muted text-center mt-2">
              <h3 style={{ textTransform: "uppercase" }}>Reset Password</h3>
            </div>
          </CardHeader>
          <CardBody className="px-lg-5 py-lg-5">
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
              <FormGroup className="mb-3">
                <div className="text-right">
                  <Button
                    className="mb-3"
                    color="primary"
                    type="button"
                    onClick={handleSendOTP}
                    disabled={isOtpSent}
                  >
                    {isOtpSent ? `Resend OTP in ${timer}s` : "Send OTP"}
                  </Button>
                </div>
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
              </FormGroup>
              <FormGroup>
                <InputGroup className="input-group-alternative">
                  <InputGroupAddon addonType="prepend">
                    <InputGroupText>
                      <i className="ni ni-lock-circle-open" />
                    </InputGroupText>
                  </InputGroupAddon>
                  <Input
                    placeholder="New Password"
                    type={showPassword ? "text" : "password"}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
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
                    placeholder="Confirm Password"
                    type={showPassword ? "text" : "password"}
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
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
                  onClick={handleResetPassword}
                  disabled={isResetButtonDisabled}
                >
                  Reset Password
                </Button>
              </div>
            </Form>
            <br></br>
            <small>
              <Link to="/public/login">Go back to login</Link>
            </small>
          </CardBody>
        </Card>
      </Col>
    </>
  );
};

export default ForgottenPassword;
