package pl.domanski.carRent.admin.user.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AdminUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private List<String> roles;
}
