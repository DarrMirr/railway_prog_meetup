package org.example.completed;

import org.example.completed.server.ServerFunctional;
import org.example.completed.server.ServerImperative;
import org.example.completed.server.ServerImperativeSimple;

public class MainRailway {

    public static void main(String[] args) {
        try {
            var result1 = ServerImperativeSimple.handleRequest("3");
            System.out.println("request handler result1 : " + result1);
        } catch (Exception e) { }

        System.out.println("----------------------------");

        var result2 = ServerImperative.handleRequest("3");
        System.out.println("request handler result2 : " + result2);

        System.out.println("----------------------------");

        var result3 = ServerFunctional.handleRequest("3");
        System.out.println("request handler result3 : " + result3);

    }

}
