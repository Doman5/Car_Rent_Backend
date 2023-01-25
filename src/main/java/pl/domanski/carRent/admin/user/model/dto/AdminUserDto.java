package pl.domanski.carRent.admin.user.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.domanski.carRent.admin.user.model.AdminUserRole;

import java.util.List;

@Builder
@Getter
public class AdminUserDto {
    private String username;
    private boolean enabled;
    private String firstName;
    private String secondName;
    private String phone;
    private List<AdminUserRole> roles;
}
