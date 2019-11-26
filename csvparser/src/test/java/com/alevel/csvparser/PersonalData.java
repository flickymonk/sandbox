package com.alevel.csvparser;

public class PersonalData {

    @CSVColumn("name")
    private String name;

    @CSVColumn("age")
    private int age;

    @CSVColumn("gender")
    private Gender gender;

    @CSVColumn("occupation")
    private String occupation;

    enum Gender {MALE, FEMALE}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
