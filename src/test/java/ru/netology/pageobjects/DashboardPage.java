package ru.netology.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement HEADING = $("[data-test-id=dashboard]");

    public DashboardPage() {
        HEADING.waitUntil(Condition.visible, 15000);
    }
}
