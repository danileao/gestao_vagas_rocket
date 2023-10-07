package br.com.danieleleao.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleao.gestao_vagas.exceptions.UserFoundException;
import br.com.danieleleao.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.danieleleao.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.danieleleao.gestao_vagas.modules.candidate.dto.CreateCandidateRequestDTO;
import br.com.danieleleao.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Tag(name = "Candadite", description = "Informações do meu candidato")
    @Operation(summary = "Cadastro de candidato", description = "Essa operação é para o cadastro de um candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CandidateEntity.class), mediaType = "application/json"),
            }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCandidateRequestDTO createCandidateRequest) {
        try {
            var result = this.createCandidateUseCase.execute(createCandidateRequest);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
