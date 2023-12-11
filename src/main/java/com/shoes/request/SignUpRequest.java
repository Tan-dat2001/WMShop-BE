package com.shoes.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String roleId;

}
