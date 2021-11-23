package org.example.completed.server;

import java.sql.SQLException;
import java.util.Random;

public class ServerImperativeSimple {

    public static String handleRequest(String requestBody) throws SQLException {
        System.out.println("start handling request with body : " + requestBody);
        validate(requestBody);
        var integer = parseInt(requestBody);
        persist2db(integer);
        return buildResponse();
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

    private static String buildResponse() {
        System.out.println("-- building response");
        return "200 OK";
    }
}
