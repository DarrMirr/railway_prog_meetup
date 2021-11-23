package org.example.completed;

import org.example.function.Either;

import static org.example.function.Define.*;

public class MainEither {

    public static void main(String[] args) {
        var string2num =
                def((String strNum) -> {
                    try {
                        return Either.<Exception, Integer>right(Integer.valueOf(strNum));
                    } catch (NumberFormatException e) {
                        return Either.<Exception, Integer>left(e);
                    }
                });

        var eitherResult1 = string2num.apply("3");
        var eitherResult2 = string2num.apply("test");

        System.out.println("Either.Left result in parsing '3' : " + eitherResult1.left());
        System.out.println("Either.Right result in parsing '3' : " + eitherResult1.right());
        System.out.println("Either.Left result in parsing 'test' : " + eitherResult2.left());
        System.out.println("Either.Right result in parsing 'test' : " + eitherResult2.right());

        System.out.println("----------------------------");

        var eitherResult3 = eitherResult1.mapRight(num -> num * num);

        System.out.println("Either result 3 : " + eitherResult3.left());
        System.out.println("Either result 3 : " + eitherResult3.right());

        System.out.println("----------------------------");

        var eitherResult4 = eitherResult2.mapRight(num -> num * num);

        System.out.println("Either result 4 : " + eitherResult4.left());
        System.out.println("Either result 4 : " + eitherResult4.right());
    }
}
