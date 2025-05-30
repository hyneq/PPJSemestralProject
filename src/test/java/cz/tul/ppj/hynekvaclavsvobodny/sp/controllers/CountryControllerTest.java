package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CountryTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.CountryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CountryService service;

    @Autowired
    private CountryTestData data;

    private Stream<Country> objsValid() {
        return data.objsValid();
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(service.getAll())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/country/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(List.of())));
    }

    @Test
    public void testGetAllValid() throws Exception {
        List<Country> objs = objsValid().toList();

        when(service.getAll())
                .thenReturn(objs);

        mockMvc.perform(get("/api/country/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(objs.size())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdValid(Country obj) throws Exception {
        when(service.getById(obj.getId()))
                .thenReturn(Optional.of(obj));

        mockMvc.perform(get("/api/country/id/" + obj.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.is(obj.getCode())))
                .andExpect(jsonPath("$.name", Matchers.is(obj.getName())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdEmpty(Country obj) throws Exception {
        when(service.getById(obj.getId()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/country/id/" + obj.getId()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByCodeValid(Country obj) throws Exception {
        when(service.getByCode(obj.getCode()))
                .thenReturn(Optional.of(obj));

        mockMvc.perform(get("/api/country/code/" + obj.getCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.is(obj.getCode())))
                .andExpect(jsonPath("$.name", Matchers.is(obj.getName())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByCodeEmpty(Country obj) throws Exception {
        when(service.getByCode(obj.getCode()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/country/code/" + obj.getCode()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameValid(Country obj) throws Exception {
        when(service.getByName(obj.getName()))
                .thenReturn(Optional.of(obj));

        mockMvc.perform(get("/api/country/name/" + obj.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.is(obj.getCode())))
                .andExpect(jsonPath("$.name", Matchers.is(obj.getName())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameEmpty(Country obj) throws Exception {
        when(service.getByName(obj.getName()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/country/name/" + obj.getName()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testCreateValid(Country obj) throws Exception {
        when(service.create(obj))
                .thenReturn(obj.getId());

        mockMvc.perform(post("/api/country/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isOk())
                .andExpect(content().string(obj.getId()));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testUpdateValid(Country obj) throws Exception {
        doNothing().when(service).update(obj);

        mockMvc.perform(patch("/api/country/id/" + obj.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAll() throws Exception {
        doNothing().when(service).deleteAll();

        mockMvc.perform(delete("/api/country/all"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteValid(Country obj) throws Exception {
        doNothing().when(service).delete(obj.getId());

        mockMvc.perform(delete("/api/country/id/" + obj.getId()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByCodeValid(Country obj) throws Exception {
        doNothing().when(service).deleteByCode(obj.getCode());

        mockMvc.perform(delete("/api/country/code/" + obj.getCode()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByNameValid(Country obj) throws Exception {
        doNothing().when(service).deleteByName(obj.getName());

        mockMvc.perform(delete("/api/country/name/" + obj.getName()))
                .andExpect(status().isOk());
    }

}
