package com.unicamp.mc322.lab02;

public class Musicfy {

    public static void main(String[] args) {
        User user1 = new User("Marcos Paulo", "777.777.777-77");
        User user2 = new User("Cookiezi", "111.111.11-11");

        // test song
        System.out.print("\n.\n.\n.\n\nTEST SONG: \n\n");

        Song song1 = new Song("Seven Nation Army", "Rock", "The White Stripes");
        Song song2 = new Song("Crazy Train", "Rock", "Ozzy Osbourne");
        Song songsPop[] = new Song[12];
        for (int i = 0; i < songsPop.length; i++)
            songsPop[i] = new Song("Roar v" + i, "Pop", "Katy Perry");
        Song song3 = new Song("Feels", "Pop", "Calvin Harris");
        Song song5 = new Song("Anima", "Hardcore", "Xi");
        Song song6 = new Song("Freedom Dive", "Hardcore", "Xi");
        Song song7 = new Song("Teo", "Hardcore", "Omoi");
        Song song8 = new Song("Sleepwalking", "Metalcore", "Bring Me The Horizon");
        Song song9 = new Song("Brasileirinho", "Samba", "Anônimo", 50);
        Song song10 = new Song("Cabide", "Samba", "Martinália", 40);
        Song song11 = new Song("O mundo é um moinho", "Samba", "Cartola", 30);
        Song song12 = new Song("As rosas não falam", "Samba", "Cartola", 40);
        song9.showMusic();
        song9.setArtist("Waldir Azevedo");
        song9.showMusic();

        // test playlist
        System.out.print("\n.\n.\n.\n\nTEST PLAYLIST: \n\n");

        Playlist sambaPlaylist = new Playlist("Best Samba", "Samba");
        sambaPlaylist.addSong(song12);
        sambaPlaylist.addSong(song11);
        sambaPlaylist.addSong(song10);
        sambaPlaylist.addSong(song9);
        sambaPlaylist.userShowMusics(4);
        Song aux = sambaPlaylist.getShorterDuration();
        System.out.print("\nMusic shorter:");
        aux.showMusic();
        System.out.print("\n");
        aux = sambaPlaylist.getLongerDuration();
        System.out.print("\nMusic longer:");
        aux.showMusic();
        System.out.print("\n");
        System.out.printf("\nMusics's mean duration: %.0f s\n\n", sambaPlaylist.getMeanDuration());
        System.out.printf("\nMusics's total duration: %.0f s\n\n", sambaPlaylist.getTotalDuration());
        System.out.printf("\nPopular artist: %s\n\n", sambaPlaylist.getPopularArtist());
        sambaPlaylist.removeMusic("Brasileirinho");
        sambaPlaylist.userShowMusics(3);
        System.out.printf("\nMusics's total duration: %.0f s\n\n", sambaPlaylist.getTotalDuration());

        Playlist rockPlaylist = new Playlist("Awesome Rock Songs", "Rock");
        rockPlaylist.addSong(song1);
        rockPlaylist.addSong(song2);

        Playlist popPlaylist = new Playlist("Best Pop", "Pop");
        for (int i = 0; i < songsPop.length; i++)
            popPlaylist.addSong(songsPop[i]);

        Playlist osuPlaylist = new Playlist("Osu Memories", "hardcore");
        osuPlaylist.addSong(song5);
        osuPlaylist.addSong(song6);
        osuPlaylist.addSong(song7);

        Playlist metalcorePlaylist = new Playlist("Best of Metalcore", "Metalcore");
        metalcorePlaylist.addSong(song8);

        // test user
        System.out.print("\n.\n.\n.\n\nTEST USER: \n\n");
        user1.addPlaylist(rockPlaylist);
        user1.addPlaylist(metalcorePlaylist);
        user1.addPlaylist(popPlaylist);
        for (int i = 0; i < 2; i++)
            user1.addPlaylist(metalcorePlaylist);

        user2.addPlaylist(osuPlaylist);

        user1.showPlaylists();
        System.out.println("");
        user2.showInformation();

        user1.showInformation();
        user1.setSubscribe(true);
        for (int i = 0; i < 2; i++)
            user1.addPlaylist(metalcorePlaylist);
        user1.addPlaylist(popPlaylist);
        user1.showInformation();
        user1.showPlaylists();

        // test transfer playlist
        user2.showInformation();
        user2.showPlaylists();
        user1.transferPlaylist(user2, "Awesome Rock Songs");
        user2.showPlaylists();
        user1.showPlaylists();

        // test remove playlist
        user2.removePlaylist("Osu Memories");
        user2.showPlaylists();

        // user cancel subscribe
        user1.setSubscribe(false);
        user1.showPlaylists();
        user1.setSubscribe(true);
        user1.showPlaylists();

        // test play
        System.out.print("\n.\n.\n.\n\nTEST PLAY (feature): \n\n");

        for (int i = 0; i < 20; i++) {
            aux = popPlaylist.play();
            aux.showMusic();
        }

        for (int i = 0; i < 20; i++) {
            aux = popPlaylist.play();
            aux.showMusic();
        }

        System.out.print("\n\nMusic random.\n\n");
        for (int i = 0; i < 20; i++) {
            aux = popPlaylist.play(true);
            aux.showMusic();
        }
    }
}