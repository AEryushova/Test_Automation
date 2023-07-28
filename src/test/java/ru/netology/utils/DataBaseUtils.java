package ru.netology.utils;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {
    private static QueryRunner runner = new QueryRunner();

    private DataBaseUtils() {
    }
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"),System.getProperty("db.user"), System.getProperty("db.password"));
    }

    @SneakyThrows
    public void clearAllData() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.update(connection, "DELETE FROM order_entity");
        runner.update(connection, "DELETE FROM payment_entity");
    }

    @SneakyThrows
    public static CreditRequest selectCreditRequest() {
        var selectCreditRequest = "SELECT bank_id, status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        return runner.query(connection, selectCreditRequest, new BeanHandler<>(CreditRequest.class));
    }

    @SneakyThrows
    public static Order selectOrder() {
        var selectOrder = "SELECT credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        return runner.query(connection, selectOrder, new BeanHandler<>(Order.class));
    }

    @SneakyThrows
    public static Payment selectPayment() {
        var selectPayment = "SELECT transaction_id, status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        return runner.query(connection, selectPayment, new BeanHandler<>(Payment.class));
    }
}
