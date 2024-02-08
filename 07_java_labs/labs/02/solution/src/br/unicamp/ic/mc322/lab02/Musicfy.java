package br.unicamp.ic.mc322.lab02;

public class Musicfy {

	public static void main(String[] args) {

        User user1 = new User("Marcos Paulo", "777.777.777-77");
        User user2 = new User("Cookiezi", "111.111.11-11");

        Song song1 = new Song("Seven Nation Army", "Rock", "The White Stripes");
        Song song2 = new Song("Crazy Train", "Rock", "Ozzy Osbourne");
        Song song3 = new Song("Feels", "Pop", "Calvin Harris");
        Song song4 = new Song("Roar", "Pop", "Katy Perry");
        Song song5 = new Song("Anima", "Hardcore", "Xi");
        Song song6 = new Song("Freedom Dive", "Hardcore", "Xi");
        Song song7 = new Song("Teo", "Hardcore", "Omoi");
        Song song8 = new Song("Sleepwalking", "Metalcore", "Bring Me The Horizon");

        Playlist rockPlaylist = new Playlist("Awesome Rock Songs", "Rock");
        rockPlaylist.addSong(song1);
        rockPlaylist.addSong(song2);

        Playlist osuPlaylist = new Playlist("Osu Memories", "hardcore");
        osuPlaylist.addSong(song5);
        osuPlaylist.addSong(song6);
        osuPlaylist.addSong(song7);

        Playlist metalcorePlaylist = new Playlist("Best of Metalcore", "Metalcore");
        metalcorePlaylist.addSong(song8);

        user1.addPlaylist(rockPlaylist);
        user1.addPlaylist(metalcorePlaylist);
        user2.addPlaylist(osuPlaylist);

        user1.showPlaylists();
        System.out.println("");
        user2.showInformation();

        Song asong1 = osuPlaylist.play();
        Song asong2 = osuPlaylist.play();
        Song asong3 = osuPlaylist.play(true);
	}

}
