package ar.edu.utn.frm.mutantes.controller;

import ar.edu.utn.frm.mutantes.dto.DnaRequest;
import ar.edu.utn.frm.mutantes.dto.StatsResponse;
import ar.edu.utn.frm.mutantes.service.MutantService;
import ar.edu.utn.frm.mutantes.service.StatsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MutantController.class)
class MutantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MutantService mutantService;

    @MockBean
    StatsService statsService;

    @Test
    @DisplayName("POST /mutant → 200 OK (mutante)")
    void testMutant200() throws Exception {
        when(mutantService.esMutante(any())).thenReturn(true);

        DnaRequest req = new DnaRequest(new String[]{
                "ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"
        });

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /mutant → 403 (humano)")
    void testMutant403() throws Exception {
        when(mutantService.esMutante(any())).thenReturn(false);

        DnaRequest req = new DnaRequest(new String[]{
                "ATGCGA","TTTTTT","GCGCGC","ATATAT","CACCAC","GGGGGG"
        });

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /mutant → 400 Bad Request (body vacío)")
    void test400EmptyBody() throws Exception {
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /stats retorna valores correctos")
    void testGetStats() throws Exception {
        when(statsService.getStats())
                .thenReturn(new StatsResponse(40, 100, 0.4));

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countMutantDna").value(40))
                .andExpect(jsonPath("$.countHumanDna").value(100))
                .andExpect(jsonPath("$.ratio").value(0.4));
    }
}
