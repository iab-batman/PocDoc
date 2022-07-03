package com.example.pocdoc;

import androidx.annotation.NonNull;

public class user {
    String name,password,email,cellno ;

    public user(String name, String password, String email, String cellno) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.cellno = cellno;
    }
    public user(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }
}
