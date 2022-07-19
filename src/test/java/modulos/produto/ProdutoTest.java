package modulos.produto;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de APIRest do modulo de Produto")
public class ProdutoTest {
    @Test
    @DisplayName("Validar os limites proibidos do valor do produto")
    public void testValidarLimitesProibidosValorProduto(){
     //Configurando os dados da API rest da lojinha
        baseURI="http://165.227.93.41";
       //porta onde a aplicação esta rodando, caso tenha porta:  port=8080;
        basePath="/lojinha";   //caminho inicial da minha aplicação

        //Obter token do usuario admin
        String token = given() //variável token
                .contentType(ContentType.JSON) //dado que eu envie um contenttype com este body
                .body("{\n" +
                        "  \"usuarioLogin\": \"admin\",\n" +
                        "  \"usuarioSenha\": \"admin\"\n" +
                        "}") //representa o que faziamos na aba body no postaman
            .when()  //qual é o método que eu quero usar
                .post("/v2/login") //método // quando eu fizer um post para v2/login no ip tal, na lojinha tal
            .then()     //então, oque que a gente quer que aconteça depois que enviamos a requisição
                .extract() //extrair o token
                    .path("data.token"); // dentro date, pegue o token.

        System.out.println(token);


     //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o status code retornado foi 422

    }
}
