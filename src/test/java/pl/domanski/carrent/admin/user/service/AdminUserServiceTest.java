package pl.domanski.carrent.admin.user.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carrent.admin.user.model.AdminUser;
import pl.domanski.carrent.admin.user.model.dto.AdminUserDto;
import pl.domanski.carrent.admin.user.repository.AdminUserRepository;
import pl.domanski.carrent.security.model.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AdminUserServiceTest {

    @Mock
    private AdminUserRepository adminUserRepository;

    @InjectMocks
    private AdminUserService adminUserService;

    @Test
    void shouldAddAdminRole() {
        //given
        given(adminUserRepository.findByUsername("test@test.com")).willReturn(creteTestUser());
        given(adminUserRepository.save(Mockito.any(AdminUser.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        //when
        AdminUserDto user = adminUserService.patchUser("test@test.com", Map.of("admin", true));
        //then
        assertThat(user.getRoles().contains(UserRole.ROLE_ADMIN.getRole())).isTrue();
        assertThat(user.getRoles().contains(UserRole.ROLE_CUSTOMER.getRole())).isFalse();
        assertThat(user.getRoles().contains(UserRole.ROLE_WORKER.getRole())).isFalse();
    }

    @Test
    void shouldAddCustomerAndWorkerRole() {
        //given
        given(adminUserRepository.findByUsername("test@test.com")).willReturn(creteTestUser());
        given(adminUserRepository.save(Mockito.any(AdminUser.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        //when
        AdminUserDto user = adminUserService.patchUser("test@test.com", Map.of("customer", true, "worker", true));
        //then
        assertThat(user.getRoles().contains(UserRole.ROLE_ADMIN.getRole())).isFalse();
        assertThat(user.getRoles().contains(UserRole.ROLE_CUSTOMER.getRole())).isTrue();
        assertThat(user.getRoles().contains(UserRole.ROLE_WORKER.getRole())).isTrue();
    }

    @Test
    void shouldRemoveAdminRole() {
        //given
        given(adminUserRepository.findByUsername("test@test.com")).willReturn(creteTestUserWithAllRoles());
        given(adminUserRepository.save(Mockito.any(AdminUser.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        //when
        AdminUserDto user = adminUserService.patchUser("test@test.com", Map.of("admin", false,"customer", true, "worker", true));
        //then
        assertThat(user.getRoles().contains(UserRole.ROLE_ADMIN.getRole())).isFalse();
        assertThat(user.getRoles().contains(UserRole.ROLE_CUSTOMER.getRole())).isTrue();
        assertThat(user.getRoles().contains(UserRole.ROLE_WORKER.getRole())).isTrue();
    }

    @Test
    void shouldOnlyBeGivenTheCustomerRole() {
        //given
        given(adminUserRepository.findByUsername("test@test.com")).willReturn(creteTestUserWithAllRoles());
        given(adminUserRepository.save(Mockito.any(AdminUser.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        //when
        AdminUserDto user = adminUserService.patchUser("test@test.com", Map.of("admin", false,"customer", true, "worker", false));
        //then
        assertThat(user.getRoles().contains(UserRole.ROLE_ADMIN.getRole())).isFalse();
        assertThat(user.getRoles().contains(UserRole.ROLE_CUSTOMER.getRole())).isTrue();
        assertThat(user.getRoles().contains(UserRole.ROLE_WORKER.getRole())).isFalse();
    }

    private Optional<AdminUser> creteTestUserWithAllRoles() {
        return Optional.of(AdminUser.builder()
                .id(1L)
                .username("test@test.com")
                .authorities(List.of(UserRole.ROLE_ADMIN, UserRole.ROLE_CUSTOMER, UserRole.ROLE_WORKER))
                .build());
    }

    private Optional<AdminUser> creteTestUser() {
        return Optional.of(AdminUser.builder()
                        .id(1L)
                        .username("test@test.com")
                        .authorities(new ArrayList<>())
                .build());
    }
}