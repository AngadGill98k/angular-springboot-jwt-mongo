package com.example.Backend.dto;

public class User_dto {
    private String id;
    private String name;
    private String mail;


    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setMail(String mail) {
        this.mail = mail;
    }
}
