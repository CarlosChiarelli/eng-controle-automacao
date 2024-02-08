package br.unicamp.ic.mc322.lab02;

public class Song {
	
	private String nome;
	private String genero;
	private String artista;
	private int duracao;
	
	public Song(String nome, String artista) {
		this.nome = nome;
		this.artista = artista;
	}

	public Song(String nome, String genero, String artista) {
		this(nome, artista);
		
		this.genero = genero;
	}
	
	public Song(String nome, String genero, String artista, int duraçao) {
		this(nome, genero, artista);

		this.duracao = duraçao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNone(String novoNome) {
		nome = novoNome;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String novoGenero) {
		genero = novoGenero;
	}
	
	public String getArtista() { 
		return artista;
	}
	
	public void setArtista(String novoArtista) {
		artista = novoArtista;
	}
	
	public int getDuracao()  {
		return duracao;
	}
	
	public void setDuracao(int novaDuracao) {
		duracao = novaDuracao;
	}
	
	public String formataDuracao() {

		int minutos = getDuracao() / 60;
		int segundos = getDuracao() % 60;
		
		return minutos + "'" + segundos + "\"";
	}
	
	public String getDetails() {
		return getNome() + " - " + getArtista();
	}
}
