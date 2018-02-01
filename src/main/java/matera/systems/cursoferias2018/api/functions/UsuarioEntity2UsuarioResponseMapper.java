package matera.systems.cursoferias2018.api.functions;

import java.util.function.Function;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;

public class UsuarioEntity2UsuarioResponseMapper implements Function<UsuarioEntity, UsuarioResponse> {

	@Override
	public UsuarioResponse apply(UsuarioEntity entity) {
		UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getUuid().toString());
        response.setNome(entity.getNome());
        response.setLogin(entity.getLogin());
        response.setEmail(entity.getEmail());
        response.setPerfil(entity.getPerfil());
        response.setUrlFoto(entity.getUrlFoto());
        return response;
	}

}
