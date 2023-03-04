package com.example.beefit;

public class Customers {

    String Name;
    String Email;
    String Password;
    String Age;

    public Customers(String name, String email, String password, String age) {
        Name = name;
        Email = email;
        Password = password;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getAge() {
        return Age;
    }
}
