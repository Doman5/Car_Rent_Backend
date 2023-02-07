package pl.domanski.carrent.worker.rent.mapper;

import pl.domanski.carrent.worker.rent.model.WorkerRent;
import pl.domanski.carrent.worker.rent.model.WorkerRentLog;
import pl.domanski.carrent.worker.rent.model.WorkerRentStatus;
import pl.domanski.carrent.worker.rent.model.dto.WorkerRentBasicInfo;
import pl.domanski.carrent.worker.rent.model.dto.WorkerRentDto;

import java.time.LocalDateTime;

public class WorkerRentDtoMapper {

    public static WorkerRentDto mapToWorkerRentDto(WorkerRent rent) {
        return WorkerRentDto.builder()
                .id(rent.getId())
                .carId(rent.getCar().getId())
                .slug(rent.getCar().getSlug())
                .userId(rent.getUserId())
                .rentStatus(rent.getRentStatus().getValue())
                // uzupełnić dane użytkownika
                .rentalPlace(rent.getRentalPlace())
                .rentalDate(rent.getRentalDate())
                .returnPlace(rent.getReturnPlace())
                .returnDate(rent.getReturnDate())
                .grossValue(rent.getGrossValue())
                .build();
    }

    public static WorkerRentBasicInfo mapToWorkerRentBasicInfo(WorkerRent rent) {
        return WorkerRentBasicInfo.builder()
                .id(rent.getId())
                .slug(rent.getCar().getSlug())
                .rentStatus(rent.getRentStatus().getValue())
                .rentalDate(rent.getRentalDate())
                .build();
    }

    public static WorkerRentLog createStatusChangeLog(WorkerRent rent, WorkerRentStatus oldStatus, WorkerRentStatus newStatus) {
        return WorkerRentLog.builder()
                .rentId(rent.getId())
                .created(LocalDateTime.now())
                .note("Zmiana statusu z " + oldStatus + " na " + newStatus)
                .build();
    }
}
