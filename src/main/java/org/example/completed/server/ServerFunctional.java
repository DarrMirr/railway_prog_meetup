package org.example.completed.server;

import org.example.util.Result;

import java.sql.SQLException;
import java.util.Random;

public class ServerFunctional {

    public static String handleRequest(String requestBody) {
        System.out.println("start handling request with body : " + requestBody);
        var message = validate(requestBody)
                .flatMap(ServerFunctional::parseInt)
                .flatMap(ServerFunctional::persist2db)
                .map(rowCount -> rowCount == 1 ? "200 OK" : "500 Internal server error due to row count incorrect")
                .orElse(Throwable::getMessage);
        return buildResponse(message);
    }

    private static Result<String> validate(String requestBody) {
        System.out.println("-- perform validation");
        if (requestBody.matches("[0-9]+")) {
            return Result.ok(requestBody);
        } else {
            return Result.error(new IllegalStateException("400 Bad request"));
        }
    }

    private static Result<Integer> parseInt(String number) {
        System.out.println("-- perform parsing");
        return Result.ok(Integer.valueOf(number));
    }

    private static Result<Integer> persist2db(Integer integer) {
        System.out.println("-- perform persistence");
        try {
            var dice = new Random().nextInt(4);
            if (dice % 3 == 0) {
                throw new SQLException("500 Internal server error due to bad day");
            }
        } catch (SQLException e) {
            return Result.error(e);
        }
        return Result.ok(1);
    }

    private static String buildResponse(String message) {
        System.out.println("-- building response");
        return message;
    }
}
