package ienquire.ie.libfff.model;

import java.io.Serializable;

/**
 * @author diogo10
 */
public class Clip implements Serializable {


    private Integer id;
    private String name;
    private Long size;
    private String format;
    private String status;
    private String url;
    private String key;
    private String category;
    private String team;
    private String message;
    private String thumbnail;
    private String imageurl;


    public Clip() {
    }

    public Clip(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Clip(String name, String message, String url) {
        this.name = name;
        this.message = message;
        this.url = url;
    }

    public Clip(String name, String message, String url, String category,String imageurl) {
        this.name = name;
        this.message = message;
        this.url = url;
        this.category = category;
        this.imageurl = imageurl;
    }

    public Clip(String name, String message, String url,String imageurl) {
        this.name = name;
        this.message = message;
        this.url = url;
        this.imageurl = imageurl;
    }



    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
