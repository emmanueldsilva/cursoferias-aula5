package matera.systems.cursoferias2018.api.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import matera.systems.cursoferias2018.api.domain.response.RelatorioResponse;
import matera.systems.cursoferias2018.api.services.RelatorioService;

@RestController
@RequestMapping(path = "/api/v1/relatorio")
public class RelatoriosRS {

	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping(path = "/{idDisciplina}",
			produces = "application/json")
	public ResponseEntity<List<RelatorioResponse>> listPresenca(@PathVariable(required = true) String idDisciplina, 
													 			@RequestParam(required = true) String dataInicio,
													 			@RequestParam(required = true) String dataTermino) {
		try {
			final List<RelatorioResponse> relatorio = relatorioService.listPresenca(UUID.fromString(idDisciplina), readDataFromString(dataInicio), readDataFromString(dataTermino));
			return ResponseEntity.ok(relatorio);
		} catch (Exception e) {	
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	private Date readDataFromString(String data) throws Exception {
        return new SimpleDateFormat("YYYY-MM-DD").parse(data);
    }
	
}
