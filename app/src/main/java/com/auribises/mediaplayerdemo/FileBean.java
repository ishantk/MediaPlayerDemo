package com.auribises.mediaplayerdemo;

/**
 * Created by ishantkumar on 28/03/17.
 */

public class FileBean {

    // Attributes
    int image;
    String title;

    //Methods
    public FileBean() {
    }

    public FileBean(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
