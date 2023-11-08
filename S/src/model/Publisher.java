package model;

public enum Publisher {
    NHAXUATBANTRE("Nhà xuất bản Trẻ"),
    NHAXUATBANKIMDONG("Nhà xuất bản Kim Đồng"),
    NHAXUATBANVANHOC("Nhà xuất bản Văn học");

    private final String value;

    private Publisher(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
