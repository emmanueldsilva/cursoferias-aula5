package matera.systems.cursoferias2018.api.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;

public interface FrequenciaRepository {

    void incluir(UUID disciplina, UUID aluno, Date data);

    void remover(UUID disciplina, UUID aluno, Date data);
    
    List<FrequenciaEntity> list(UUID disciplina, Date dataInicio, Date dataTermino) throws Exception;
    
}
