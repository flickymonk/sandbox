package com.alevel.sandbox.reflection;

public class PrivateFieldHolder {

    @Secret(modifiable = true)
    @TestOnly(TestType.DEBUG)
    private String secret = "non-accessible private field";

}