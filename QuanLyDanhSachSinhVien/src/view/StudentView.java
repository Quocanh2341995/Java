package view;

import model.Student;
import service.StudentService;

import java.util.Scanner;

import static Utils.AppUtils.*;
import static service.StudentService.readData;
import static service.StudentService.writeFile;

public class StudentView {
    static Scanner sc = new Scanner(System.in);
    static StudentService studentService = new StudentService();
    public static void printMenu() {
        while (true) {
            System.out.println("-----Menu-----");
            System.out.println("1. Xem danh sach sinh vien.");
            System.out.println("2. Them moi.");
            System.out.println("3. Cap nhat.");
            System.out.println("4. Xoa.");
            System.out.println("5. Sap xep.");
            System.out.println("6. Doc tu file.");
            System.out.println("7. Ghi tu file.");
            System.out.println("0. Thoat.");
            int choice = getNumberMinMax("Nhap vao lua chon:", 0, 8);
            switch (choice) {
                case 1 :
                    studentService.sortByIdStudent();
                    printStudent();
                    break;
                case 2 :
                    createStudent();
                    break;
                case 3 :
                    updateStudent();
                    break;
                case 4 :
                    deleteStudent();
                    break;
                case 5 :
                    sortScoreStudent();
                    break;
                case 6 :
                    readData();
                case 7 :
                    writeFile();
            }
        }
    }


    private static void sortScoreStudent() {
        System.out.println("1. Sap xep theo diem trung binh tang dan.");
        System.out.println("2. Sap xep theo diem trung binh giam dan.");
        System.out.println("0. Thoat.");
        int choose = getNumberMinMax("Nhap vao lua chon:", 0, 2);
        switch (choose) {
            case 1 :
                studentService.sortSorceIncreaseStudent();
                printStudent();
                break;
            case 2 :
                studentService.sortSorceDecreaseStudent();
                printStudent();
                break;
        }

    }

    private static void deleteStudent() {
        printStudent();
        boolean isSuccess = studentService.deleteStudent(getString("Nhap ma sinh vien muon xoa:"));
        if (isSuccess) {
            System.out.println("Xoa sinh vien thanh cong.");
        } else {
            System.out.println("Khong tim thay ma sinh vien.");
        }

    }

    private static void updateStudent() {
        printStudent();
        boolean isSuccess = studentService.updateStudent(new Student(getString("Nhap ma sinh vien muon cap nhat"),
                getString("Nhap ten moi sinh vien:"),
                Integer.parseInt(getString("Nhap tuoi moi sinh vien")),
                getString("Nhap gioi moi tinh sinh vien:"),
                getString("Nhap dia chi moi sinh vien:"),
                Float.parseFloat(getString("Nhap diem trung binh moi sinh vien"))));
        if (isSuccess) {
            System.out.println("Cap nhat sinh vien thanh cong.");
        } else {
            System.out.println("Khong tim ma sinh vien.");
        }
    }

    private static void createStudent() {
        studentService.createStudent(new Student(getString("Nhap ma sinh vien:"),
                getString("Nhap ten sinh vien:"),
                Integer.parseInt(getString("Nhap tuoi sinh vien")),
                getString("Nhap gioi tinh sinh vien:"),
                getString("Nhap dia chi sinh vien:"),
                Float.parseFloat(getString("Nhap diem trung binh sinh vien"))));
        System.out.println("Them sinh vien thanh cong!");
    }

    private static void printStudent() {
        System.out.println("Ma sinh vien:        | Name:        | Age:        | Sex:        | Address:        | Score:");
        for (var student : studentService.getStudent()) {
            System.out.printf("%-10s        | %-10s         | %-10s        | %-10s        | %-10s        | %-10f%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getGender(),
                    student.getAddress(),
                    student.getScore());
        }
    }


}
