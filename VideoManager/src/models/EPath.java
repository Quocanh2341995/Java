package models;

public enum EPath {
    VIDEOS("data/videos.txt"),
    CATEGORYS("data/category.txt");

    private final String filePath;

    EPath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
