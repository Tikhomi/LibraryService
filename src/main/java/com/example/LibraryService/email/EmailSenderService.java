package com.example.LibraryService.email;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
