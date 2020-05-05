package ru.netology.tests;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.pageobjects.LoginPage;
import ru.netology.userdata.UserData;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class AuthorisationTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clean() throws SQLException {
        UserData.cleanAuthCodes();
    }

    @Test
    @DisplayName("Авторизация с корректными данными")
    void authorisationWithCorrectData() throws SQLException {
        val loginPage = new LoginPage();
        val authInfo = UserData.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val authSMSCode = UserData.getAuthSMSCode();
        val dashboardPage = verificationPage.validVerify(authSMSCode);
    }

    @Test
    @DisplayName("Авторизация с некорректным паролем")
    void authorisationWithIncorrectPassword() {
        val loginPage = new LoginPage();
        val authInfo = UserData.getAuthInfo();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    @DisplayName("Авторизация с некорректным СМС кодом")
    void authorisationWithIncorrectCode() throws SQLException {
        val loginPage = new LoginPage();
        val authInfo = UserData.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val authSMSCode = UserData.getAuthSMSCode();
        verificationPage.invalidVerify(authSMSCode);
    }
}