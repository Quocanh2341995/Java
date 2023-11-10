package service;

import model.*;


import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService implements BasicCRUD<User> {
    public static List<User> listUsers = new ArrayList<>();
    List<Order> orderList = new ArrayList<>();
    OrderService orderService = new OrderService();
    private static int currentId = 0;

    static {
        readData();
    }
    public static void save() {
        writeFile();
    }




    @Override
    public User getByID(int id) {
        return listUsers.stream().filter(us->us.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return listUsers;
    }

    @Override
    public void create(User user) {
        listUsers.add(user);
    }

    @Override
    public void update(User obj) {
        for (User user : listUsers) {
            if (user.getId() == obj.getId()) {
                user = obj;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        listUsers.removeAll(listUsers.stream().filter(us->us.getId() == id).toList());
    }

    @Override
    public boolean isExist(int id) {
        User user = listUsers.stream().filter(b-> Objects.equals(b.getId(), id)).findFirst().orElse(null);
        return user != null;
    }
    public static User getByPhone(String phone) {
        return listUsers.stream().filter(us->Objects.equals(us.getPhone(), phone)).findFirst().orElse(null);
    }
    private static void readData() {
        try{
            File fileWriter = new File("data/users.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                User user = new User(Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        Role.valueOf(data[5]),
                        UserStatus.valueOf(data[6]));

                listUsers.add(user);
                line = reader.readLine();


            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt"));
            for (var user : listUsers) {
                writer.write(user.toStringShow());
                writer.newLine();
            }

            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
