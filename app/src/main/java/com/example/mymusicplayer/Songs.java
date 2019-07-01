package com.example.mymusicplayer;

public class Songs {

    private String songTitle;
    private String songCountry;
    private String songMusic;
    private String songDetails;

    public Songs(String songTitle, String songCountry, String songMusic, String songDetails) {
        this.songTitle = songTitle;
        this.songCountry = songCountry;
        this.songMusic = songMusic;
        this.songDetails = songDetails;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongCountry() {
        return songCountry;
    }

    public String getSongMusic() {
        return songMusic;
    }

    public String getSongDetails() {
        return songDetails;
    }

}
