package agenda.lembretes;

public class PessoaReuniao {

	private String nome;
	private String email;
	private String telefone;
	private boolean confirmada;

	public PessoaReuniao(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.confirmada = false;
	}

	
	public boolean isConfirmada() {
		return this.confirmada;
	}
	
	void confirmarPresenca() { //metodo visivel somente para classes no mesmo pacote
		this.confirmada = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PessoaReuniao) {
			PessoaReuniao p = (PessoaReuniao) obj;
			return this.nome.equals(p.nome) && this.email.equals(p.email) && this.telefone.equals(p.telefone);
		}
		return false;
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.telefone;
	}
}
