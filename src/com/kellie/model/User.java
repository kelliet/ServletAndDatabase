package com.kellie.model;

/**
 * Created by kellie on 11/22/16.
 */
public class User {

    //private instance variable - so we create getters and setters
    private String name;
    private String specialMessage;
    private boolean firstLogin = true;

    //default constructor
    public User() {
    }

    //overloading the constructor
    public User(String name, String specialMessage) {
        setName(name);
        setSpecialMessage(specialMessage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialMessage() {
        return specialMessage;
    }

    public void setSpecialMessage(String specialMessage) {
        this.specialMessage = specialMessage;
    }

    //boolean getter begins with is
    public boolean isFirstLogin() {
        if(firstLogin) {
            firstLogin = false;
            return true;
        }
        return false;
    }
}
