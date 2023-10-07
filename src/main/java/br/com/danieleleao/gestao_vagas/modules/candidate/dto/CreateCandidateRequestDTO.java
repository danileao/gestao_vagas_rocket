package br.com.danieleleao.gestao_vagas.modules.candidate.dto;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateCandidateRequestDTO {

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "user_name")
    private String username;

    @Schema(example = "Name Test")
    private String name;

    @Schema(example = "email@email.com")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Schema(example = "pass1234567")
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    private String password;

    @Schema(example = "Description of candidate")
    private String description;
}
