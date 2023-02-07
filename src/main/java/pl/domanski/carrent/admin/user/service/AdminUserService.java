package pl.domanski.carrent.admin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.domanski.carrent.admin.user.mapper.AdminUserMapper;
import pl.domanski.carrent.admin.user.model.AdminUser;
import pl.domanski.carrent.admin.user.model.dto.AdminUserBasicInfo;
import pl.domanski.carrent.admin.user.model.dto.AdminUserDto;
import pl.domanski.carrent.admin.user.repository.AdminUserRepository;
import pl.domanski.carrent.security.model.UserRole;

import java.util.ArrayList;
import java.util.Map;

import static pl.domanski.carrent.admin.user.mapper.AdminUserMapper.mapToAdminUserDto;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public Page<AdminUserBasicInfo> getAllUsers(Pageable pageable) {
        Page<AdminUser> all = adminUserRepository.findAll(pageable);
        Page<AdminUserBasicInfo> page = all.map(AdminUserMapper::mapToAdminUserBasicInfo);
        return page;
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
        ArrayList<UserRole> newRoles = new ArrayList<>();

        if (values.get("admin") != null && values.get("admin")) {
            newRoles.add(UserRole.ROLE_ADMIN);
        }
        if (values.get("customer") != null && values.get("customer")) {
            newRoles.add(UserRole.ROLE_CUSTOMER);
        }
        if (values.get("worker") != null &&values.get("worker")) {
            newRoles.add(UserRole.ROLE_WORKER);
        }
        adminUser.setAuthorities(newRoles);
        return adminUserRepository.save(adminUser);
    }
}
