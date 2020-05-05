package ru.netology.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.userdata.UserData;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement CODE_FIELD = $("[name=code]");
    private final SelenideElement VERIFY_BUTTON = $("[data-test-id=action-verify]");
    private final SelenideElement ERROR_MESSAGE = $("[data-test-id=error-notification]");

    public VerificationPage() {
        CODE_FIELD.waitUntil(Condition.visible, 15000);
    }

    public DashboardPage validVerify(UserData.AuthSMSCode authSMSCode) {
        CODE_FIELD.setValue(authSMSCode.getAuthSMSCode());
        VERIFY_BUTTON.click();
        return new DashboardPage();
    }

    public void invalidVerify(UserData.AuthSMSCode authSMSCode) {
        String wrongCode = authSMSCode.getAuthSMSCode().substring(1,3) + "123";
        CODE_FIELD.setValue(wrongCode);
        VERIFY_BUTTON.click();
        ERROR_MESSAGE.waitUntil(Condition.visible, 15000);
    }
}