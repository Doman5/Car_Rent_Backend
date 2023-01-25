package pl.domanski.carRent.admin.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.user.model.dto.AdminUserBasicInfo;
import pl.domanski.carRent.admin.user.model.dto.AdminUserDto;
import pl.domanski.carRent.admin.user.service.AdminUserService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping()
    public List<AdminUserBasicInfo> getAllUsers() {
        return adminUserService.getAllUsers();
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
