package pl.domanski.carRent.admin.user.mapper;

import pl.domanski.carRent.admin.user.model.AdminUser;
import pl.domanski.carRent.admin.user.model.dto.AdminUserBasicInfo;
import pl.domanski.carRent.admin.user.model.dto.AdminUserDto;

public class AdminUserMapper {

    public static AdminUserBasicInfo mapToAdminUserBasicInfo(AdminUser adminUser) {
        return AdminUserBasicInfo.builder()
                .username(adminUser.getUsername())
                .firstName(adminUser.getFirstName())
                .secondName(adminUser.getSecondName())
                .roles(adminUser.getRoles())
                .build();
    }

    public static AdminUserDto mapToAdminUserDto(AdminUser adminUser) {
        return AdminUserDto.builder()
                .username(adminUser.getUsername())
                .firstName(adminUser.getFirstName())
                .secondName(adminUser.getSecondName())
                .phone(adminUser.getPhone())
                .enabled(adminUser.isEnabled())
                .roles(adminUser.getRoles())
                .build();
    }
}
