package br.unicamp.ic.mc322.lab02;

import java.util.Date;

public class User {
	
	// Constantes PODEM ser colocadas como publicas,  
	// SE fazem sentido para um usuário externo da classe
	public final int MAX_PLAYLISTS_NORMAL = 3;
	public final int MAX_PLAYLISTS_PREMIUM = 10;
	
	private String nome;
	private String cpf;
	private Date nascimento;
	private UserGenre genero = UserGenre.NAOINFORMADO;
	private boolean assinante = false;
	
	private Playlist[] playlists = new Playlist[MAX_PLAYLISTS_NORMAL];
	private int currentPlaylistsAmount = 0;
	
	public User(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		
		assinarServico(false);
	}
	
	public User(String nome, String cpf, Date nascimento) {
		this(nome, cpf);
		
		this.nascimento = nascimento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public Date getDataNascimento() {
		return nascimento;
	}
	
	public UserGenre getGenero() {
		return genero;
	}
	
	public boolean isAssinante() {
		return assinante;
	}
	
	public void assinarServico(boolean assinar) {
		assinante = assinar;
		
		resizePlaylists();
	}
	
	public boolean addPlaylist(Playlist p) {
		boolean added = false;
		if(currentPlaylistsAmount < playlists.length) {
			playlists[currentPlaylistsAmount++] = p;
			added = true;
		}
		return added;
	}
	
	private void resizePlaylists() {
		int playListLimit = MAX_PLAYLISTS_NORMAL;
		if(isAssinante()) {
			playListLimit = MAX_PLAYLISTS_PREMIUM;
		}
		
		// Cria uma lista do novo tamanho, copia todas as playlists e 
		// altera o tamanho de cada uma
		Playlist[] newList = new Playlist[playListLimit];
	
		int newAmount = Math.min(currentPlaylistsAmount, newList.length);
	
		for(int i=0; i < newAmount; i++) {
			newList[i] = playlists[i];
			newList[i].resize(isAssinante());
		}
		currentPlaylistsAmount = newAmount;
	}
	
	private int getPlaylistPosition(Playlist playlist) {
		int position = -1;
		for(int i=0; i<currentPlaylistsAmount && position < 0; i++) {
			if(playlist.equals(this.playlists[i])) {
				position = i;
			}
		}
		return position;
	}
	
	public boolean removePlaylist(Playlist playlist) {
		boolean found = false;
		removePlaylist(playlist, getPlaylistPosition(playlist));
		return found;
	}
	
	private boolean removePlaylist(Playlist playlist, int position) {
		boolean done = false;
		if(position >=0 && position < currentPlaylistsAmount) {
			// Se a playlsit removida não é a última preciso mover as outras para trás
			if(position < currentPlaylistsAmount-1) {
				playlists[position] = playlists[position+1];
			}else {
				playlists[position] = null;
			}
			done = true;
		}
		return done;
	}
	
	public boolean transferePlaylist(Playlist playlist, User otherUser) {
		boolean done = false;
		int position = this.getPlaylistPosition(playlist);
		if(position >= 0 && otherUser != null) {
			if(otherUser.addPlaylist(playlist)) {
				removePlaylist(playlist);
				done = true;
			}
		}
		return done;
	}

	public void showInformation() {
		String info = "Nome: " + nome + "\r\n";
		info += "CPF: " + cpf+ "\r\n";
		if(isAssinante()) {
			info += "[Musicfy Premium! Plano: 1 BTC por mês]";
		}
		System.out.print(info);
	}
	
	public void showPlaylists() {
		final String NEWLINE = "\r\n";
		String info = "User: " + nome + NEWLINE;
		info += "Number of Playlists: " + currentPlaylistsAmount + NEWLINE;
		System.out.print(info);
		
		info = "";
		for(int i=0; i<currentPlaylistsAmount; i++) {
			info += "Playlist " + (i+1) + ": " + playlists[i].getInformation();
		}
		System.out.print(info);
	}

}
