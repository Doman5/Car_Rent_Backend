package pl.domanski.carRent.admin.user.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.domanski.carRent.admin.user.model.AdminUserRole;

import java.util.List;

@Getter
@Builder
public class AdminUserBasicInfo {
    private String username;
    private String firstName;
    private String secondName;
    private List<AdminUserRole> roles;
}
