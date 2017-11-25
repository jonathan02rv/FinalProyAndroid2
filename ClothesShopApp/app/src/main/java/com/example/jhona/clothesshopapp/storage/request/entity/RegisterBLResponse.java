package com.example.jhona.clothesshopapp.storage.request.entity;

/**
 * Created by arg_r on 11/21/2017.
 */

/*{
        "userStatus": "ENABLED",
        "created": 1511319985254,
        "___class": "Users",
        "name": "Paolo",
        "last_name": "Guerrero Gonzales",
        "ownerId": "24B9E4DA-2CD8-45E8-FF2E-751A3EDE4F00",
        "updated": null,
        "dni": "75784526",
        "email": "paolo@abc.com",
        "objectId": "24B9E4DA-2CD8-45E8-FF2E-751A3EDE4F00",
        "__meta": "{\"relationRemovalIds\":{},\"selectedProperties\":[\"password\",\"created\",\"___saved\",\"___class\",\"name\",\"last_name\",\"ownerId\",\"updated\",\"dni\",\"email\",\"objectId\"],\"relatedObjects\":{}}"
        }*/

public class RegisterBLResponse {

    private String objectId;
    private String email;
    private String dni;
    private String name;
    private String last_name;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
