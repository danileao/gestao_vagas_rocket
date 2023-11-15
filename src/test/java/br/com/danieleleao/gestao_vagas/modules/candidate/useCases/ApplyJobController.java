package br.com.danieleleao.gestao_vagas.modules.candidate.useCases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danieleleao.gestao_vagas.modules.candidate.controllers.CandidateController;
import br.com.danieleleao.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.danieleleao.gestao_vagas.modules.company.controllers.JobController;
import br.com.danieleleao.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.danieleleao.gestao_vagas.modules.company.entities.JobEntity;
import br.com.danieleleao.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import br.com.danieleleao.gestao_vagas.providers.JWTCandidateProvider;
import br.com.danieleleao.gestao_vagas.providers.JWTProvider;
import br.com.danieleleao.gestao_vagas.security.SecurityConfig;

@WebMvcTest(JobController.class)
@AutoConfigureMockMvc
public class ApplyJobController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateJobUseCase createJobUseCase;

    @MockBean
    private SecurityConfig securityConfig;

   @Test
    public void testCreateJob() throws Exception {
        // Configurar comportamento esperado para o serviço mock (createJobUseCase) se necessário.
        when(createJobUseCase.execute(any(JobEntity.class))).thenReturn(new JobEntity(/* configure os valores apropriados */));

        CreateJobDTO createJobDTO = new CreateJobDTO(/* configure os valores apropriados como benefícios, descrição, nível, etc. */);

        mockMvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createJobDTO))) // Converter o objeto createJobDTO para JSON
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.algumCampo").value("algumValor")); // Verificar campos na resposta JSON
    }

    // Método para converter um objeto em JSON
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
