package models;

import services.UserService;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
    private int id;
    private String name;

    private String address;
    private String phoneNumber;

    private String password;

    private Role role;
    private UserStatus userStatus;


    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public User(String name, String address, String phoneNumber, String password, Role role, UserStatus userStatus) {
        this.id = UserService.listUsers.size() + 1;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public User(int id, String name, String address, String phoneNumber, String password, Role role, UserStatus userStatus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;

    }


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-13s | %-15s | %-20s | %-10s | %-10s | %-15s |%n",
                getId(), getName(), getAddress(), getPhonenumber(), "******", getRole(), getUserStatus());
    }
}