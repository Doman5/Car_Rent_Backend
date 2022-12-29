package pl.domanski.carRent.common.mail;

public interface EmailSender {

    void send(String to, String subject, String msg);
}
