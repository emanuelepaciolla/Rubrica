package com.example.emanuelepaciolla.rubrica;

/**
 * Created by Emanuele Paciolla on 11/03/2017.
 */

public class Contact {

    //field
    private String name;
    private String surname;
    private String address = "";
    private String cellphone;
    private String email = "";
    private int ID;

    //Costructor

    public Contact(int ID, String name, String surname, String cellphone, String email, String address) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
        this.email = email;
        this.address = address;
    }

    public Contact(String name, String surname, String cellphone) {
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
    }

    Contact(String name, String surname, String address, String cellphone, String email){
        this.name= name;
        this.surname=surname;
        this.address=address;
        this.cellphone=cellphone;
        this.email=email;
    }
    Contact(){}

    //getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
