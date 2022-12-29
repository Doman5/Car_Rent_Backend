package pl.domanski.carRent.customer.rent.mapper;

import pl.domanski.carRent.customer.rent.model.Rent;

public class RentEmailMessageMapper {
    public static String createEmailMessage(Rent rent) {
        return "Twoje zamówienie zostało o id: " + rent.getId() +
                "\n Data wynajmu: " + rent.getRentalDate() + " " + rent.getRentalPlace() +
                "\n Data oddana: " + rent.getReturnDate() + " " + rent.getReturnPlace() +
                "\n Koszt: " + rent.getGrossValue() +
                "\n\n" +
                "\n Płatność: " + rent.getPayment().getName() +
                (rent.getPayment().getNote() != null ? "\n" + rent.getPayment().getNote() : "") +
                "\n\n Dziękujemy że nas wybrałeś";
    }
}