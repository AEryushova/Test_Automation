package ru.netology.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequest {
    private String id;
    private String bank_id;
    private String created;
    private String status;

}
