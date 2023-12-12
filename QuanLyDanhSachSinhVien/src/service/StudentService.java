package service;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService {
    private static final List<Student> students = new ArrayList<>();
    private static int currentId = 0;
    static {
        students.add(new Student("C0823K1", "QuocAnh1", 27, "Nam", "Hue", 8.5f));
        students.add(new Student("C0823K2", "QuocAnh2", 27, "Nam", "Hue", 7.5f));
        students.add(new Student("C0823K3", "QuocAnh3", 27, "Nu", "Hue", 9.5f));
        students.add(new Student("C0823K4", "QuocAnh4", 27, "Nam", "Hue", 6.5f));
        students.add(new Student("C0823K5", "QuocAnh5", 27, "Nam", "Hue", 5.5f));
        readData();

    }

    public List<Student> getStudent() {
        return students;
    }

    public void createStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(Student student) {
        for (var student1:students) {
            if (student1.getStudentId().equals(student.getStudentId())) {
                student1.setName(student.getName());
                student1.setAge(student.getAge());
                student1.setGender(student.getGender());
                student1.setAddress(student.getAddress());
                student1.setScore(student.getScore());
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                return true;
            }
            writeFile();
        }
        return false;

    }

    public void sortSorceIncreaseStudent() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Float.compare(student1.getScore(),student2.getScore());
            }
        });
    }

    public void sortSorceDecreaseStudent() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Float.compare(student2.getScore(),student1.getScore());
            }
        });
    }
    public static void readData(){
        try{
            File fileWriter = new File("src/data/students.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            students.clear();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");

                Student student = new Student(data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        data[4],
                        Float.parseFloat(data[5])
                );

                students.add(student);
                line = reader.readLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/students.txt"));
            for (var student :students) {
                writer.write(student.toString());
                writer.newLine();
            }


            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void sortByIdStudent() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getStudentId().compareTo(student2.getStudentId());
            }
        });
    }
}
