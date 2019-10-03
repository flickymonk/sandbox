package com.alevel.sandbox.exceptions;

public class CheckedExceptionDemo {
    public static void main(String[] args) {
        try {
            m();
        } catch (CustomCheckedException e) {
            e.printStackTrace();
        }
    }

    private static void m() throws CustomCheckedException {
        try {
            throw new CustomCheckedException("Ooops");
        } catch (CustomCheckedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Error!");
            throw new RuntimeException(e);
        } finally {
            System.out.println("Hello from finally block!");
        }
        throw new CustomCheckedException("Ouch!");
    }
}
