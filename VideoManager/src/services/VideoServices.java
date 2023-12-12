package services;

import models.EPath;
import models.Video;
import ultis.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
