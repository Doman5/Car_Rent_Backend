package pl.domanski.carRent.admin.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.rent.model.dto.AdminFullRentInfo;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentDto;
import pl.domanski.carRent.admin.rent.service.AdminRentService;

import java.util.Map;

@RestController
@RequestMapping("/admin/rents")
@RequiredArgsConstructor
public class AdminRentController {

    private final AdminRentService adminRentService;

    @GetMapping
    public Page<AdminRentDto> getRents(Pageable pageable) {
        return adminRentService.getRents(pageable);
    }

    @GetMapping("/{id}")
    public AdminFullRentInfo getRent(@PathVariable Long id) {
        return adminRentService.getRent(id);
    }

    @PatchMapping("/{id}")
    public void patchRent(@PathVariable Long id, @RequestBody Map<String, String> values) {
        adminRentService.patchRent(id, values);
    }

    @GetMapping("/statuses")
    public Map<String, String > getRentStatuses() {
        return adminRentService.getRentStatuses();
    }
}
