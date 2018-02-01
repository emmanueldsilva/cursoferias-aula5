package matera.systems.cursoferias2018.api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import matera.systems.cursoferias2018.api.domain.response.RelatorioResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.functions.UsuarioEntity2UsuarioResponseMapper;
import matera.systems.cursoferias2018.api.repository.FrequenciaRepository;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;

@Service
public class RelatorioService {
	
	private RelatorioResponse RELATORIO_VAZIO = new RelatorioResponse();
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<RelatorioResponse> listPresenca(UUID disciplinaId, Date dataInicio, Date dataTermino) throws Exception {
		return frequenciaRepository
				.list(disciplinaId, dataInicio, dataTermino)
				.stream()
				.map(f -> toResponse(f))
				.collect(Collectors.toList());
	}
	
	private RelatorioResponse toResponse(FrequenciaEntity frequencia) {

		Function<UsuarioResponse, RelatorioResponse> buildRelatorio = usuario -> {
			final RelatorioResponse relatorio = new RelatorioResponse();
			relatorio.setUsuarioResponse(usuario);
			relatorio.setPresenca(new ArrayList<>(frequencia.getPresencas()));
			relatorio.setFrequencia(frequencia.getPresencas().size());
			return relatorio;
		};
		
		return usuarioRepository
			.findByID(frequencia.getUsuarioId())
			.map(new UsuarioEntity2UsuarioResponseMapper())
			.map(buildRelatorio)
			.orElse(RELATORIO_VAZIO);
	}
	
}
