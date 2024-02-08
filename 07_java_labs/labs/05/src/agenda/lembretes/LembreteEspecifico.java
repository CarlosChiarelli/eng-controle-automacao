package agenda.lembretes;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class LembreteEspecifico extends LembreteMensal {

	private ZonedDateTime data;

	public LembreteEspecifico(String nome, int ano, Month mes, int dia) {
		super(nome, mes);
		// mes.getValue() - 1 pois GregorianCalendar comeca o mes em 0 enquanto ZonedDateTime em 1
		this.data = new GregorianCalendar(ano, mes.getValue() - 1, dia).toZonedDateTime(); 
	}

	@Override
	public boolean verificar(ZonedDateTime data) {
		if (super.verificar(data)) {
			return this.data.getYear() == data.getYear() && this.data.getDayOfMonth() == data.getDayOfMonth();
		}
		return false;
	}
	
	@Override
	public boolean verificar(ZonedDateTime dataInicio, ZonedDateTime dataFim) {
		if (super.verificar(dataInicio, dataFim)) {
			// tambem considera intervalo fechado
			return (this.verificar(dataInicio) || this.verificar(dataFim))
					|| (this.data.isAfter(dataInicio) && this.data.isBefore(dataFim));

		}
		return false;
	}



}
