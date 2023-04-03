package com.example.beefit;

public class User {

    public String fullName, userName, age, email;
    public int gymLevel, howManyTimesAWeek;

    //0 is null
    /*
    gym level
    1 = beginner
    2 = intermidate
    3 = gymrat

    howManyTimesAweek
    1 = 1-2
    2 = 3-4
    3 = 5.6
     */


    public User() {
    }

    public User(String fullName, String userName, String age, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.age = age;
        this.email = email;
        gymLevel = 2;
        howManyTimesAWeek = 2;
    }
}
