package br.com.danieleleao.gestao_vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api de Gestão de Vagas", version = "1", description = "Essa API é responsável pela gestão de vagas (RH)", contact = @Contact(email = "gestao@gestaodevagas.com.br", name = "Equipe de TI Gestão de Vagas")))
public class GestaoVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVagasApplication.class, args);
	}

}
