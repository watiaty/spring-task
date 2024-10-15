package ru.aston.springtask2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.aston.springtask2.dtos.request.AddAttractionRequestDTO;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;
import ru.aston.springtask2.exception.AttractionNotFoundException;
import ru.aston.springtask2.service.AttractionService;
import ru.aston.springtask2.service.LocalityService;
import ru.aston.springtask2.util.DataUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AttractionController.class)
class AttractionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AttractionService attractionService;

    @MockBean
    private LocalityService localityService;

    @Test
    @DisplayName("Test create attraction functionality")
    void givenAddAttractionRequestDTO_whenCreateAttraction_thenSuccessResponse() throws Exception {
        //given
        AddAttractionRequestDTO dto = DataUtils.getAttractionRequestDTOParkPersisted();
        Attraction attraction = DataUtils.getHomelParkPersisted();
        BDDMockito.given(attractionService.createAttraction(any(Attraction.class)))
                .willReturn(attraction);
        BDDMockito.given(localityService.findById(anyLong()))
                .willReturn(DataUtils.getHomelLocalityPersisted());
        //when
        ResultActions result = mockMvc.perform(post("/api/v1/attraction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("aaa")));
    }

    @Test
    @DisplayName("Test get attractions by sort order and type functionality")
    void givenSortOrderAndAttractionType_whenGetAttraction_thenSuccessResponse() throws Exception {
        //given
        Attraction attraction1 = DataUtils.getHomelParkPersisted();
        Attraction attraction2 = DataUtils.getHomelPark2Persisted();
        Page<Attraction> attractions = new PageImpl<>(List.of(attraction1, attraction2));
        Pageable pageable = DataUtils.getPageableWithSortByName();
        BDDMockito.given(attractionService.getAllByType(AttractionType.PARK, pageable))
                .willReturn(attractions);
        //when
        ResultActions result = mockMvc.perform(get("/api/v1/attraction?page=0&size=1&sort=name&type=PARK")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2));
    }

    @Test
    @DisplayName("Test get attractions by locality id functionality")
    void givenLocalityId_whenGetAttractionByLocalityId_thenSuccessResponse() throws Exception {
        //given
        var id = 1L;
        Attraction attraction1 = DataUtils.getHomelParkPersisted();
        Attraction attraction2 = DataUtils.getHomelPark2Persisted();
        Page<Attraction> attractions = new PageImpl<>(List.of(attraction1, attraction2));
        BDDMockito.given(attractionService.getAllByLocalityId(id, DataUtils.getPageable()))
                .willReturn(attractions);
        //when
        ResultActions result = mockMvc.perform(get("/api/v1/attraction/" + id + "?page=0&size=1")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2));
    }

    @Test
    @DisplayName("Test update attraction functionality")
    void givenIdAndDescription_whenUpdateAttraction_thenSuccessResponse() throws Exception {
        //given
        var id = 1L;
        var newDescription = "new description";
        Attraction attraction = DataUtils.getHomelParkPersisted();
        attraction.setDescription(newDescription);
        BDDMockito.given(attractionService.updateDescription(anyLong(), anyString()))
                .willReturn(attraction);
        //when
        ResultActions result = mockMvc.perform(patch("/api/v1/attraction/" + id + "?description=" + newDescription)
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(newDescription)));
    }

    @Test
    @DisplayName("Test update attraction functionality")
    void givenIdAndDescription_whenUpdateAttraction_thenThrowException() throws Exception {
        //given
        var id = 1L;
        var newDescription = "new description";
        Attraction attraction = DataUtils.getHomelParkPersisted();
        attraction.setDescription(newDescription);
        BDDMockito.given(attractionService.updateDescription(anyLong(), anyString()))
                .willThrow(new AttractionNotFoundException(id));
        //when
        ResultActions result = mockMvc.perform(patch("/api/v1/attraction/" + id + "?description=" + newDescription)
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Test remove attraction functionality")
    void givenId_whenRemoveAttraction_thenSuccessResponse() throws Exception {
        //given
        var id = 1L;
        BDDMockito.doNothing().when(attractionService).deleteAttractionById(anyLong());
        //when
        ResultActions result = mockMvc.perform(delete("/api/v1/attraction/" + id)
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
