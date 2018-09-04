package com.alevel.sandbox.basics;

public class Operators {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {

        System.out.println();

        //math (+ - * / %)
        System.out.println("Mathematical operators: " + System.lineSeparator());

        //plus
        int i = 1 + 1;
        System.out.println("i = 1 + 1 = " + i);
        System.out.println("-i = " + -i);

        //minus
        i = 2 - 1;
        System.out.println("i = 2 - 1 = " + i);

        //increment
        i++;
        System.out.println("after i++, i = " + i);

        //increment postfix form
        System.out.println("i++ = " + i++);
        System.out.println("after i++, i = " + i);

        //increment prefix form
        System.out.println("++i = " + ++i);

        //same as i = i - 1;
        i -= 1;
        System.out.println("after i -= 1, i = " + i);

        //same as i = i * 2;
        i *= 2;
        System.out.println("after i *= 2, i = " + i);

        //same as i = i / 2
        i /= 2;
        System.out.println("after i /= 2, i = " + i);

        //mod 2
        System.out.println("i % 2 = " + i % 2);
        //mod 3
        System.out.println("i % 3 = " + i % 3);

        //same as i = i / 3
        i /= 3;
        System.out.println("after i /= 3, i = " + i);

        //decrement, postfix form
        i--;
        System.out.println("after i--, i = " + i);

        System.out.println();

        //comparison
        System.out.println("Comparison operators: " + System.lineSeparator());

        //equal
        boolean b = i == 0;
        System.out.println("i == 0 is " +  b);

        //lesser
        b = i < 100;
        System.out.println("i < 100 is " + b);

        //greater
        b = i > 100;
        System.out.println("i > 100 is " + b);

        //lesser or equal
        b = i <= 0;
        System.out.println("i <= 0 is " + b);

        //greater or equal
        b = i >= 1;
        System.out.println("i >= 1 is " + b);

        //not equal
        b = i != 0;
        System.out.println("i != 0 is " + b);

        System.out.println();

        //logical
        System.out.println("Logical operators: " + System.lineSeparator());

        boolean t = true;
        boolean f = false;

        System.out.println("true && true = " + (t && t));
        System.out.println("true && false = " + (t && f));
        System.out.println("false && false = " + (f && f));
        System.out.println("true || false = " + (t || f));
        System.out.println("true || true = " + (t || t));
        System.out.println("false || false = " + (f || f));
        System.out.println("!true = " + !t);

        System.out.println();

        //ternary
        System.out.println("Ternary operator: " + System.lineSeparator());

        int value = 10;

        String even = (value % 2 == 0) ? "even" : "not even";
        System.out.println("10 is " + even);

        value = 1;
        even = value % 2 == 0 ? "even" : "not even";
        System.out.println("1 is " + even);

        System.out.println();

        //bitwise
        System.out.println("Bitwise operators: " + System.lineSeparator());

        int five  = 0b0101;
        int seven = 0b0111;
        // XOR
        System.out.println("five XOR seven = " + (five ^ seven)); // 0010
        // OR
        System.out.println("five OR seven = " + (five | seven));  // 0111
        // AND
        System.out.println("five AND seven = " + (five & seven)); // 0101
        // Complement
        System.out.println("~0101 = " + ~five);                   // -6
        // 32 bits
        // 0000 0000 0000 0000 0000 0000 0000 0101
        // 1111 1111 1111 1111 1111 1111 1111 1010
        int minusSix = 0b1111_1111_1111_1111_1111_1111_1111_1010;
        System.out.println("minusSix = " + minusSix);
        System.out.println("check it: " + (~five == minusSix));

        System.out.println();

        //bit shift
        System.out.println("Bit shift operators: " + System.lineSeparator());

        System.out.println("1 << 4 = " + (1 << 4));       // 1 -> 10000
        System.out.println("4 >> 4 = " + (4 >> 4));       // 100 -> 1
        System.out.println("-8 >> 2 = " + (-8 >> 2));     // -1000 -> -10
        System.out.println("-8 >>> 2 = " + (-8 >>> 2));   // wtf
        System.out.println("4 >>> 2 = " + (4 >>> 2));     // 100 -> 1

    }

}
