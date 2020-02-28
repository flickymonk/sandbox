package com.alevel.sandbox.reflection;

public class PrivateFieldHolder {

    @Secret(modifiable = true)
    private String secret = "non-accessible private field";

}