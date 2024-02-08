package agenda.lembretes;

import java.time.Month;
import java.time.ZonedDateTime;

public class LembreteMensal extends Lembrete {

	private Month mes;

	public LembreteMensal(String nome, Month mes) {
		super(nome);
		this.mes = mes;
	}

	@Override
	public boolean verificar(ZonedDateTime data) {
		return mes == data.getMonth();
	}
	
	@Override
	public boolean verificar(ZonedDateTime dataInicio, ZonedDateTime dataFim) {
		if (super.verificar(dataInicio, dataFim)) {
			if (dataInicio.getYear() == dataFim.getYear()) { //caso as datas estejam no mesmo ano
				return mes.getValue() >= dataInicio.getMonth().getValue() && mes.getValue() <= dataFim.getMonth().getValue();
			} else if (dataInicio.getYear() < dataFim.getYear()) { //caso as datas estejam em anos diferentes
				return mes.getValue() >= dataInicio.getMonth().getValue() || mes.getValue() <= dataFim.getMonth().getValue();
			}
		}
		return false;
	}


}
