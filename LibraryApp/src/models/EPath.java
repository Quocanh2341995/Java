package models;

public enum EPath {
    BOOKS("data/books.txt"),
    BORROWERS("data/borrowers.txt"),
    USERS("data/users.txt");
    private final String filePath;

    EPath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
