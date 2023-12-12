package service;

import model.Video;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.time.LocalDate.*;

public class VideoService {
    private static List<Video> videoList = new ArrayList<>();

    private static CategoryService categoryService = new CategoryService();
    private static int currentId = 0;
    static {
        readData();
    }

    public List<Video> getVideos() {
        return videoList;
    }
    public void createProduct(Video video) {
        video.setId(++currentId);
        video.setCategory(categoryService.findById((int) video.getCategoryId()));
        videoList.add(video);
        writeFile();
    }

    public boolean updateVideo(Video video) {
        for (var item:videoList) {
            if (item.getId() == video.getId()) {
                item.setTitle(video.getTitle());
                item.setDescription(video.getDescription());
                item.setDayAt(video.getDayAt());
                return true;
            }
        }
        return false;
    }
    public boolean deleteVideoId(long id) {
        var sizeOld = videoList.size();
        for (int i = 0; i < videoList.size(); i++) {
            if (videoList.get(i).getId() == id) {
                videoList.remove(i);
                writeFile();
                return sizeOld != videoList.size();
            }
        }
        return false;

    }

    public boolean deleteProductName(String title) {
        var sizeOld = videoList.size();
        for (int i = 0; i < videoList.size(); i++) {
            if(videoList.get(i).getTitle().equals(title)) {
                videoList.remove(i);
                writeFile();
                return sizeOld != videoList.size();
            }
        }
        return false;
    }

    public void sortByNameVideo() {
        Collections.sort(videoList, new Comparator<Video>() {
            @Override
            public int compare(Video video1, Video video2) {
                return video1.getTitle().compareTo(video2.getTitle());
            }
        });
    }

    public void findByNameVideo(String title) {
        for (Video video:videoList) {
            if(video.getTitle().indexOf(title) >= 0) {
                System.out.printf("%s   | %s   |%s   | %s   |     %n",
                         video.getId(),
                        video.getTitle(),
                        video.getDescription(),
                        video.getCategory().getId(),
                        video.getDayAt().toString());
            }
        }
    }

    public int countProduct() {
        return videoList.size();
    }

    private static void readData() {
        try {
            File fileWriter = new File("data/video.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals(" ")) {
                String [] data = line.split(",");
                currentId = (int) Long.parseLong(data[0]);
                Video video = new Video(
                        Long.parseLong(data[0]),
                        data[1],
                        data[2],
                        Long.parseLong(data[3]),
                        Date.valueOf(data[4]).toLocalDate());
                video.setCategory(categoryService.findById((int) video.getCategoryId()));
                videoList.add(video);

                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/video.txt"));
            for (var video: videoList) {
                writer.write(video.toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void sortByDayVideo() {
        Collections.sort(videoList, new Comparator<Video>() {
            @Override
            public int compare(Video video1, Video video2) {
                return video1.getDayAt().compareTo(video2.getDayAt());
            }
        });
    }

}
