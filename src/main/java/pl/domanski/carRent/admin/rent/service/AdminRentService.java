package pl.domanski.carRent.admin.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.rent.mapper.AdminRentMapper;
import pl.domanski.carRent.admin.rent.model.AdminRent;
import pl.domanski.carRent.admin.rent.model.AdminRentLog;
import pl.domanski.carRent.admin.rent.model.AdminRentStatus;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentDto;
import pl.domanski.carRent.admin.rent.repository.AdminRentLogRepository;
import pl.domanski.carRent.admin.rent.repository.AdminRentRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminRentService {

    private final AdminRentRepository adminRentRepository;
    private final AdminRentLogRepository adminRentLogRepository;

    public Page<AdminRentDto> getRents(Pageable pageable) {
        return adminRentRepository.findAll(pageable)
                .map(AdminRentMapper::mapToAdminRentDto);
    }


    public AdminRent getRent(Long id) {
        return adminRentRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void patchRent(Long id, Map<String, String> values) {
        AdminRent rent = adminRentRepository.findById(id).orElseThrow();
        patchValues(rent, values);
    }

    private void patchValues(AdminRent rent, Map<String, String> values) {
        if(values.get("rentStatus") != null) {
            processOrderStatusChange(rent,values);
        }
    }

    private void processOrderStatusChange(AdminRent rent, Map<String, String> values) {
        AdminRentStatus oldStatus = rent.getRentStatus();
        AdminRentStatus newStatus = AdminRentStatus.get(values.get("rentStatus")).orElseThrow();
        if(oldStatus == newStatus) {
            return;
        }
        rent.setRentStatus(newStatus);
        adminRentLogRepository.save(AdminRentLog.builder()
                        .rentId(rent.getId())
                        .created(LocalDateTime.now())
                        .note("Zmiana statusu z " + oldStatus + " na " + newStatus)
                .build());
    }

    public List<String> getRentStatuses() {
        return Arrays.stream(AdminRentStatus.values())
                .map(AdminRentStatus::getName).toList();
    }
}
