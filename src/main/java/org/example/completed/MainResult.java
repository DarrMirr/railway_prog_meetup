package org.example.completed;

import org.example.util.Result;

import static org.example.function.Define.def;

public class MainResult {

    public static void main(String[] args) {
        var string2num =
                def((String strNum) ->  {
                    try {
                        return Result.ok(Integer.valueOf(strNum));
                    } catch (NumberFormatException e) {
                        return Result.<Integer>error(e);
                    }
                });

        var result1 = string2num.apply("3");
        var result2 = string2num.apply("test");

        System.out.println("parsing '3' result ok: " + result1.get());
        System.out.println("parsing '3' result error : " + result1.error());
        System.out.println("parsing 'test' result ok : " + result2.get());
        System.out.println("parsing 'test' result error : " + result2.error());

        System.out.println("----------------------------");

        var result3 = result1.map(num -> num * num);

        System.out.println("Result 3, result ok after map : " + result3.get());
        System.out.println("Result 3, result error after map  : " + result3.error());

        System.out.println("----------------------------");

        var result4 = result2.map(num -> num * num);

        System.out.println("Result 4, result ok after map : " + result4.get());
        System.out.println("Result 4, result error after map : " + result4.error());
    }
}
