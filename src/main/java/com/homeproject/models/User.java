package com.homeproject.models;

import java.time.LocalDate;

public class User {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String age;
    private String gender;
    private LocalDate dateOfBorn;
    private String inn;
    private String index;
    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String flat;

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPatronymic(){
        return patronymic;
    }

    public String getAge(){
        return age;
    }

    public String getGender(){
        return gender;
    }

    public String getState(){
        return state;
    }

    public String getCity(){
        return city;
    }

    public String getStreet(){
        return street;
    }

    public LocalDate getDateOfBorn(){
        return dateOfBorn;
    }

    public String getInn(){
        return inn;
    }

    public String getIndex(){
        return index;
    }

    public String getHouse(){
        return house;
    }

    public String getFlat(){
        return flat;
    }

    public String getCountry(){
        return country;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBorn(LocalDate dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

}
