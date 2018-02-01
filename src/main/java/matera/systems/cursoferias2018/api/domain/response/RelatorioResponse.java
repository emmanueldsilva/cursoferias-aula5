package matera.systems.cursoferias2018.api.domain.response;

import java.util.ArrayList;
import java.util.List;

public class RelatorioResponse {

	private UsuarioResponse usuarioResponse;
	
	private List<String> presenca = new ArrayList<>();
	
	private Integer frequencia;

	public UsuarioResponse getUsuarioResponse() {
		return usuarioResponse;
	}

	public void setUsuarioResponse(UsuarioResponse usuarioResponse) {
		this.usuarioResponse = usuarioResponse;
	}

	public List<String> getPresenca() {
		return presenca;
	}

	public void setPresenca(List<String> presenca) {
		this.presenca = presenca;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}
	
}
