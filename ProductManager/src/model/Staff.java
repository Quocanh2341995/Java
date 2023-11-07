package model;

public class Staff {
    private String name;
    private String adress;
    private String phone;
    private StaffStatus staffStatus;
    private Shift shift;

    public Staff() {
    }

    public Staff(String name, String adress, String phone, StaffStatus staffStatus, Shift shift) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.staffStatus = staffStatus;
        this.shift = shift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }


    @Override
    public String toString() {
        return String.format("| %-7s | %-7s| %-7s | %-8s | %-15s |",
                name, adress, phone, staffStatus, shift);
    }
}
