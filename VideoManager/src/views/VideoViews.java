package views;

import models.Category;
import models.Video;
import services.VideoServices;
import ultis.AppUltis;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ultis.AppUltis.getNumberMinMax;
import static ultis.AppUltis.getString;

public class VideoViews {
    static Scanner sc = new Scanner(System.in);
    static VideoServices videoServices = new VideoServices();

    public static void printMenu() {
        while (true) {
            System.out.println("---------------Video Manager-------------");
            System.out.println("1. Xem danh sach video.");
            System.out.println("2. Them moi video.");
            System.out.println("3. Cap nhat thong tin video.");
            System.out.println("4. Xoa video.");
            System.out.println("5. Sap xep video.");
            System.out.println("6. Doc tu file.");
            System.out.println("7. Ghi tu file.");
            System.out.println("0. Thoat.");
            int choice = getNumberMinMax("Nhap vao lua chon:", 0, 8);
            switch (choice) {
                case 1: {
                    printVideo();
                    break;
                }
                case 2: {
                    addVideo();
                    break;
                }
                case 3 :
                    deleteVideo();
                    break;
            }
        }
    }

    private static void addVideo() {
        List<Video> videoList = videoServices.getAll();
        int category;
        Video video = new Video();
        String title, description;
        int id = 0;

        if (videoList.size() == 0) {
            id = 1;
        } else id = (int) (videoList.get(videoList.size() - 1).getId() + 1);
        video.setId(id);

        do {
            System.out.println("Enter Book Name: ");
            title = sc.nextLine();
            video.setTitle(title);
        } while (title == "");

        do {
            System.out.println("Enter Description: ");
            description = sc.nextLine();
            video.setDescription(description);
        } while (description == "");
        try {
            boolean checkCategory = false;
            do {
                System.out.println("Choose Category: ");
                System.out.println("[1] Hai");
                System.out.println("[2] Kinh di");
                System.out.println("[3] Lang man");
                System.out.println("[4] Hanh dong");
                String input = sc.nextLine();
                if (AppUltis.checkInt(input)) {
                    category = Integer.parseInt(input);
                } else {
                    continue;
                }
                switch (category) {
                    case 1:
                        video.setCategory(Category.HAI);
                        checkCategory = true;
                        break;
                    case 2 :
                        video.setCategory(Category.KINHDI);
                        checkCategory = true;
                        break;
                    case 3 :
                        video.setCategory(Category.LANGMAN);
                        checkCategory = true;
                        break;
                    case 4 :
                        video.setCategory(Category.HANHDONG);
                        checkCategory = true;
                        break;
                    default:
                        System.out.println("Please Enter Valid Function!");
                        checkCategory = false;
                }
            } while (!checkCategory);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        LocalDate date = LocalDate.now();
        video.setDayat(date);
        videoServices.add(video);
        System.out.println("Successfully added video!!");


    }


    private static void printVideo() {
        List<Video> videoList = videoServices.getAll();
        System.out.println("╔------------------------------------------------- List Video --------------------------------------------╗");
        System.out.println(String.format("| %-5s | %-20s | %-20s | %-15s | %-15s|",
                " ID", "Title", "Description", "Category", "DateAt"));
        System.out.println("|-------*---------------------------*-------------------*------------*----------|");
        for (Video video : videoList) {
            System.out.println(video.toString());
            System.out.println("╚-------*--------------------------------*------------*----------╝");
        }
    }

}