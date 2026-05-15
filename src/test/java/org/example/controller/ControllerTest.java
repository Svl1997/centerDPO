package org.example.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    void testIsEmptyAccept() {
        // Проверяем, что непустые логин и пароль дают true
        assertTrue(Controller.isEmptyAccept("user", "pass"));
        // Пустой логин – false
        assertFalse(Controller.isEmptyAccept("", "pass"));
        // Пустой пароль – false
        assertFalse(Controller.isEmptyAccept("user", ""));
        // null – false
        assertFalse(Controller.isEmptyAccept(null, "pass"));
        assertFalse(Controller.isEmptyAccept("user", null));
    }

    @Test
    void testValidateUserData() {
        // Корректные данные
        assertTrue(Controller.validateUserData("login", "pass", "Иван", "Иванов"));
        // Пустой логин
        assertFalse(Controller.validateUserData("", "pass", "Иван", "Иванов"));
        // Пароль с пробелом
        assertFalse(Controller.validateUserData("login", "pa ss", "Иван", "Иванов"));
        // Пустое имя
        assertFalse(Controller.validateUserData("login", "pass", "", "Иванов"));
        // Пустая фамилия
        assertFalse(Controller.validateUserData("login", "pass", "Иван", ""));
        // Все поля пустые
        assertFalse(Controller.validateUserData("", "", "", ""));
    }
}