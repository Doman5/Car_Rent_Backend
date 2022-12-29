package pl.domanski.carRent.admin.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.rent.mapper.AdminRentMapper;
import pl.domanski.carRent.admin.rent.model.AdminRent;
import pl.domanski.carRent.admin.rent.model.AdminRentStatus;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentDto;
import pl.domanski.carRent.admin.rent.repository.AdminRentRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminRentService {

    private final AdminRentRepository adminRentRepository;

    public Page<AdminRentDto> getRents(Pageable pageable) {
        return adminRentRepository.findAll(pageable)
                .map(AdminRentMapper::mapToAdminRentDto);
    }


    public AdminRent getRent(Long id) {
        return adminRentRepository.findById(id).orElseThrow();
    }

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
        AdminRentStatus newStatus = AdminRentStatus.valueOf(values.get("rentStatus"));
        if(oldStatus == newStatus) {
            return;
        }
        rent.setRentStatus(newStatus);
        //log status change
    }
}
