package com.healthcare.notification.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
class EmailRequestDTO {
    private String to;
    private String subject;
    private String message;
}
