package agenda.lembretes;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;

public class LembreteDiaDaSemana extends Lembrete {

	private DayOfWeek diaDaSemana;

	public LembreteDiaDaSemana(String nome, DayOfWeek diaDaSemana) {
		super(nome);
		this.diaDaSemana = diaDaSemana;
	}

	@Override
	public boolean verificar(ZonedDateTime data) {
		return diaDaSemana == data.getDayOfWeek();
	}
	
	@Override
	public boolean verificar(ZonedDateTime dataInicio, ZonedDateTime dataFim) {
		if (super.verificar(dataInicio, dataFim)) {			
			long diff = Duration.between(dataInicio, dataFim).getSeconds() / (60 * 60 * 24); //diferenca de dias entre as duas datas
			if (diff >= 7) { //diferenca maior que 7 dias, com certeza algum dia da semana esta no invervalo
				return true;
			} else if (dataInicio.getDayOfWeek().getValue() <= dataFim.getDayOfWeek().getValue()) {
				return diaDaSemana.getValue() >= dataInicio.getDayOfWeek().getValue()
						&& diaDaSemana.getValue() <= dataFim.getDayOfWeek().getValue(); //caso o dia de inicio seja antes do dia de fim
			} else if (dataInicio.getDayOfWeek().getValue() > dataFim.getDayOfWeek().getValue()) { //caso o dia de inicio seja maior que o dia de fim
				return diaDaSemana.getValue() >= dataInicio.getDayOfWeek().getValue()
						|| diaDaSemana.getValue() <= dataFim.getDayOfWeek().getValue();
			}
		}
		return false;
	}

	
}
