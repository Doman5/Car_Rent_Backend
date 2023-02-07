package pl.domanski.carrent.rent.mapper;

import pl.domanski.carrent.rent.model.Rent;

public class RentEmailMessageMapper {
    public static String createEmailMessage(Rent rent) {
        return "Twoje zamówienie zostało o id: " + rent.getId() +
                "\n Data wynajmu: " + rent.getRentalDate() + " " + rent.getRentalPlace() +
                "\n Data oddana: " + rent.getReturnDate() + " " + rent.getReturnPlace() +
                "\n Koszt: " + rent.getPriceWithoutDeposit() +
                "\n\n" +
                "\n Płatność: " + rent.getPayment().getName() +
                (rent.getPayment().getNote() != null ? "\n" + rent.getPayment().getNote() : "") +
                "\n\n Dziękujemy że nas wybrałeś";
    }
}
