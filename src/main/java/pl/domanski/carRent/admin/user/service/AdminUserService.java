package pl.domanski.carRent.admin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.user.mapper.AdminUserMapper;
import pl.domanski.carRent.admin.user.model.AdminUser;
import pl.domanski.carRent.admin.user.model.AdminUserRole;
import pl.domanski.carRent.admin.user.model.dto.AdminUserBasicInfo;
import pl.domanski.carRent.admin.user.model.dto.AdminUserDto;
import pl.domanski.carRent.admin.user.repository.AdminUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.domanski.carRent.admin.user.mapper.AdminUserMapper.mapToAdminUserDto;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public List<AdminUserBasicInfo> getAllUsers() {
        return adminUserRepository.findAll().stream()
                .map(AdminUserMapper::mapToAdminUserBasicInfo)
                .toList();
    }

    public AdminUserDto getUser(String username) {
        AdminUser adminUser = adminUserRepository.findByUsername(username).orElseThrow();
        return mapToAdminUserDto(adminUser);
    }

    public AdminUserDto patchUser(String username, Map<String, Boolean> values) {
        AdminUser adminUser = adminUserRepository.findByUsername(username).orElseThrow();
        return mapToAdminUserDto(patchUserRoles(adminUser, values));
    }

    private AdminUser patchUserRoles(AdminUser adminUser, Map<String, Boolean> values) {
        ArrayList<AdminUserRole> newRoles = new ArrayList<>();

        if (values.get("admin") != null && values.get("admin")) {
            newRoles.add(AdminUserRole.ROLE_ADMIN);
        }
        if (values.get("customer") != null && values.get("customer")) {
            newRoles.add(AdminUserRole.ROLE_CUSTOMER);
        }
        if (values.get("worker") != null &&values.get("worker")) {
            newRoles.add(AdminUserRole.ROLE_WORKER);
        }
        adminUser.setRoles(newRoles);
        return adminUserRepository.save(adminUser);
    }
}
