package tech.huning.wall.e.util.async.dto;

/**
 * 视频信息
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class VideoDTO {

    private String title;
    private long duration;

    public VideoDTO(String title, long duration) {
        this.duration = duration;
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
