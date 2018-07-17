package com.alevel.sandbox.exceptions;

import java.util.Random;

public class MultiCatchDemo {
    public static void main(String[] args) {

        try {
            Random rnd = new Random();
            boolean b = rnd.nextBoolean();
            if (b) {
                throw new CustomCheckedException("True!");
            } else {
                throw new Exception("False");
            }
        } catch (CustomCheckedException | CustomRuntimeException | IllegalArgumentException e) {
            System.err.println("It's my exception!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Eh, finally!");
        }

    }
}
