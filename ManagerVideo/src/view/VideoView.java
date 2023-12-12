package view;

import model.Video;
import service.CategoryService;
import service.VideoService;

import java.sql.Date;
import java.util.Scanner;

public class VideoView {

    static Scanner sc = new Scanner(System.in);
    static VideoService videoService = new VideoService();
    static CategoryService categoryService = new CategoryService();

    public static void printMenu() {
        while(true) {
            System.out.println("-----ManagerVideo-----");
            System.out.println("1. Hien thi danh sach video.");
            System.out.println("2. Them moi video.");
            System.out.println("3. Cap nhat thong tin video.");
            System.out.println("4. Xoa video.");
            System.out.println("5. Sap xep video.");
            System.out.println("6. Tim kiem video theo ten");
            System.out.println("7. doc file.");
            System.out.println("0. Thoat.");
            int choice = getNumber("Nhap vao lua chon: ");
            switch (choice) {
                case 1 -> {
                    printVideo();
                    break;
                }
                case 2 -> {
                    createVideo();
                    break;
                }
                case 3 -> {
                    updateVideo();
                    break;
                }
                case 4 -> {
                    deleteVideo();
                    break;
                }
                case 5 -> {
                    sortProduct();
                    break;
                }
                case 6 -> {
                    System.out.println("Nhap ten san pham muon tim: ");
                    String name = sc.nextLine();
                    System.out.println("Ket qua tim thay: ");
                    videoService.findByNameVideo(name);
                }
                case 7 -> {
                    System.out.println("So luong san pham co trong menu :" + videoService.countProduct());
                }
            }

        }
    }

    private static void sortProduct() {
        System.out.println("1. Sap xep theo ten.");
        System.out.println("2. Sap xep theo ngay.");
        System.out.println("3. Tro lai");
        int choosee = getNumber("Nhap vao lua chon sap xep:");
        switch (choosee) {
            case 1 -> {
                videoService.sortByNameVideo();
                printVideo();
                break;
            }
            case 2 -> {
                videoService.sortByDayVideo();
                printVideo();
                break;
            }
            case 3 -> {
                printMenu();
                break;
            }
        }
    }

    private static void deleteVideo() {
        printVideo();
        boolean isSuccess = videoService.deleteVideoId(getNumber("Nhap Id video muon xoa: "));
        if (isSuccess) {
            System.out.println("Xoa san pham thanh cong.");
        } else {
            System.out.println("Khong tim thay Id san pham");
        }
    }

    private static void updateVideo() {
        printVideo();
        boolean isSuccess = videoService.updateVideo(new Video(getNumber("Nhap Id san pham muon cap nhat thong tin:"),
                getString("Nhap ten moi cua san pham:"),
                getString("Nhap mo ta moi cua san pham:"),
                getCategoryId("Nhap phan loai moi cua san pham:"),
                getDay("Nhap ngay nhap hang moi cua san pham:").toLocalDate()));
    }

    private static void createVideo() {
        videoService.createProduct(new Video(0, getString("Nhap ten san pham moi:"),
                getString("Nhap mo ta san pham moi:"),
                getCategoryId("Nhap phan loai san pham:"),
                getDay("Nhap ngay nhap hang:").toLocalDate()
        ));
    }

    private static void printVideo() {
        System.out.println("----------Video-----------");
        System.out.println("Id:   | Title:   | Descript:   | CategoryId:   || Date:    ");
        for (var video: videoService.getVideos()) {
            System.out.printf("%s   | %s   | %s   | %s   | %s   |     %n",
                    video.getId(),
                    video.getTitle(),
                    video.getDescription(),
                    video.getCategory().getId(),
                    video.getDayAt().toString());
        }
    }

    public static int getNumber(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            return num;
        } catch (Exception e) {
            System.out.println("Du lieu nhap vao khong dung dinh dang !");
            return getNumber(str);
        }
    }

    public static String getString(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }

    public static Date getDay(String str) {
        System.out.println(str);
        System.out.println("Kieu du lieu ngay : yyyy-mm-dd, vidu: 2023-01-01");
        try {
            return Date.valueOf(sc.nextLine());
        } catch (Exception e) {
            return getDay(str);
        }
    }

    public static int getCategoryId(String str) {
        System.out.println("Danh sach phan loai:");
        for (var category:categoryService.findAll()) {
            System.out.println(category.getId() + ". " + category.getName());
        }
        return getNumber(str);
    }


}
