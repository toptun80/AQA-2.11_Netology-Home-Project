package ru.netology.userdata;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UserData {
    private UserData() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class AuthSMSCode {
        String authSMSCode;
    }

    public static AuthSMSCode getAuthSMSCode() throws SQLException {
        String authSMSCode;
        val doSelectCode = "SELECT * FROM auth_codes ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db", "user", "pass"
        );
        ) {
            val code = runner.query(conn, doSelectCode, new BeanHandler<>(AuthCodes.class));
            authSMSCode = code.getCode();
        }
        return new AuthSMSCode(authSMSCode);
    }

    public static void cleanAuthCodes() throws SQLException {
        val doDeleteAuthCodes = "DELETE FROM auth_codes";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "pass")) {
            val cleanAuthCodesUser = runner.execute(conn, doDeleteAuthCodes, new BeanHandler<>(AuthCodes.class));
        }
    }
}
