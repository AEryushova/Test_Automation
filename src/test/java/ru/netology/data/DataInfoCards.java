package ru.netology.data;

import lombok.Value;

@Value
public class DataInfoCards {

    private final String numberRandomCard;
    private final String month;
    private final String year;
    private final String owner;
    private final String cvc;
}