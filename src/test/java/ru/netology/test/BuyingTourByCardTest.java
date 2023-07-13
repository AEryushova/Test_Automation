package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.BuyingByCard;
import ru.netology.page.TourPurchaseHomePage;
import ru.netology.utils.DataGenerator;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.utils.DataBaseUtils.*;

public class BuyingTourByCardTest {
    private BuyingByCard buyingByCard;

    @BeforeAll
    static void setUpAllAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUpAll() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080/");
        var tourPurchaseHomePage = new TourPurchaseHomePage();
        buyingByCard = tourPurchaseHomePage.getBuyingByCard();
    }

    @Test
    void buyingTourByCardApprovedStatusCard() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getSuccessMessage();
        assertEquals(selectOrder().getPayment_id(),selectPayment().getTransaction_id());
        assertEquals("APPROVED", selectPayment().getStatus());

    }

    @Test
    void buyingTourByCardOwnerDoubleSurname() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateDoubleSurnameEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getSuccessMessage();
        assertEquals(selectOrder().getPayment_id(),selectPayment().getTransaction_id());
        assertEquals("APPROVED", selectPayment().getStatus());
    }

    @Test
    void buyingTourByCardOwnerNoName() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateNoNameOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getSuccessMessage();
        assertEquals(selectOrder().getPayment_id(),selectPayment().getTransaction_id());
        assertEquals("APPROVED", selectPayment().getStatus());
    }

    @Test
    void buyingTourByCardNumberCardBoundaryMinTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberIncompleteCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorFormat();
    }

    @Test
    void buyingTourByCardNumberCardBoundaryMaxTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberBoundaryCard());
        $x("//input[@value='4444 4444 4444 4441']").shouldBe(Condition.visible);
    }

    @Test
    void buyingTourByCardMonthBoundaryMinTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateIncompleteMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorFormat();
    }

    @Test
    void buyingTourByCardMonthBoundaryMaxTest() {
        buyingByCard.fillingMonthField(DataGenerator.generateBoundaryMonth());
        $x("//input[@value='07']").shouldBe(Condition.visible);
    }

    @Test
    void buyingTourByCardYearBoundaryMinTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateIncompleteYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorFormat();
    }

    @Test
    void buyingTourByCardYearBoundaryMaxTest() {
        buyingByCard.fillingYearField(DataGenerator.generateBoundaryYear());
        $x("//input[@value='23']").shouldBe(Condition.visible);
    }

    @Test
    void buyingTourByCardOwnerBoundaryMinTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateIncompleteOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorFormat();
    }

    @Test
    void buyingTourByCardOwnerBoundaryMaxTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateBoundaryOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorFormat();
    }

    @Test
    void buyingTourByCardOwnerMaxBoundaryTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateMaxBoundaryOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getSuccessMessage();
    }

    @Test
    void buyingTourByCardCVCBoundaryMinTest() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateIncompleteCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorFormat();
    }

    @Test
    void buyingTourByCardCVCBoundaryMaxTest() {
        buyingByCard.fillingCVCField(DataGenerator.generateBoundaryCVC());
        $x("//input[@value='404']").shouldBe(Condition.visible);
    }

    @Test
    void validationRequiredFieldsEmptyFieldCardNumber() {
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void validationRequiredFieldsEmptyFieldMonth() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void validationRequiredFieldsEmptyFieldYear() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void validationRequiredFieldsEmptyFieldOwner() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void validationRequiredFieldsEmptyFieldCVC() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void submittingFormWithEmptyFieldsWithNoData() {
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void buyingTourByCardDeclinedStatusCard() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberDeclinedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getDeclinedMessage();
        assertEquals(selectOrder().getPayment_id(),selectPayment().getTransaction_id());
        assertEquals("DECLINED", selectPayment().getStatus());
    }

    @Test
    void buyingTourByCardDeclinedStatusCardRandom() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberRandomCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getDeclinedMessage();
        assertEquals(selectOrder().getPayment_id(),selectPayment().getTransaction_id());
        assertEquals("DECLINED", selectPayment().getStatus());
    }

    @Test
    void enteringInvalidValuesLatinCardNumberField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberInvalidEnCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesCyrillicCardNumberField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberInvalidRuCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSymbolCardNumberField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateCardSymbol());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSpaceCardNumberField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateCardSpace());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesNonExistentMonthField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateInvalidMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorCardExpiryDate();
    }

    @Test
    void enteringElapsedMonthCurrentYearMonthField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generatePreviousMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorCardExpiryDate();
    }

    @Test
    void enteringInvalidValuesLatinMonthField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateInvalidEnMonthYear());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesCyrillicMonthField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateInvalidRuMonthYear());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSymbolMonthField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateSymbolMonthYear());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSpaceMonthField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateSpaceMonthYear());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringPreviousYearAfterCurrentOneYearField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generatePreviousYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorCardExpired();
    }

    @Test
    void enteringInvalidValuesLatinYearField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateInvalidEnMonthYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesCyrillicYearField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateInvalidRuMonthYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSymbolYearField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateSymbolMonthYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSpaceYearField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateSpaceMonthYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesCyrillicOwnerField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateInvalidRuOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesNumbersOwnerField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateNumberOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSymbolOwnerField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateSymbolOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSpaceOwnerField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateSpaceOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesLatinCVCField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateInvalidEnCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesCyrillicCVCField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateInvalidRuCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSymbolCVCField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateSymbolCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }

    @Test
    void enteringInvalidValuesSpaceCVCField() {
        buyingByCard.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCard.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCard.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCard.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCard.fillingCVCField(DataGenerator.generateSpaceCVC());
        buyingByCard.subFormWithCardDetails();
        buyingByCard.getErrorRequiredField();
    }
}
