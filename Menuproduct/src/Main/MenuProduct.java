package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MenuProduct {
    private ArrayList<Product> menu;

    public MenuProduct() {
        this.menu = new ArrayList<Product>();
    }
    public MenuProduct(ArrayList<Product> menu) {
        this.menu = menu;
    }

    // 1. them san pham moi vao menu.
    public void addProduct(Product Pr) {
        this.menu.add(Pr);
    }

    // 2. in menu ra man hinh
    public void printProduct() {
        for (Product pr: menu) {
            System.out.println(pr);
        }
    }
    // 4. xoa san pham theo ten
    public boolean remove(Product pr){
        return this.menu.remove(pr);
    }

    // 5. sap xep san pham theo gia tu cao den thap
    public void arrange(){
        Collections.sort(this.menu, new Comparator<Product>() {
            @Override
            public int compare(Product pr1, Product pr2) {
                if(pr1.getPrice() > pr2.getPrice()) {
                    return -1;
                } else if (pr1.getPrice() < pr2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    // 6. tim kiem theo ten san pham
    public void find(String name){
        for (Product pr: menu) {
            if(pr.getName().indexOf(name) >= 0) {
                System.out.println(pr);
            }
        }
    }
}
