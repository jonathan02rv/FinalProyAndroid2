package com.example.jhona.clothesshopapp.storage.request.entity;

/**
 * Created by arg_r on 11/21/2017.
 */

/*{
        "email": "paolo@abc.com",
        "password": "987654",
        "dni": "75784526",
        "name": "Paolo",
        "last_name": "Guerrero Gonzales"
        }*/

public class RegisterBLRaw {

    private String email;
    private String password;
    private String dni;
    private String name;
    private String last_name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
