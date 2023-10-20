public class MyListText {
    public static void main(String[] args) {
        MyList<Integer> listInteger = new MyList<Integer>();

        listInteger.add(1);
        listInteger.add(2);
        listInteger.add(3);
        listInteger.add(4);
        listInteger.add(5);

        System.out.println("element 1:"+ listInteger.get(1));
        System.out.println("element 3:"+ listInteger.get(3));

        listInteger.get(-1);
        System.out.println("element -1:"+ listInteger.get(-1));
    }
}
