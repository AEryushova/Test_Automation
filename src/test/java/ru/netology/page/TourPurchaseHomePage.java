package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class TourPurchaseHomePage {

    private final SelenideElement headerBuy = $x("//h2[text()='Путешествие дня']");
    private final SelenideElement buyCard = $$("button").findBy(text("Купить"));
    private final SelenideElement buyCredit = $$("button").findBy(text("Купить в кредит"));

    public BuyingByCard getBuyingByCard() {
        buyCard.click();
        return new BuyingByCard();
    }

    public BuyingByCredit getBuyingByCredit() {
        buyCredit.click();
        return new BuyingByCredit();
    }
}
