package model;


public class User {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String pass;
    private Role role;
    private UserStatus userStatus;

    public User(int id, String name, String address, String phone, String pass, Role role, UserStatus userStatus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pass = pass;
        this.role = role;
        this.userStatus = userStatus;
    }

    public User() {
    }

    public User(String name, String address, String phone, String pass, Role role, UserStatus userStatus) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pass = pass;
        this.role = role;
        this.userStatus = userStatus;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-13s | %-15s | %-20s | %-10s | %-10s | %-15s |%n",
                getId(),getName(),getAddress(),getPhone(),"******" ,getRole() ,getUserStatus()) ;
    }


    public String toStringShow() {
        return id + "," + name + "," + address + "," + phone + "," + pass + "," + role + "," + userStatus;
    }
}
