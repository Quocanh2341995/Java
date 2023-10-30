package Main;

public class Product implements Comparable<Product> {
    private int numbersOfProduct;
    private String name;
    private String descrip;
    private double price;


    public Product(Integer numbersOfProduct, String name, String descrip, double price) {
        this.numbersOfProduct = numbersOfProduct;
        this.name = name;
        this.descrip = descrip;
        this.price = price;
    }

    public Product(int numbersOfProduct) {
        this.numbersOfProduct = numbersOfProduct;
    }

    public Product(String name) {
    }

    public int getNumbersOfProduct() {
        return numbersOfProduct;
    }

    public void setNumbersOfProduct(int numbersOfProduct) {
        this.numbersOfProduct = numbersOfProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }


    @Override
    public String toString() {
        return "Menu San Pham:" +
                "ma san pham : " + numbersOfProduct +
                ", name : '" + name + '\'' +
                ", descrip : '" + descrip + '\'' +
                ", price : " + price;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo((o.name));
    }
}
