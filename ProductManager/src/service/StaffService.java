package service;

import model.Category;
import model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffService {
    private static List<Staff> staffs = new ArrayList<>();
    public List<Staff> findAll() {

        return staffs;
    }
    public Staff findByName(String name) {
        return staffs.stream().filter(e -> e.getName() == name).findFirst().orElse(null);
    }
}
