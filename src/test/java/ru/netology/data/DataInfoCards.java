package ru.netology.data;

import lombok.Data;

@Data
public class DataInfoCards {

    private final String numberRandomCard;
    private final String month;
    private final String year;
    private final String owner;
    private final String cvc;

    public DataInfoCards(String numberRandomCard, String month, String year, String owner, String cvc) {
        this.numberRandomCard = numberRandomCard;
        this.month = month;
        this.year = year;
        this.owner = owner;
        this.cvc = cvc;
    }

    public String getNumberCard() {
        return numberRandomCard;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvc() {
        return cvc;
    }
}