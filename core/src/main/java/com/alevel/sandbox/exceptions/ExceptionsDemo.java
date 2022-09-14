package com.alevel.sandbox.exceptions;

public class ExceptionsDemo {

    public static void main(String[] args) {
        divideByZero();

        throwAndCatchChecked();

        try {
            throwUnchecked();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            throwChecked();
        } catch (CustomCheckedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("We are done");
        }
    }

    private static void divideByZero() {

        try {
            int i = 1 / 0;
            System.out.println("Oh, I divided by 0! I got " + i);
        } catch (ArithmeticException e) {
            System.err.println("Hello from the catch block!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Generic exceptions");
            e.printStackTrace();
        } finally {
            System.err.println("I knew it was a bad idea");
        }

    }

    private static void throwUnchecked() {
        throw new CustomRuntimeException("Oh, no!");
    }

    private static void throwAndCatchChecked() {
        try {
            throw new CustomCheckedException("Whoopsie!"); //won't compile unless caught
        } catch (CustomCheckedException e) {
            e.printStackTrace();
        }

    }

    private static void throwChecked() throws CustomCheckedException {
        throw new CustomCheckedException("Ouch!");
    }

}
