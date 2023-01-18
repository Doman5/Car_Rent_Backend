package pl.domanski.carRent.admin.rent.mapper;

import pl.domanski.carRent.admin.rent.model.AdminRent;
import pl.domanski.carRent.admin.rent.model.AdminRentLog;
import pl.domanski.carRent.admin.rent.model.dto.AdminFullRentInfo;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentDto;
import pl.domanski.carRent.admin.rent.model.dto.AdminRentLogDto;
import pl.domanski.carRent.security.model.User;

import java.util.List;

public class AdminRentMapper {

    public static AdminRentDto mapToAdminRentDto(AdminRent adminRent) {
        return AdminRentDto.builder()
                .id(adminRent.getId())
                .car(adminRent.getCar().getSlug())
                .paymentType(adminRent.getPayment().getName())
                .rentStatus(adminRent.getRentStatus().name())
                .rentalDate(adminRent.getRentalDate())
                .returnDate(adminRent.getReturnDate())
                .grossValue(adminRent.getGrossValue())
                .build();
    }

    public static AdminFullRentInfo mapToAdminFullRentInfo(AdminRent adminRent, User user, List<AdminRentLogDto> rentLog) {
        return AdminFullRentInfo.builder()
                .id(adminRent.getId())
                .car(adminRent.getCar().getSlug())
                .username(user.getUsername())
                .paymentType(adminRent.getPayment().getName())
                .rentStatus(adminRent.getRentStatus().getName())
                .rentalDate(adminRent.getRentalDate())
                .rentalPlace(adminRent.getRentalPlace())
                .returnDate(adminRent.getReturnDate())
                .returnPlace(adminRent.getReturnPlace())
                .grossValue(adminRent.getGrossValue())
                .rentLogs(rentLog)
                .build();
    }

    public static AdminRentLogDto mapToAdminRentLogDto(AdminRentLog adminRentLog) {
        return AdminRentLogDto.builder()
                .note(adminRentLog.getNote())
                .created(adminRentLog.getCreated())
                .build();
    }
}
