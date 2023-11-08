package model;

public enum EPath {
    BOOKS("data/books.data"),
    BORROWERS("data/borrowers.data"),
    USERS("data/users.data");
    private final String filePath;

    EPath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
