package com.example.Backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String mail;
    private String pass;

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaas(String paas) {
        this.pass = paas;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

