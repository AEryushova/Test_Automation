package ru.netology.utils;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.DriverManager;

public class DataBaseUtils {

    @SneakyThrows
    public void clearAllData() {
        var runner = new QueryRunner();
        var clearCreditRequest = "DELETE FROM credit_request_entity";
        var clearOrder = "DELETE FROM order_entity";
        var clearPayment = "DELETE FROM payment_entity";
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            runner.update(connection, clearCreditRequest);
            runner.update(connection, clearOrder);
            runner.update(connection, clearPayment);
        }
    }

    @SneakyThrows
    public static CreditRequest selectCreditRequest() {
        var runner = new QueryRunner();
        var selectCreditRequest = "SELECT bank_id, status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(connection, selectCreditRequest, new BeanHandler<>(CreditRequest.class));
        }
    }

    @SneakyThrows
    public static Order selectOrder() {
        var runner = new QueryRunner();
        var selectOrder = "SELECT credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(connection, selectOrder, new BeanHandler<>(Order.class));
        }
    }

    @SneakyThrows
    public static Payment selectPayment() {
        var runner = new QueryRunner();
        var selectPayment = "SELECT transaction_id, status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (
                var connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            return runner.query(connection, selectPayment, new BeanHandler<>(Payment.class));
        }
    }
}
