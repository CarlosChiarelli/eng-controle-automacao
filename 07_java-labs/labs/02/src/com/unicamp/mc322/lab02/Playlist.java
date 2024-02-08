package com.unicamp.mc322.lab02;

import java.lang.Math;

public class Playlist {
    private String name;
    private String style;
    private int MAX_MUSICS = 100;
    private int currentMusic = 0;
    private int size = 0; // amount of songs
    private Song songs[] = new Song[MAX_MUSICS];

    public Playlist(String name, String style) {
        this.name = name;
        this.style = style;
    }

    // get name
    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    // method add music (order in final)
    public void addSong(Song music) {
        this.songs[this.size++] = music;
        this.sort();
    }

    // method remove music
    public void removeMusic(String music) {
        int idxMusic = this.findMusic(music);
        // if music exist
        if (idxMusic != -1) {
            this.songs[idxMusic] = this.songs[--this.size];
            this.sort();
        }
    }

    // method return music with shorter duration
    public Song getShorterDuration() {
        Song songShorter = this.songs[0];
        for (int i = 0; i < this.size; i++)
            if (songShorter.getDuration() > this.songs[i].getDuration())
                songShorter = this.songs[i];
        return (songShorter);
    }

    // method return music with longer duration
    public Song getLongerDuration() {
        Song songLonger = this.songs[0];
        for (int i = 0; i < this.size; i++)
            if (songLonger.getDuration() < this.songs[i].getDuration())
                songLonger = this.songs[i];
        return (songLonger);
    }

    // method return musics's mean duration
    public float getMeanDuration() {
        float sum = 0;
        for (int i = 0; i < this.size; i++)
            sum += this.songs[i].getDuration();
        return (sum / this.size);
    }

    // method return musics's total duration
    public float getTotalDuration() {
        float sum = 0;
        for (int i = 0; i < this.size; i++)
            sum += this.songs[i].getDuration();
        return (sum);
    }

    // method return artist with more songs
    public String getPopularArtist() {
        String popularArtist = this.songs[0].getArtist();
        int countMusic = 1;
        int aux = 1;
        for (int i = 0; i < this.size; i++) {
            aux = this.countArtist(this.songs[i].getArtist());
            if (aux > countMusic) {
                countMusic = aux;
                popularArtist = this.songs[i].getArtist();
            }
        }
        return (popularArtist);
    }

    private int countArtist(String artist) {
        int counter = 0;
        for (int j = 0; j < this.size; j++)
            if (artist.equals(this.songs[j].getArtist()))
                counter++;
        return counter;
    }

    // method play (return next music, if shuffle parameter call == true -> return
    // random music different from the current)
    public Song play() {
        if (this.currentMusic + 1 == this.size) {
            this.currentMusic = 0;
            return (this.songs[this.currentMusic]);
        }
        return (this.songs[++this.currentMusic]);
    }

    public Song play(boolean shuffle) {
        int intRand;
        if (shuffle) {
            do {
                intRand = generateIntRand(0, this.size - 1);
            } while (intRand == this.currentMusic);
            this.currentMusic = intRand;
            return (this.songs[this.currentMusic]);
        }
        return (this.play());
    }

    public int generateIntRand(int min, int max) {
        int res = min + (int) (Math.random() * ((max - min) + 1));
        return (res);
    }

    // method sort playlist (by name's music)
    private void sort() {
        boolean switched = true;
        Song aux;
        while (switched) {
            switched = false;
            for (int i = 0; i < this.size - 1; i++) {
                String songBefore = this.songs[i].getName();
                String songAfter = this.songs[i + 1].getName();

                // if > 0 so is larger
                if (songBefore.compareTo(songAfter) > 0) {
                    aux = this.songs[i];
                    this.songs[i] = this.songs[i + 1];
                    this.songs[i + 1] = aux;
                    switched = true;
                }
            }
        }
    }

    // return index music in playlist
    private int findMusic(String name) {
        for (int i = 0; i < this.size - 1; i++)
            if (this.songs[i].getName() == name)
                return (i);
        return (-1);
    }

    // show user's musics (maxMusics subscribe)
    public void userShowMusics(int maxMusics) {
        maxMusics = (maxMusics < this.size) ? maxMusics : this.size;

        System.out.printf("%s\n", this.getName());
        System.out.printf("\tNumber of Songs: %d\n\tSongs:\n", maxMusics);

        for (int i = 0; i < maxMusics; i++) {
            // showMusic
            this.songs[i].showMusic();
        }
    }
}
