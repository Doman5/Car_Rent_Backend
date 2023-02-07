package pl.domanski.carrent.userProfile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.common.repository.RentRepository;
import pl.domanski.carrent.common.repository.UserRepository;
import pl.domanski.carrent.rent.model.Rent;
import pl.domanski.carrent.userProfile.controller.dto.CarNameDto;
import pl.domanski.carrent.userProfile.controller.dto.UserInfoDto;
import pl.domanski.carrent.userProfile.controller.dto.UserRentDto;
import pl.domanski.carrent.security.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final RentRepository rentRepository;

    public List<UserRentDto> getUserRents(Long userId) {
        return rentRepository.findAllByUserIdOrderByIdDesc(userId).stream()
                .map(this::mapToUserRentDto)
                .toList();

    }

    public UserInfoDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return mapToUserInfoDto(user);
    }


    public UserInfoDto updateUserInfo(Long userId, UserInfoDto userInfoDto) {
        User user = userRepository.findById(userId).orElseThrow();
        User changedUser = userRepository.save(changeUserInfoValues(user, userInfoDto));
        return mapToUserInfoDto(changedUser);
    }

    private UserInfoDto mapToUserInfoDto(User user) {
        return UserInfoDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
    }

    private User changeUserInfoValues(User user, UserInfoDto newUserInfoValues) {
        user.setFirstName(newUserInfoValues.getFirstName());
        user.setLastName(newUserInfoValues.getLastName());
        user.setUsername(newUserInfoValues.getUsername());
        user.setPhone(newUserInfoValues.getPhone());
        return user;
    }

    private UserRentDto mapToUserRentDto(Rent rent) {
        return UserRentDto.builder()
                .id(rent.getId())
                .car(mapToCarNameDto(rent.getCar()))
                .rentStatus(rent.getRentStatus().getValue())
                .rentalDate(rent.getRentalDate())
                .rentalPlace(rent.getRentalPlace())
                .returnDate(rent.getReturnDate())
                .returnPlace(rent.getReturnPlace())
                .priceWithoutDeposit(rent.getPriceWithoutDeposit())
                .deposit(rent.getDeposit())
                .finalPrice(rent.getFinalPrice())
                .paymentType(rent.getPayment().getName())
                .build();
    }

    private CarNameDto mapToCarNameDto(Car car) {
        return CarNameDto.builder()
                .name(String.format("%s %s %d", car.getBrand(), car.getModel(), car.getYear()))
                .slug(car.getSlug())
                .build();
    }

}
