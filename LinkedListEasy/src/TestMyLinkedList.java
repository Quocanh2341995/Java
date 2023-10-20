public class TestMyLinkedList {
    public static void main(String[] args) {
        MyLinkedList test = new MyLinkedList(10);

        test.addFirst(15);
        test.addFirst(23);
        test.addFirst(04);

//        test.printList();

        test.add(3, 8);

        test.printList();

        System.out.println("data 4 :"+ test.get(4).getData());
    }
}


