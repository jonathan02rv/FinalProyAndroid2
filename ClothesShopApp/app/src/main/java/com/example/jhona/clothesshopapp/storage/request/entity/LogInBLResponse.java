package com.example.jhona.clothesshopapp.storage.request.entity;

import com.google.gson.annotations.SerializedName;

/*
{
    "ownerId": "7AD2B842-910E-ECE7-FF1B-B9DFFF6E0800",
    "email": "admin@gmail.com",
    "objectId": "7AD2B842-910E-ECE7-FF1B-B9DFFF6E0800",
    "lastname": "Admin",
    "updated": 1510359887344,
    "name": "Admin",
    "created": 1510359816663,
    "userStatus": "ENABLED",
    "lastLogin": 1510360415974,
    "___class": "Users",
    "__meta": "{\"relationRemovalIds\":{},\"selectedProperties\":[\"ownerId\",\"email\",\"objectId\",\"lastname\",\"updated\",\"name\",\"password\",\"created\",\"userStatus\",\"lastLogin\",\"__updated__meta\",\"___class\"],\"relatedObjects\":{}}",
    "user-token": "73034DA7-61AE-331B-FF73-2BA295394400"
}
 */
public class LogInBLResponse {
    private String objectId;
    private String dni;
    private String email;
    private String name;
    private String lastName;

    @SerializedName("user-token")
    private String token;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
