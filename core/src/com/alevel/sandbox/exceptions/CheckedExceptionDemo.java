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
        }
        throw new CustomCheckedException("Ouch!");
    }
}
