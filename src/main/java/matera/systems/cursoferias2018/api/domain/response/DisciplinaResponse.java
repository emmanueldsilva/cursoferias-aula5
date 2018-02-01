package matera.systems.cursoferias2018.api.domain.response;

import java.util.List;

public class DisciplinaResponse {

    private String id;
    private String descricao;
    private List<String> professores;
    private String dataInicio;
    private String dataTermino;
    private String segmento;
    private String urlLogo;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public List<String> getProfessores() {
		return professores;
	}

	public void setProfessores(List<String> professores) {
		this.professores = professores;
	}

	public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
