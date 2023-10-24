class Student {
    private String name;
    private String classes;

    public String getName() {
        return name;
    }

    public String getClasses() {
        return classes;
    }

    public Student() {
        this.name = "John";
        this.classes = "C02";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }



}

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("AAA");
        s1.setClasses("C03");

        System.out.println("Name:" + s1.getName());
        System.out.println("Classes:" + s1.getClasses());
    }
}
