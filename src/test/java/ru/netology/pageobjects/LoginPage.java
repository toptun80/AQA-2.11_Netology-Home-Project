package ru.netology.pageobjects;

import com.codeborne.selenide.SelenideElement;
import ru.netology.userdata.UserData;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement LOGIN_FIELD = $("[name=login]");
    private final SelenideElement PASSWORD_FIELD = $("[name=password]");
    private final SelenideElement LOGIN_BUTTON = $("[data-test-id=action-login]");
    private final SelenideElement ERROR_MESSAGE = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(UserData.AuthInfo authInfo) {
        LOGIN_FIELD.setValue(authInfo.getLogin());
        PASSWORD_FIELD.setValue(authInfo.getPassword());
        LOGIN_BUTTON.click();
        return new VerificationPage();
    }

    public SelenideElement invalidLogin(UserData.AuthInfo authInfo) {
        LOGIN_FIELD.setValue(authInfo.getLogin());
        PASSWORD_FIELD.setValue(authInfo.getPassword() + "123");
        LOGIN_BUTTON.click();
        return ERROR_MESSAGE;
    }


}
