package pl.domanski.carRent.admin.user.mapper;

import pl.domanski.carRent.admin.user.model.AdminUser;
import pl.domanski.carRent.admin.user.model.dto.AdminUserBasicInfo;
import pl.domanski.carRent.admin.user.model.dto.AdminUserDto;
import pl.domanski.carRent.security.model.UserRole;

public class AdminUserMapper {

    public static AdminUserBasicInfo mapToAdminUserBasicInfo(AdminUser adminUser) {
        return AdminUserBasicInfo.builder()
                .username(adminUser.getUsername())
                .firstName(adminUser.getFirstName())
                .lastName(adminUser.getLastName())
                .roles(adminUser.getAuthorities().stream().map(UserRole::getRole).toList())
                .build();
    }

    public static AdminUserDto mapToAdminUserDto(AdminUser adminUser) {
        return AdminUserDto.builder()
                .username(adminUser.getUsername())
                .firstName(adminUser.getFirstName())
                .lastName(adminUser.getLastName())
                .phone(adminUser.getPhone())
                .roles(adminUser.getAuthorities().stream().map(UserRole::getRole).toList())
                .build();
    }
}
