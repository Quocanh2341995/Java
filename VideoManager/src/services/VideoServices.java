package services;

import models.EPath;
import models.Video;
import ultis.Serializable;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class VideoServices implements BasicCRUD<Video> {
    private static final List<Video> list = new ArrayList<>();
    public static List<Video> listVideos;
    static {
        listVideos =  (List<Video>) Serializable.deserialize(EPath.VIDEOS.getFilePath());
    }

    public VideoServices(){
    }

    public void add(Video newvideo){
        listVideos = getAll();
        listVideos.add(newvideo);
        save();
    }

    public static void save() {
        Serializable.serialize(listVideos, EPath.VIDEOS.getFilePath());
    }


    @Override
    public Video getByID(long id) {
        return listVideos.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Video> getAll() {
        return listVideos;
    }

    @Override
    public void create(Video video) {
        listVideos.add(video);
    }

    @Override
    public void update(Video obj) {
        for (Video video: listVideos
        ) {
            if(video.getId() == obj.getId()){
                video = obj;
                break;
            }
        }

    }

    @Override
    public void delete(long id) {
        listVideos.removeAll(listVideos.stream().filter(b -> b.getId() == id).toList());
        save();
    }

    @Override
    public boolean isExist(long id) {
        Video video = listVideos.stream()
                .filter(b -> Objects.equals(b.getId(),id))
                .findFirst()
                .orElse(null);
        return video != null;
    }

    public void sortByName() {
        Collections.sort(listVideos, new Comparator<Video>() {
            @Override
            public int compare(Video video1, Video video2) {
                return video1.getTitle().compareTo(video2.getTitle());
            }
        });
    }

    public void sortByDate() {
        Collections.sort(listVideos, new Comparator<Video>() {
            @Override
            public int compare(Video video1, Video video2) {
                return video1.getDayat().compareTo(video2.getDayat());
            }
        });
    }

    public void findByNameVideo(String title) {
        for (Video video:listVideos) {
            if(video.getTitle().indexOf(title) >= 0) {
                System.out.printf("%x   | %s   | %s   |      %s%n",
                        video.getId(),
                        video.getTitle(),
                        video.getDescription(),
                        video.getDayat());
            }
        }
    }

    private static void readData() {
        try {
            File fileWriter = new File("data/videos.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals(" ")) {
                String [] data = line.split(",");
                long currentId = Long.parseLong(data[0]);
                Video video = new Video(
                        Long.parseLong(data[0]),
                        data[1],
                        data[2],
                        Date.valueOf(data[3]).toLocalDate()
                );

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/videos.txt"));
            for (var video: listVideos) {
                writer.write(video.toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
