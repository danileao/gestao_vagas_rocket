package br.com.danieleleao.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_vagas.exceptions.UserFoundException;
import br.com.danieleleao.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.danieleleao.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.danieleleao.gestao_vagas.modules.candidate.dto.CreateCandidateRequestDTO;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CreateCandidateRequestDTO candidateRequestDTO) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateRequestDTO.getUsername(), candidateRequestDTO.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var candidateEntity = CandidateEntity
                .builder()
                .username(candidateRequestDTO.getUsername())
                .name(candidateRequestDTO.getName())
                .password(candidateRequestDTO.getPassword())
                .email(candidateRequestDTO.getEmail())
                .description(candidateRequestDTO.getDescription())
                .build();

        return this.candidateRepository.save(candidateEntity);
    }

}
