package ru.aston.springtask2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.aston.springtask2.dtos.request.AddLocalityRequestDTO;
import ru.aston.springtask2.model.Locality;
import ru.aston.springtask2.service.LocalityService;
import ru.aston.springtask2.service.WeatherService;
import ru.aston.springtask2.util.DataUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(LocalityController.class)
class LocalityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LocalityService localityService;

    @MockBean
    private WeatherService weatherService;

    @Test
    @DisplayName("Test create locality functionality")
    void givenAddLocalityRequestDTO_whenCreateLocality_thenSuccessResponse() throws Exception {
        //given
        AddLocalityRequestDTO dto = DataUtils.getHomelLocalityDTOTransient();
        Locality locality = DataUtils.getHomelLocalityPersisted();
        BDDMockito.given(localityService.create(any(Locality.class)))
                .willReturn(locality);
        //when
        ResultActions result = mockMvc.perform(post("/api/v1/locality")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Gomel")));
    }
}
