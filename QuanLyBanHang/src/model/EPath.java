package model;

public enum EPath {
    PRODUCTS("data/products.txt"),
    BUYER("data/bill.txt"),
    USERS("data/users.txt");
    private final String filePath;

    EPath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
