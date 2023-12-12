package views;

import models.Category;
import models.Video;
import services.VideoServices;
import ultis.AppUltis;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static services.VideoServices.writeFile;
import static ultis.AppUltis.*;

public class VideoViews {
    static Scanner sc = new Scanner(System.in);
    static VideoServices videoServices = new VideoServices();

    public static void printMenu() {
        while (true) {
            System.out.println("---------------Video Manager-------------");
            System.out.println("1. Xem danh sach video.");
            System.out.println("2. Them moi video.");
            System.out.println("3. Xoa video.");
            System.out.println("4. Cap nhat thong tin video.");
            System.out.println("5. Sap xep video.");
            System.out.println("6. Tim kiem theo ten video.");
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
                    editVideo();
                    break;
                case 4 : {
                    deleteVideo();
                    break;
                }
                case 5 : {
                    sortVideo();
                    break;
                }
                case 6 : {
                    System.out.println("Nhap ten video muon tim: ");
                    String title = sc.nextLine();
                    System.out.println("Ket qua tim thay: ");
                    videoServices.findByNameVideo(title);
                }
                case 7 : {
                    writeFile();
                }
            }
        }
    }

    private static void sortVideo() {
        System.out.println("1. Sap xep theo ten tang dan.");
        System.out.println("2. Sap xep theo ngay giam dan.");
        System.out.println("3. Quay tro lai menu.");
        int choosee = getNumberMinMax("Nhap vao lua chon",1, 3);
        switch (choosee) {
            case 1 -> {
                videoServices.sortByName();
                printVideo();
            }
            case 2 -> {
                videoServices.sortByDate();
                printVideo();
            }
            case 3 -> {
                printMenu();
            }
        }
    }

    public static boolean checkExistId(long idVideo) {
        List<Video> videoList = videoServices.getAll();
        for (Video video : videoList) {
            if (video.getId() == idVideo) {
                return true;
            }
        }
        return false;
    }

    private static void editVideo() {
        printVideo();
        long id;
        boolean exists;
        int category;
        do {
            System.out.println("Enter Video ID: ");
            id = Long.parseLong(sc.nextLine());
            exists = checkExistId(id);

            if (!exists) {
                System.out.println("Invalid Video ID! Please try again.");
            }
        } while (!exists);

        Video video = videoServices.getByID(id);
        System.out.println("Enter Video Name:");

        boolean flag;
        String titlenew = sc.nextLine();

        do {
            flag = false;
            if (titlenew.equals("")) {
                System.out.println("Error!! Enter Name: ");
                flag = true;
                titlenew = sc.nextLine();

            }
        } while (flag);
        video.setTitle(titlenew);

        System.out.println("Enter Description: ");
        boolean check;
        String descriptionNew = sc.nextLine();

        do {
            check = false;
            if (descriptionNew.equals("")) {
                System.out.println("Error!! Enter Description: ");
                check = true;
                descriptionNew = sc.nextLine();
            }
        } while (check);
        video.setDescription(descriptionNew);
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
        Date date = getDate("Nhap ngay sua Video : ");
        video.setDayat(date.toLocalDate());
    }


    private static void deleteVideo() {
        try {
            printVideo();
            long videoId = AppUltis.getInt("Input video id to remove: ");
            if (!videoServices.isExist(videoId)) {
                System.out.printf("Not found %d.\n", videoId);
                deleteVideo();
            }
            videoServices.delete(videoId);
            System.out.println("Remove video successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            System.out.println("Enter Video Name: ");
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