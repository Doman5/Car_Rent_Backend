package pl.domanski.carRent.admin.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.user.model.dto.AdminUserBasicInfo;
import pl.domanski.carRent.admin.user.model.dto.AdminUserDto;
import pl.domanski.carRent.admin.user.service.AdminUserService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping()
    public Page<AdminUserBasicInfo> getAllUsers(Pageable pageable) {
        return adminUserService.getAllUsers(pageable);
    }

    @GetMapping("/{username}")
    public AdminUserDto getUser(@PathVariable String username) {
        return adminUserService.getUser(username);
    }

    @PatchMapping("/{username}")
    public AdminUserDto patchUser(@PathVariable String username, @RequestBody Map<String, Boolean> values) {
        return adminUserService.patchUser(username, values);
    }
}
