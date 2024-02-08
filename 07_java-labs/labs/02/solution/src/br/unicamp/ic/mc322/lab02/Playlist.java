package br.unicamp.ic.mc322.lab02;

import java.util.Random;

public class Playlist {

	// Constantes PODEM ser colocadas como publicas,  
	// SE fazem sentido para um usuário externo da classe
	public final int MAX_SIZE_PREMIUM = 100;
	public final int MAX_SIZE_NORMAL = 3;
	private final int FIRST_SONG = 0;
	
	private String nome;
	private String genero;
	
	private Song[] songs;
	
	private int currentSongsAmount = 0;
	private int playingSongIndex = FIRST_SONG;
	
	public Playlist(String nome, String genero, boolean assinante) {
		this.nome = nome;
		this.genero = genero;
		
		if(assinante) {
			songs = new Song[MAX_SIZE_PREMIUM];
		}else {
			songs = new Song[MAX_SIZE_NORMAL];
		}
		currentSongsAmount = 0;
		setPlayingSong(FIRST_SONG);
	}

	public Playlist(String nome, String genero) {
		this(nome, genero, false);
	}
	
	public boolean addSong(Song s) {
		boolean added = false;
		if(currentSongsAmount < songs.length) {
			songs[currentSongsAmount] = s;
			currentSongsAmount++;
			sortSongsByName();
			added = true;
		}
		return added;
	}
	
	public boolean removeSong(Song s) {
		boolean done = false;
		for(int i=0; i<currentSongsAmount && !done; i++) {
			if(s.equals(songs[i])) {
				// Se a musica removida não é a última preciso mover as outras para trás
				if(i < currentSongsAmount-1) {
					songs[i] = songs[i+1];
				}else {
					songs[i] = null;
				}
			}
		}
		return done;
	}
	
	private void sortSongsByName() {
		// Simples implementação do BubbleSort 
		// Este método é PRIVADO, não precisa ser acessado fora da classe!
		Song temporary;
		for(int i=0; i < currentSongsAmount; i++) {
			for(int j=0; j < currentSongsAmount-i-1; j++) {
				if(songs[j].getNome().compareTo(songs[j+1].getNome()) > 0) {
					temporary = songs[j+1];
					songs[j+1] = songs[j];
					songs[j] = temporary;
				}
			}
		}
	}
	
	public void resize(boolean assinante) {
		if(assinante) {
			resize(MAX_SIZE_PREMIUM);
		}else {
			resize(MAX_SIZE_NORMAL);
		}
	}
		
	private void resize(int newSize) {
		Song[] oldList = songs;
		
		songs = new Song[newSize];
		for(int i=0; i<songs.length && i<oldList.length; i++) {
			songs[i] = oldList[i];
		}
		
		setPlayingSong(FIRST_SONG);
	}
	
	public int getCurrentSize() {
		return currentSongsAmount;
	}
	
	public int getMaximumSize() { 
		return songs.length;
	}
	
	private void setPlayingSong(int index) {
		playingSongIndex = index;
	}
	
	public Song play() {
		return play(false);
	}
	
	public Song play(boolean shuffle) {
		int nextSong = FIRST_SONG;

		if(shuffle) {
			Random rand = new Random();
			do {
				nextSong = rand.nextInt(getCurrentSize());
			}while(nextSong == playingSongIndex);
		}else {
			nextSong = playingSongIndex + 1;
		}
		
		setPlayingSong(nextSong);
		if(playingSongIndex >= getCurrentSize()) {
			setPlayingSong(FIRST_SONG);
		}
		
		System.out.println("Now playing: " + songs[playingSongIndex].getDetails());
		return songs[playingSongIndex];
	}
	
	public Song getMenorDuracao() {
		int shortest = FIRST_SONG;		
		for(int i=1; i < getCurrentSize(); i++) {
			if(songs[i].getDuracao() < songs[shortest].getDuracao()) {
				shortest = i;
			}
		}
		return songs[shortest];
	}
	
	public Song getMaiorDuracao() {
		int longest = FIRST_SONG;		
		for(int i=1; i < getCurrentSize(); i++) {
			if(songs[i].getDuracao() > songs[longest].getDuracao()) {
				longest = i;
			}
		}
		return songs[longest];
	}
	
	public int getDuracaoTotal() {
		int duracaoTotal = 0;		
		for(int i=0; i < getCurrentSize(); i++) {
			duracaoTotal += songs[i].getDuracao();
		}
		return duracaoTotal;
	}
	
	public int getDuracaoMedia() {
		return getDuracaoTotal() / getCurrentSize();
	}
	
	
	public String getDetails() {
		String result = "Playlist " + nome;
		if(currentSongsAmount > 0) {
			result += "has songs: ";
			for(int i=0; i < currentSongsAmount; i++) {
				if(i > 0) {
					result += ", ";
				}
				result += songs[i].getNome();
			}
			result += ".";
		}else {
			result += "is empty!";
		}
		
		return result;
	}

	public String getArtistaMaisFrequente() {
		/* Esta tarefa pode ser resolvida mais eficientemente com uma tabela hash.
		 * A biblioteca Java possui classes que implementam tabelas hash, porém usam conceitos que ainda 
		 * não temos visto na disciplina (como por exemplo os tipos genéricos)  */
		
		String[] artistas = new String[getCurrentSize()];
		int[] occurencias = new int[getCurrentSize()];
		
		String currentArtist = "";
		boolean found = false;
		
		// Passa em todas as canções na playlist e adiciona o artista em um vetor
		// de artistas. Se o artista existe já, incrementa o contador de uma unidade
		for(int i=0; i < getCurrentSize(); i++) {
			currentArtist = songs[i].getArtista();
			
			int k = 0;
			while(!found && artistas[k] != null) {
				if(artistas[k].contentEquals(currentArtist)) {
					found = true;
				}else {
					k++;
				}
			}
			
			// Se o artista foi encontrado na lista, incrementa o contador
			// Caso contrário, adiciona na lista e coloca o contador para 1.
			if(found) {
				occurencias[k]++;
			}else {
				artistas[k] = currentArtist;
				occurencias[k] = 1;
			}
		}
		
		// Procura agora o artista que tem o valor maior do contador
		int indexOfMostOccurring = 0;
		for(int j=0; j < occurencias.length && artistas[j] != null; j++) {
			if(occurencias[j] > occurencias[indexOfMostOccurring]) {
				indexOfMostOccurring = j;
			}
		}
		
		return artistas[indexOfMostOccurring];
	}
	
	public String getInformation() {
		final String INDENT = "    ";
		final String NEWLINE = "\r\n";
		
		String info = nome + NEWLINE;
		info += INDENT + "Number of Songs: " + currentSongsAmount + NEWLINE;
		if(currentSongsAmount > 0) {
			info += INDENT + "Songs: " + NEWLINE;
			for(int i=0; i<currentSongsAmount; i++) {
				info += INDENT + "- " + songs[i].getDetails() + ";" + NEWLINE;
			}
		}
		return info;
	}

}
