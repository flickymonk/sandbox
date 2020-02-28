package com.alevel.sandbox.reflection;

import java.lang.reflect.Field;

public class PrivateFieldAccessDemo {

    public static void main(String[] args) {
        PrivateFieldHolder instance = new PrivateFieldHolder();

        try {
            Class<? extends PrivateFieldHolder> classOfInstance = instance.getClass();

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

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
