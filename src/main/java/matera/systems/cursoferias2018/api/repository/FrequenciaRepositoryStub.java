package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Profile("stub")
public class FrequenciaRepositoryStub implements FrequenciaRepository {

    private Map<EntryKey, FrequenciaEntity> frequencias = new HashMap<>();


    @Override
    public void incluir(UUID disciplina, UUID aluno, Date data) {
        final EntryKey key = new EntryKey(disciplina, aluno);
        if (frequencias.containsKey(key)) {
            FrequenciaEntity entity = frequencias.get(key);
            entity.addPresenca(data);
            frequencias.put(new EntryKey(disciplina, aluno), entity);
        } else {
            FrequenciaEntity entity = new FrequenciaEntity();
            entity.setDisciplinaId(disciplina);
            entity.setUsuarioId(aluno);
            frequencias.put(new EntryKey(disciplina, aluno), entity);
        }
    }

    @Override
    public void remover(UUID disciplina, UUID aluno, Date data) {
        final EntryKey key = new EntryKey(disciplina, aluno);
        if (frequencias.containsKey(key)) {
            FrequenciaEntity entity = frequencias.get(key);
            entity.removePresenca(data);
        }
    }
    
    @Override
    public List<FrequenciaEntity> list(UUID disciplina, Date dataInicio, Date dataTermino) {
    	final List<FrequenciaEntity> allFrequencias = new ArrayList<>();
    	for (FrequenciaEntity frequencia : frequencias.values()) {
    		if (frequencia.getDisciplinaId().equals(disciplina)) {  
    			
    			final Set<String> presencas = frequencia.getPresencas();
    			for (String presenca : presencas) {
					final Date dataPresenca = readDataFromString(presenca);
					
					if (dataInicio.before(dataPresenca) && dataTermino.after(dataPresenca)) {
						allFrequencias.add(frequencia);
					}
				}
			}
		}
    	
    	return allFrequencias;
    }
    
    private Date readDataFromString(String data) {
        try {
			return new SimpleDateFormat("YYYY-MM-DD").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return null;
    }

    private class EntryKey {

        private final UUID disciplina, aluno;

        EntryKey(UUID disciplina, UUID aluno) {
            this.disciplina = disciplina;
            this.aluno = aluno;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EntryKey entryKey = (EntryKey) o;
            return Objects.equals(disciplina, entryKey.disciplina) &&
                    Objects.equals(aluno, entryKey.aluno);
        }

        @Override
        public int hashCode() {
            return Objects.hash(disciplina, aluno);
        }
    }

}
