package pl.domanski.carrent.customer.rent.service.rentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carrent.common.mail.EmailClientService;
import pl.domanski.carrent.common.mail.FakeEmailService;
import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.common.repository.CarRepository;
import pl.domanski.carrent.common.repository.RentRepository;
import pl.domanski.carrent.common.model.Payment;
import pl.domanski.carrent.rent.model.Rent;
import pl.domanski.carrent.rent.model.dto.RentDto;
import pl.domanski.carrent.rent.model.dto.RentSummary;
import pl.domanski.carrent.rent.repository.PaymentRepository;
import pl.domanski.carrent.rent.service.RentService;
import pl.domanski.carrent.rent.utils.CheckCarAvailabilityUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class RentServiceTest {

    @Mock
    CheckCarAvailabilityUtils checkCarAvailabilityUtils;

    @Mock
    CarRepository carRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    RentRepository rentRepository;

    @Mock
    EmailClientService emailClientService;

    @InjectMocks
    RentService rentService;

    @Test
    void should_place_rent() {
        //given
        RentDto rentDto = createRentDto();
        given(carRepository.findById(any())).willReturn(Optional.of(Car.builder().id(2L).build()));
        given(paymentRepository.findById(any())).willReturn(Optional.of(Payment.builder().id(1L).name("Przelew bankowy").build()));
        given(rentRepository.save(Mockito.any(Rent.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given(emailClientService.getInstance()).willReturn(new FakeEmailService());
        //when
        RentSummary rentSummary = rentService.placeRent(rentDto, 1L);
        //then
        assertEquals("Sochaczew", rentSummary.getReturnPlace());
        assertEquals(BigDecimal.valueOf(13690).setScale(2), rentSummary.getFinalPrice());
        assertEquals("Przelew bankowy", rentSummary.getPaymentName());
        verify(carRepository).findById(rentDto.getCarId());
        verify(paymentRepository).findById(rentDto.getPaymentId());
        verify(rentRepository).save(any(Rent.class));
    }

    private RentDto createRentDto() {
        return RentDto.builder()
                .carId(2L)
                .priceWithoutDeposit(BigDecimal.valueOf(3000))
                .deposit(BigDecimal.valueOf(10000))
                .rentalPrice(BigDecimal.valueOf(0))
                .returnPrice(BigDecimal.valueOf(0))
                .rentalPlace("Sochaczew")
                .returnPlace("Sochaczew")
                .rentalDate(LocalDateTime.now().plusDays(2))
                .returnDate(LocalDateTime.now().plusDays(3))
                .paymentId(1L)
                .build();
    }


}