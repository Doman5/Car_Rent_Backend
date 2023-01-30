package pl.domanski.carRent.customer.userProfile.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoDto {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
}
