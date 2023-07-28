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
    private final SelenideElement errorSubNumberCard = $$(".input__top").findBy(text("Номер карты")).parent().find(".input__sub");
    private final SelenideElement errorSubMonth = $$(".input__top").findBy(text("Месяц")).parent().find(".input__sub");
    private final SelenideElement errorSubYear = $$(".input__top").findBy(text("Год")).parent().find(".input__sub");
    private final SelenideElement errorSubOwner = $$(".input__top").findBy(text("Владелец")).parent().find(".input__sub");
    private final SelenideElement errorSubCVC = $$(".input__top").findBy(text("CVC/CVV")).parent().find(".input__sub");

    private final SelenideElement cardNumberValue=$x("//input[@value='4444 4444 4444 4441']");
    private final SelenideElement monthValue=$x("//input[@value='07']");
    private final SelenideElement yearValue=$x("//input[@value='23']");
    private final SelenideElement cvcValue=$x("//input[@value='404']");

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

    public void errorsFieldNumberCard(String errorText) {
        errorSubNumberCard.shouldHave(Condition.text(errorText));
    }

    public void errorsFieldMonth(String errorText) {
        errorSubMonth.shouldHave(Condition.text(errorText));
    }

    public void errorsFieldYear(String errorText) {
        errorSubYear.shouldHave(Condition.text(errorText));
    }

    public void errorsFieldOwner(String errorText) {
        errorSubOwner.shouldHave(Condition.text(errorText));
    }

    public void errorsFieldCVC(String errorText) {
        errorSubCVC.shouldHave(Condition.text(errorText));
    }
    public void cardNumberSetValue(){
        cardNumberValue.shouldBe(Condition.visible);
    }
    public void monthSetValue(){
        monthValue.shouldBe(Condition.visible);
    }
    public void yearSetValue(){
        yearValue.shouldBe(Condition.visible);
    }
    public void cvcSetValue(){
        cvcValue.shouldBe(Condition.visible);
    }

}