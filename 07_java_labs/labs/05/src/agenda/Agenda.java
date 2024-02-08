package agenda;

import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import agenda.lembretes.Lembrete;

public class Agenda {

	private List<Lembrete> lembretes;
	
	public Agenda() {
		this.lembretes = new LinkedList<>();
	}
		
	public void adicionarLembrete(Lembrete l) {
		this.lembretes.add(l);
	}
	
	public void removerLembrete(Lembrete l) {
		this.lembretes.remove(l);
	}
	
	public void mostrarLembretesAtivos(ZonedDateTime data1, ZonedDateTime data2) {
		for(Lembrete l : this.lembretes) {
			if(l.verificar(data1, data2)) {
				System.out.println(l);
			}
		}
	}
		
	public void mostrarLembretesAtivos(ZonedDateTime data) {
		for(Lembrete l : this.lembretes) {
			if(l.verificar(data)) {
				System.out.println(l);
			}
		}
	}
	
	public void mostrarLembretesAtivos() {
		ZonedDateTime dataAtual = new GregorianCalendar().toZonedDateTime();
		this.mostrarLembretesAtivos(dataAtual);
	}
	
}
