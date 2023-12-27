package com.shoes.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeInforCustomerRequest {

    private String id;
    private String firstName;
    private String lastName;
//    private String gender;
//    private Long birthday;
    private String phone;
    private String address;

}
