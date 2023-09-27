package com.calaton.student;

import com.calaton.address.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Address address;
}
