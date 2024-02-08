package main;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

import agenda.Agenda;
import agenda.lembretes.Lembrete;
import agenda.lembretes.LembreteDiaDaSemana;
import agenda.lembretes.LembreteEspecifico;
import agenda.lembretes.LembreteMensal;
import agenda.lembretes.LembreteReuniao;
import agenda.lembretes.PessoaReuniao;

public class Main {
	public static void main(String[] args) {

		Agenda agenda = new Agenda();

		// Lembrete Comum
		Lembrete lembreteComum = new Lembrete("Lembrete Comum");
		// agenda.adicionarLembrete(new Lembrete("Lembrete Comum"));
		agenda.adicionarLembrete(lembreteComum);

		// Lembrete dia da semana
		agenda.adicionarLembrete(new LembreteDiaDaSemana("Lembrete Semanal", DayOfWeek.SATURDAY));

		// Lembrete Mensal
		agenda.adicionarLembrete(new LembreteMensal("Lembrete Mensal", Month.MARCH));

		// Lembrete Evento
		agenda.adicionarLembrete(new LembreteEspecifico("Lembrete Evento", 2019, Month.APRIL, 2));

		// Reuniao
		PessoaReuniao elder = new PessoaReuniao("Elder", "email1", "tel2");
		PessoaReuniao leonardo = new PessoaReuniao("Leonardo", "email2", "tel2");
		LembreteReuniao reuniao = new LembreteReuniao("Lembrete Reuniao", 2019, Month.APRIL, 7);
		reuniao.adicionarPessoa(elder);
		reuniao.adicionarPessoa(leonardo);
		reuniao.confirmarPresenca(leonardo);
		agenda.adicionarLembrete(reuniao);

		// Mostrar por dia especifico
		System.out.println("### Dia Especifico ###");
		agenda.mostrarLembretesAtivos(new GregorianCalendar(2019, Calendar.MARCH, 2).toZonedDateTime());

		// Mostrar por intervalo
		System.out.println("### Intervalo ###");
		agenda.mostrarLembretesAtivos(new GregorianCalendar(2019, Calendar.APRIL, 2).toZonedDateTime(),
				new GregorianCalendar(2019, Calendar.APRIL, 24).toZonedDateTime());

		// removendo lembre especifico
		agenda.removerLembrete(lembreteComum);
		System.out.println("\nTeste lembrete removido.");
		agenda.mostrarLembretesAtivos(new GregorianCalendar(2019, Calendar.MARCH, 2).toZonedDateTime());

	}
}
