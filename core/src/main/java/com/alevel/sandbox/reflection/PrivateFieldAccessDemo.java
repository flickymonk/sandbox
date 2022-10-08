package com.alevel.sandbox.reflection;

import java.lang.reflect.Field;
import java.util.Scanner;

public class PrivateFieldAccessDemo {

    public static void main(String[] args) {

        try {
            Class<?> classOfInstance = Class.forName(new Scanner(System.in).nextLine());
            Object instance = classOfInstance.getConstructor().newInstance();

            Field secret = classOfInstance.getDeclaredField("secret");

            secret.setAccessible(true);

            String secretValueForInstance = ((String) secret.get(instance));

            System.out.println(secretValueForInstance);

            Secret secretAnnotation = secret.getAnnotation(Secret.class);
            if (secretAnnotation == null) return;

            if (secretAnnotation.modifiable()) {
                secret.set(instance, "modified via reflection");
                System.out.println(secret.get(instance));
            }

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

    }
}
