package com.healthcare.account.exception;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class OTPExpiredException extends CustomException {
    public OTPExpiredException(Integer otp) {
        super("OTPExpiredException", "OTP", "Given OTP " + otp + " has been expired, resend it and try again.", HttpStatus.BAD_REQUEST);
    }
}