package com.hunza.catererapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Contact {
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
}
