package com.example.bankcards.exception;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException() {
        super("Пользователь уже существует");
    }
}
