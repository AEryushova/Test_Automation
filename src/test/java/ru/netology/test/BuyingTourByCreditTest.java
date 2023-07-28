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
import ru.netology.page.BuyingByCredit;
import ru.netology.page.TourPurchaseHomePage;
import ru.netology.utils.DataGenerator;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.utils.DataBaseUtils.*;


public class BuyingTourByCreditTest {
    private BuyingByCredit buyingByCredit;

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
        var tourPurchaseHomePage= new TourPurchaseHomePage();
        buyingByCredit = tourPurchaseHomePage.getBuyingByCredit();
    }
    @Test
    void buyingTourByCredit() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.getSuccessMessage();
        assertEquals(selectOrder().getPayment_id(),selectCreditRequest().getBank_id());
        assertEquals("APPROVED", selectCreditRequest().getStatus());
    }
    @Test
    void buyingTourByCreditOwnerDoubleSurname() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateDoubleSurnameEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.getSuccessMessage();
        assertEquals(selectOrder().getPayment_id(),selectCreditRequest().getBank_id());
        assertEquals("APPROVED", selectCreditRequest().getStatus());
    }
    @Test
    void buyingTourByCreditOwnerNoName() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateNoNameOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.getSuccessMessage();
        assertEquals(selectOrder().getPayment_id(),selectCreditRequest().getBank_id());
        assertEquals("APPROVED", selectCreditRequest().getStatus());
    }
    @Test
    void buyingTourByCreditNumberCardBoundaryMinTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberIncompleteCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateNoNameOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Неверный формат");
    }
    @Test
    void buyingTourByCreditNumberCardBoundaryMaxTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberBoundaryCard());
        buyingByCredit.cardNumberSetValue();
    }
    @Test
    void buyingTourByCreditMonthBoundaryMinTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateIncompleteMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Неверный формат");
    }

    @Test
    void buyingTourByCreditMonthBoundaryMaxTest() {
        buyingByCredit.fillingMonthField(DataGenerator.generateBoundaryMonth());
        buyingByCredit.monthSetValue();
    }
    @Test
    void buyingTourByCreditYearBoundaryMinTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateIncompleteYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Неверный формат");
    }
    @Test
    void buyingTourByCreditYearBoundaryMaxTest() {
        buyingByCredit.fillingYearField(DataGenerator.generateBoundaryYear());
        buyingByCredit.yearSetValue();
    }
    @Test
    void buyingTourByCreditOwnerBoundaryMinTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateIncompleteOwner());
        buyingByCredit .fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit .subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Неверный формат");
    }
    @Test
    void buyingTourByCreditOwnerBoundaryMaxTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateBoundaryOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Неверный формат");
    }
    @Test
    void buyingTourByCreditOwnerMaxBoundaryTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateMaxBoundaryOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.getSuccessMessage();
    }
    @Test
    void buyingTourByCreditCVCBoundaryMinTest() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateIncompleteCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldCVC("Неверный формат");
    }
    @Test
    void buyingTourByCreditCVCBoundaryMaxTest() {
        buyingByCredit.fillingCVCField(DataGenerator.generateBoundaryCVC());
        buyingByCredit.cvcSetValue();
    }
    @Test
    void validationRequiredFieldsEmptyFieldCardNumber() {
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Поле обязательно для заполнения");
    }
    @Test
    void validationRequiredFieldsEmptyFieldMonth() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Поле обязательно для заполнения");
    }

    @Test
    void validationRequiredFieldsEmptyFieldYear() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Поле обязательно для заполнения");
    }
    @Test
    void validationRequiredFieldsEmptyFieldOwner() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Поле обязательно для заполнения");
    }
    @Test
    void validationRequiredFieldsEmptyFieldCVC() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldCVC("Поле обязательно для заполнения");
    }
    @Test
    void submittingFormWithEmptyFieldsWithNoData(){
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Поле обязательно для заполнения");
        buyingByCredit.errorsFieldMonth("Поле обязательно для заполнения");
        buyingByCredit.errorsFieldYear("Поле обязательно для заполнения");
        buyingByCredit.errorsFieldOwner("Поле обязательно для заполнения");
        buyingByCredit.errorsFieldCVC("Поле обязательно для заполнения");
    }
    @Test
    void buyingTourByCreditDeclinedStatusCard() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberDeclinedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.getDeclinedMessage();
        assertEquals(selectOrder().getPayment_id(),selectCreditRequest().getBank_id());
        assertEquals("DECLINED", selectCreditRequest().getStatus());
    }
    @Test
    void buyingTourByCreditDeclinedStatusCardRandom() {
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberRandomCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.getDeclinedMessage();
        assertEquals(selectOrder().getPayment_id(),selectCreditRequest().getBank_id());
        assertEquals("DECLINED", selectCreditRequest().getStatus());
    }
    @Test
    void enteringInvalidValuesLatinCardNumberField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberInvalidEnCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesCyrillicCardNumberField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberInvalidRuCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSymbolCardNumberField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateCardSymbol());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSpaceCardNumberField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateCardSpace());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldNumberCard("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesNonExistentMonthField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateInvalidMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Неверно указан срок действия карты");
    }
    @Test
    void enteringElapsedMonthCurrentYearMonthField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generatePreviousMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Неверно указан срок действия карты");
    }
    @Test
    void enteringInvalidValuesLatinMonthField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateInvalidEnMonthYear());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesCyrillicMonthField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateInvalidRuMonthYear());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSymbolMonthField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateSymbolMonthYear());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSpaceMonthField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateSpaceMonthYear());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldMonth("Поле обязательно для заполнения");
    }
    @Test
    void enteringPreviousYearAfterCurrentOneYearField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generatePreviousYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Истёк срок действия карты");
    }
    @Test
    void enteringInvalidValuesLatinYearField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateInvalidEnMonthYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesCyrillicYearField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateInvalidRuMonthYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSymbolYearField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateSymbolMonthYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSpaceYearField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateSpaceMonthYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldYear("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesCyrillicOwnerField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateInvalidRuOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesNumbersOwnerField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateNumberOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSymbolOwnerField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateSymbolOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSpaceOwnerField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateSpaceOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateValidCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldOwner("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesLatinCVCField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateInvalidEnCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldCVC("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesCyrillicCVCField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateInvalidRuCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldCVC("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSymbolCVCField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateSymbolCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldCVC("Поле обязательно для заполнения");
    }
    @Test
    void enteringInvalidValuesSpaceCVCField(){
        buyingByCredit.fillingNumberCardField(DataGenerator.generateNumberApprovedStatusCard());
        buyingByCredit.fillingMonthField(DataGenerator.generateCurrentMonth());
        buyingByCredit.fillingYearField(DataGenerator.generateCurrentYear());
        buyingByCredit.fillingOwnerField(DataGenerator.generateValidEnOwner());
        buyingByCredit.fillingCVCField(DataGenerator.generateSpaceCVC());
        buyingByCredit.subFormWithCardDetails();
        buyingByCredit.errorsFieldCVC("Поле обязательно для заполнения");
    }
    }

