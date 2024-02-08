package agenda.lembretes;

import java.time.ZonedDateTime;

public class Lembrete {

	private String name;

	public Lembrete(String name) {
		this.name = name;
	}

	public boolean verificar(ZonedDateTime data) {
		return true;
	}
	
	public boolean verificar(ZonedDateTime dataInicio, ZonedDateTime dataFim) {
		//retorna verdadeiro se sao as mesmas datas ou dataInicio e' antes de dataFim
		return (dataInicio.getYear() == dataFim.getYear() && dataInicio.getMonth() == dataFim.getMonth()
				&& dataInicio.getDayOfMonth() == dataFim.getDayOfMonth()) || dataInicio.isBefore(dataFim);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
