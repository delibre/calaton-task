package com.calaton.mapper;

import com.calaton.model.entity.Address;
import com.calaton.model.request.AddressRequest;
import com.calaton.model.response.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressRequest addressRequest) {
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        return address;
    }

    public AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .build();
    }

    public void updateEntity(Address address, AddressRequest addressRequest) {
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
    }
}
