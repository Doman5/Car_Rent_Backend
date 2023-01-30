package pl.domanski.carRent.admin.user.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AdminUserBasicInfo {
    private String username;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
