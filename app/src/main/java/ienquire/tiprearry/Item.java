package ienquire.tiprearry;

import android.graphics.drawable.Drawable;

import java.io.Serializable;


public class Item implements Serializable {

    private String title,details,heading;
    private String Url;
    private Drawable image;
    private String imageurl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUrl() {

        return Url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Item(String title, String details, String Url, Drawable image)
    {
        this.title=title;
        this.details=details;
        this.Url=Url;
        this.image=image;
        this.imageurl = null;

    }

    public Item(String title, String details, String Url, String imageurl)
    {
        this.title=title;
        this.details=details;
        this.Url=Url;
        this.imageurl=imageurl;
        this.image = null;


    }

    public Item(String heading, Drawable image){

        this.heading=heading;
        this.image=image;

    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
