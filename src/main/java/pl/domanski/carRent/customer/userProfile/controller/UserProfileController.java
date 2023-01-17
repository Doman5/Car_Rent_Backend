package pl.domanski.carRent.customer.userProfile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.userProfile.controller.dto.UserInfoDto;
import pl.domanski.carRent.customer.userProfile.controller.dto.UserRentDto;
import pl.domanski.carRent.customer.userProfile.service.UserProfileService;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/rents")
    public List<UserRentDto> getUserRents(@AuthenticationPrincipal Long userId) {
        return userProfileService.getUserRents(userId);
    }

    @GetMapping("/info")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal Long userId) {
        return userProfileService.getUserInfo(userId);
    }

    @PutMapping("/info")
    public UserInfoDto updateUserInfo(@AuthenticationPrincipal Long userId, @RequestBody UserInfoDto userInfoEditDto) {
        return userProfileService.updateUserInfo(userId, userInfoEditDto);
    }

}
