package com.calaton.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponse {

    private Long id;

    private String fullName;

    private String email;

    private AddressResponse address;
}
