package pl.domanski.carRent.customer.userProfile.controller.dto;

import lombok.Getter;

@Getter
public class UserInfoEditDto {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
}
