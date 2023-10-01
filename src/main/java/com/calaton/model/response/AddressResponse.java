package com.calaton.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponse {

    private Long id;

    private String street;

    private String city;
}
