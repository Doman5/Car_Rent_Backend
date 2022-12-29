package pl.domanski.carRent.admin.rent.mapper;

import pl.domanski.carRent.admin.rent.model.AdminRent;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentDto;

public class AdminRentMapper {

    public static AdminRentDto mapToAdminRentDto(AdminRent adminRent) {
        return AdminRentDto.builder()
                .id(adminRent.getId())
                .rentStatus(adminRent.getRentStatus())
                .grossValue(adminRent.getGrossValue())
                .build();
    }
}
