package org.example.started.server;

import org.example.util.Result;

import java.sql.SQLException;
import java.util.Random;

public class ServerFunctional {

    public static String handleRequest(String requestBody) {
        System.out.println("start handling request with body : " + requestBody);
        return null;
    }

    private static Result<String> validate(String requestBody) {
        System.out.println("-- perform validation");
        if(!requestBody.matches("[0-9]+")) {
          return Result.error(new IllegalStateException("validate error"));
        }
        return Result.ok(requestBody);
    }

    private static Result<Integer> parseInt(String number) {
        System.out.println("-- perform parsing");
        try {
            return Result.ok(Integer.valueOf(number));
        } catch (NumberFormatException e) {
            return Result.error(e);
        }
    }

    private static Result<Integer> persist2db(Integer integer) {
        System.out.println("-- perform persistence");
        var dice = new Random().nextInt(10);
        if (dice % 3 == 0) {
            return Result.error(new SQLException("500 Internal server error due to bad day"));
        }
        return Result.ok(1);
    }

    private static String buildResponse(String message) {
        System.out.println("-- building response");
        return message;
    }
}
