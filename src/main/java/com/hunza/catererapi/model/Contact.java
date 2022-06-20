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
    @NotNull
    @Pattern(message = "Invalid mobile number. Allowed only 10 digit number", regexp="(^$|[0-9]{10})")
    private String mobileNumber;
    @Email(message = "Email is not valid",regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotEmpty(message = "Email cannot be empty")
    private String emailAddress;
}
