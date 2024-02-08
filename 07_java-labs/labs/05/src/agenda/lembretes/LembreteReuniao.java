package agenda.lembretes;



import java.time.Month;
import java.util.LinkedList;
import java.util.List;


public class LembreteReuniao extends LembreteEspecifico{

	private List<PessoaReuniao> pessoas;
	
	public LembreteReuniao(String nome, int ano, Month mes, int dia) {
		super(nome, ano, mes, dia);
		this.pessoas = new LinkedList<>();
	}
	
	public void adicionarPessoa(PessoaReuniao p) {
		this.pessoas.add(p);
	}
	
	public void confirmarPresenca(PessoaReuniao p) {
		for(PessoaReuniao pessoaReuniao : this.pessoas) {
			if(pessoaReuniao.equals(p)) {
				pessoaReuniao.confirmarPresenca();
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(super.toString());
		strBuilder.append("\nPessoas").append("(").append(this.pessoas.size()).append("):\n");
		int index = 1;
		for(PessoaReuniao p : this.pessoas) {
			strBuilder.append(index).append('.').append(p).append(" (");
			if(p.isConfirmada()) {
				strBuilder.append("confirmada");
			}else {
				strBuilder.append("nao confirmado");
			}
			strBuilder.append(")\n");
			index++;
		}
		return strBuilder.toString();
	}

}
