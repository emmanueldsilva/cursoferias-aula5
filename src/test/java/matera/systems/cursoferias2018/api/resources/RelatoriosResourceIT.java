package matera.systems.cursoferias2018.api.resources;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.domain.response.RelatorioResponse;
import matera.systems.cursoferias2018.api.repository.DisciplinasRepositoryStub;

public class RelatoriosResourceIT {

	static final String API = "/api";
	static final String VERSION = "/v1";
    static final String RELATORIO_URL = "/relatorio";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final int OK_STATUS_CODE = 200;
	
    @Test
    public void test() {
        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header(CONTENT_TYPE_HEADER, "application/json")
                        .queryParam("dataInicio", "2000-01-01")
                        .queryParam("dataTermino", "2001-01-01")
                    .when()
                        .get(API + VERSION + RELATORIO_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_1.toString())
                    .thenReturn();
        
        final List<RelatorioResponse> registros = Arrays.asList(response.getBody().as(RelatorioResponse[].class));
        
        Assert.assertEquals(OK_STATUS_CODE, response.getStatusCode());
        Assert.assertEquals(0, registros.size());
        
        
//        RelatorioResponse relatorioResponse = registros.get(0);
//        Assert.assertNotNull(relatorioResponse.getUsuarioResponse());
//        Assert.assertThat(relatorioResponse.getPresenca(), Matchers.not(Matchers.empty()));
    }
    
    private Header getAuthorizationHeader() {

        String clientBasicAuthCredentials =
                Base64.getEncoder().encodeToString("angular:alunos".getBytes());

        Response response = RestAssured.given()
                .header(new Header("Authorization", "Basic " + clientBasicAuthCredentials))
                .queryParam("username", "usuario")
                .queryParam("password", "password")
                .queryParam("grant_type", "password")
                .when()
                .post("/oauth/token")
                .then().extract().response();

        String token = response.getBody().jsonPath().getString("access_token");

        return new Header("Authorization", "bearer " + token);
    }
    
}
