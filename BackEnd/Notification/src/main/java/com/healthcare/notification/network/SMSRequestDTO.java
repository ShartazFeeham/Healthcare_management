package com.healthcare.notification.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
class SMSRequestDTO {
    private String receiverNumber;
    private String messageBody;
}
