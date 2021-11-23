package org.example.started.server;

import java.sql.SQLException;
import java.util.Random;

public class ServerImperative {

    public static String handleRequest(String requestBody) {
        System.out.println("start handling request with body : " + requestBody);
        return null;
    }

    private static boolean validate(String requestBody) {
        System.out.println("-- perform validation");
        return requestBody.matches("[0-9]+");
    }

    private static Integer parseInt(String number) {
        System.out.println("-- perform parsing");
        return Integer.valueOf(number);
    }

    private static int persist2db(Integer integer) throws SQLException {
        System.out.println("-- perform persistence");
        var dice = new Random().nextInt(10);
        if (dice % 3 == 0) {
            throw new SQLException("500 Internal server error due to bad day");
        }
        return 1;
    }

    private static String buildResponse(String message) {
        System.out.println("-- building response");
        return message;
    }
}
