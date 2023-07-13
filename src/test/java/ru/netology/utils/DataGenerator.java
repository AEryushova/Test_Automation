package ru.netology.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;

public class DataGenerator {
    public DataGenerator() {
    }

    private final static Faker fakerRu = new Faker(new Locale("ru"));
    private final static Faker fakerEn = new Faker(new Locale("en"));


    public static String generateNumberApprovedStatusCard() {
        return generateNumberCardForField(String.valueOf(fakerRu.number().numberBetween(4441, 4441)));
    }

    public static String generateNumberDeclinedStatusCard() {
        return generateNumberCardForField(String.valueOf(fakerRu.number().numberBetween(4442, 4442)));
    }

    public static String generateNumberIncompleteCard() {
        return generateNumberCardForField(String.valueOf(fakerRu.number().numberBetween(444, 444)));
    }

    public static String generateNumberBoundaryCard() {
        return generateNumberCardForField(String.valueOf(fakerRu.number().numberBetween(44414, 44414)));
    }

    public static String generateNumberCardForField(String card) {
        String firstPast = String.valueOf(fakerRu.number().numberBetween(4444, 4444));
        return String.join(" ", firstPast, firstPast, firstPast, card);
    }

    public static String generateNumberRandomCard() {
        return fakerRu.business().creditCardNumber();
    }

    public static String generateNumberInvalidEnCard() {
        return fakerEn.name().lastName();
    }

    public static String generateNumberInvalidRuCard() {
        return fakerRu.name().lastName();
    }

    public static String generateCardSymbol() {
        return RandomStringUtils.randomNumeric(16);

    }

    public static String generateCardSpace() {
        String cardSpace = "                ";
        return cardSpace;
    }

    public static String generateCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        return month;
    }

    public static String generatePreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        return month;
    }


    public static String generateIncompleteMonth() {
        return String.valueOf(fakerRu.number().numberBetween(0, 1));
    }

    public static String generateBoundaryMonth() {
        return generateCurrentMonth().concat(String.valueOf(fakerRu.number().numberBetween(1, 9)));

    }

    public static String generateInvalidMonth() {
        int ivnMonth = fakerRu.number().numberBetween(13, 99);
        return String.valueOf(ivnMonth);
    }

    public static String generateInvalidEnMonthYear() {
        return RandomStringUtils.randomAlphabetic(2).toLowerCase();
    }

    public static String generateInvalidRuMonthYear() {
        String randomName = fakerRu.name().firstName();
        char[] array = new char[2 - 0];
        randomName.getChars(0, 2, array, 0);
        String value = Arrays.toString(array);
        return value;
    }

    public static String generateSymbolMonthYear() {
        return RandomStringUtils.randomNumeric(2);
    }

    public static String generateSpaceMonthYear() {
        String monthYearSpace = "  ";
        return monthYearSpace;
    }

    public static String generateCurrentYear() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generatePreviousYear() {
        return LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateIncompleteYear() {
        return String.valueOf(fakerRu.number().numberBetween(1, 9));
    }

    public static String generateBoundaryYear() {
        return generateCurrentYear().concat(generateIncompleteYear());
    }

    public static String generateValidEnOwner() {
        return fakerEn.name().fullName();
    }

    public static String generateInvalidRuOwner() {
        return fakerRu.name().fullName();
    }

    public static String generateDoubleSurnameEnOwner() {
        return String.join("-", generateValidEnOwner(), fakerEn.name().lastName());
    }

    public static String generateIncompleteOwner() {
        return RandomStringUtils.randomAlphabetic(1);
    }

    public static String generateBoundaryOwner() {
        return RandomStringUtils.randomAlphabetic(2);
    }

    public static String generateMaxBoundaryOwner() {
        return RandomStringUtils.randomAlphabetic(43);
    }

    public static String generateSymbolOwner() {
        return RandomStringUtils.randomNumeric(10);
    }

    public static String generateSpaceOwner() {
        String spaceOwner = "           ";
        return spaceOwner;
    }

    public static String generateNoNameOwner() {
        String noName = "No name";
        return noName;
    }

    public static String generateNumberOwner() {
        int numberOwner = fakerRu.number().numberBetween(1000000000, 2000000000);
        return String.valueOf(numberOwner);
    }

    public static String generateValidCVC() {
        int cvc = fakerRu.number().numberBetween(100, 999);
        return String.valueOf(cvc);
    }

    public static String generateInvalidEnCVC() {
        return RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }

    public static String generateInvalidRuCVC() {
        String randomName = fakerRu.name().firstName();
        char[] array = new char[3 - 0];
        randomName.getChars(0, 3, array, 0);
        String value = Arrays.toString(array);
        return value;
    }

    public static String generateIncompleteCVC() {
        int invShortCvc = fakerRu.number().numberBetween(10, 99);
        return String.valueOf(invShortCvc);
    }

    public static String generateBoundaryCVC() {
        String invLongCvc = "4041";
        return invLongCvc;

    }

    public static String generateSymbolCVC() {
        return RandomStringUtils.randomNumeric(3);
    }

    public static String generateSpaceCVC() {
        String spaceOwner = "   ";
        return spaceOwner;
    }
}
