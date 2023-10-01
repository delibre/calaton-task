package com.calaton.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentRequest {

    private String fullName;

    private String email;

    private AddressRequest address;
}
