package com.shoes.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    private String id;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;

}
