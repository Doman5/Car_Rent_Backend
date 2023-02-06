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
import pl.domanski.carRent.admin.rent.model.dto.AdminFullRentInfo;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentDto;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentLogDto;
import pl.domanski.carRent.admin.rent.repository.AdminRentLogRepository;
import pl.domanski.carRent.admin.rent.repository.AdminRentRepository;
import pl.domanski.carRent.common.repository.UserRepository;
import pl.domanski.carRent.security.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.domanski.carRent.admin.rent.mapper.AdminRentMapper.mapToAdminFullRentInfo;

@Service
@RequiredArgsConstructor
public class AdminRentService {

    private final AdminRentRepository adminRentRepository;
    private final AdminRentLogRepository adminRentLogRepository;
    private final UserRepository userRepository;

    public Page<AdminRentDto> getRents(Pageable pageable) {
        return adminRentRepository.findAllByOrderByIdDesc(pageable)
                .map(AdminRentMapper::mapToAdminRentDto);
    }


    @Transactional
    public AdminFullRentInfo getRent(Long id) {
        AdminRent rent = adminRentRepository.findById(id).orElseThrow();
        User user = userRepository.findById(rent.getUserId()).orElseThrow();
        List<AdminRentLogDto> rentLogs = adminRentLogRepository.findAllByRentIdOrderByIdDesc(rent.getId()).stream()
                .map(AdminRentMapper::mapToAdminRentLogDto)
                .toList();
        return mapToAdminFullRentInfo(rent, user, rentLogs);
    }



    @Transactional
    public void patchRent(Long id, Map<String, String> values) {
        AdminRent rent = adminRentRepository.findById(id).orElseThrow();
        patchValues(rent, values);
    }

    private void patchValues(AdminRent rent, Map<String, String> values) {
        if(values.get("rentStatus") != null) {
            processRentStatusChange(rent,values);
        }
    }

    private void processRentStatusChange(AdminRent rent, Map<String, String> values) {
        AdminRentStatus oldStatus = rent.getRentStatus();
        AdminRentStatus newStatus = AdminRentStatus.get(values.get("rentStatus")).orElseThrow();
        if(oldStatus == newStatus) {
            return;
        }
        rent.setRentStatus(newStatus);
        adminRentLogRepository.save(AdminRentLog.builder()
                        .rentId(rent.getId())
                        .created(LocalDateTime.now())
                        .note("Zmiana statusu z " + oldStatus.getName() + " na " + newStatus.getName())
                .build());
    }

    public HashMap<String, String> getRentStatuses() {
        HashMap<String, String> statuses = new HashMap<>();
        Arrays.stream(AdminRentStatus.values()).forEach(status -> statuses.put(status.name() ,status.getName()));
        return statuses;
    }
}
