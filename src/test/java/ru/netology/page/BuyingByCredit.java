package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.utils.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BuyingByCredit {
    private final SelenideElement buyCard = $$("button").findBy(text("Купить"));
    private final SelenideElement numberCard = $x("//input[@placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $x("//input[@placeholder='08']");
    private final SelenideElement year = $x("//input[@placeholder='22']");
    private final SelenideElement owner = $$(".input__top").findBy(text("Владелец")).parent().find(".input__control");
    private final SelenideElement cvc = $x("//input[@placeholder='999']");
    private final SelenideElement continueButton = $$("button").findBy(text("Продолжить"));
    private final SelenideElement approvedBankOperation = $x("//div[contains(text(),'Операция одобрена Банком')]");
    private final SelenideElement declinedBankOperation = $x("//div[contains(text(),'Ошибка! Банк отказал в проведении операции')]");
    private final SelenideElement errors = $(".input__sub");

    public BuyingByCredit() {
        numberCard.shouldBe(Condition.visible);
        month.shouldBe(Condition.visible);
        year.shouldBe(Condition.visible);
        owner.shouldBe(Condition.visible);
        cvc.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }

    public BuyingByCard buyByCredit() {
        buyCard.click();
        return new BuyingByCard();
    }

    public void fillingNumberCardField(String valueField) {
        numberCard.setValue(valueField);
    }

    public void fillingMonthField(String valueField) {
        month.setValue(valueField);
    }

    public void fillingYearField(String valueField) {
        year.setValue(valueField);
    }

    public void fillingOwnerField(String valueField) {
        owner.setValue(valueField);
    }

    public void fillingCVCField(String valueField) {
        cvc.setValue(valueField);
    }

    public void subFormWithCardDetails() {
        continueButton.click();
    }

    public void getSuccessMessage() {
        approvedBankOperation.shouldBe(Condition.visible, Duration.ofSeconds(25));
    }

    public void getDeclinedMessage() {
        declinedBankOperation.shouldBe(Condition.visible, Duration.ofSeconds(25));
    }

    public void getErrorFormat() {
        errors.shouldHave(Condition.text("Неверный формат"));
    }

    public void getErrorRequiredField() {
        errors.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void getErrorCardExpiryDate() {
        errors.shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void getErrorCardExpired() {
        errors.shouldHave(Condition.text("Истёк срок действия карты"));
    }
}