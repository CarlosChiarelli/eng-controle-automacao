package com.unicamp.mc322.lab02;

public class Song {
    private String name;
    private String style;
    private String artist;
    private int duration; // seconds

    public Song(String name, String style, String artist) {
        this.name = name;
        this.style = style;
        this.artist = artist;
    }

    public Song(String name, String style, String artist, int duration) {
        this.name = name;
        this.style = style;
        this.artist = artist;
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void showMusic() {
        System.out.printf("\t- %s - %s;\n", this.name, this.artist);
    }
}
