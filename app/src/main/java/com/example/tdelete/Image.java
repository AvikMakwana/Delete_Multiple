package com.example.tdelete;

public class Image {

    private int ImageID;
    private String ImageName;

    //Constructor

    public Image(int imageID, String imageName) {
        ImageID = imageID;
        ImageName = imageName;
    }

    //Getters


    public int getImageID() {
        return ImageID;
    }

    public String getImageName() {
        return ImageName;
    }
}
