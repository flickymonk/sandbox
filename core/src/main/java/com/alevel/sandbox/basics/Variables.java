package com.alevel.sandbox.basics;

public class Variables {

    public static void main(String[] args) { // {} define scope
        // Declaration: Type name;
        String s;

        // won't compile - not defined
        // System.out.println(s);

        // assignment: name = value;
        s = "string";

        System.out.println(s);

        // reassignment
        s = "new string";
        System.out.println(s);

        // won't compile - already defined in scope
        // String s = "another string";
        {
            String scoped = "scoped string 1";
            System.out.println(scoped);
        }
        String scoped = "scoped string 2";
        System.out.println(scoped);

        //All object types can be null
        s = null;
        //noinspection ConstantConditions
        System.out.println(s);

        s = "1234";
        System.out.println(s.length());
        System.out.println(s.concat("56"));
        System.out.println("   my string    \n".trim());
        System.out.println(s.replace('3', 'z'));
        System.out.println((s + "12").replace("12", "ab"));
        System.out.println("AAAAAAAA!".toLowerCase());
        System.out.println("aaaaaaaa!".toUpperCase());
    }

}
