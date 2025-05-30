package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CityTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.CityService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.mapToArguments;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CityService service;

    @Autowired
    private CityTestData data;

    private Stream<City> objsValid() {
        return data.objsValid();
    }

    private Stream<Arguments> objsValidByCountry() {
        return mapToArguments(data.objsValidGroupedByCountry());
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(service.getAll())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/city/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(List.of())));
    }

    @Test
    public void testGetAllValid() throws Exception {
        List<City> objs = objsValid().toList();

        when(service.getAll())
                .thenReturn(objs);

        mockMvc.perform(get("/api/city/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(objs.size())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdValid(City obj) throws Exception {
        when(service.getById(obj.getId()))
                .thenReturn(Optional.of(obj));

        mockMvc.perform(get("/api/city/id/" + obj.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(obj.getId())))
                .andExpect(jsonPath("$.name", Matchers.is(obj.getName())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdEmpty(City obj) throws Exception {
        when(service.getById(obj.getId()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/city/id/" + obj.getId()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryCodeValid(Country country, List<City> objs) throws Exception {
        when(service.getByCountryCode(country.getCode()))
                .thenReturn(Optional.of(objs));

        mockMvc.perform(get("/api/city/country_code/" + country.getCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(objs.size())));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryCodeEmpty(Country country, List<City> objs) throws Exception {
        when(service.getByCountryCode(country.getCode()))
                .thenReturn(Optional.of(List.of()));

        mockMvc.perform(get("/api/city/country_code/" + country.getCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(List.of())));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryCodeNotFound(Country country, List<City> objs) throws Exception {
        when(service.getByCountryCode(country.getCode()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/city/country_code/" + country.getCode()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryNameValid(Country country, List<City> objs) throws Exception {
        when(service.getByCountryName(country.getName()))
                .thenReturn(Optional.of(objs));

        mockMvc.perform(get("/api/city/country_name/" + country.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(objs.size())));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryNameEmpty(Country country, List<City> objs) throws Exception {
        when(service.getByCountryName(country.getName()))
                .thenReturn(Optional.of(List.of()));

        mockMvc.perform(get("/api/city/country_name/" + country.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(List.of())));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryNameNotFound(Country country, List<City> objs) throws Exception {
        when(service.getByCountryName(country.getName()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/city/country_name/" + country.getName()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameValid(City obj) throws Exception {
        when(service.getByName(obj.getName()))
                .thenReturn(Optional.of(obj));

        mockMvc.perform(get("/api/city/name/" + obj.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(obj.getId())))
                .andExpect(jsonPath("$.name", Matchers.is(obj.getName())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameEmpty(City obj) throws Exception {
        when(service.getByName(obj.getName()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/city/name/" + obj.getName()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testCreateValid(City obj) throws Exception {
        when(service.create(obj))
                .thenReturn(obj.getId());

        mockMvc.perform(post("/api/city/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isOk())
                .andExpect(content().string(obj.getId().toString()));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testUpdateValid(City obj) throws Exception {
        doNothing().when(service).update(obj);

        mockMvc.perform(patch("/api/city/id/" + obj.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAll() throws Exception {
        doNothing().when(service).deleteAll();

        mockMvc.perform(delete("/api/city/all"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteValid(City obj) throws Exception {
        doNothing().when(service).delete(obj.getId());

        mockMvc.perform(delete("/api/city/id/" + obj.getId()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountryCodeValid(Country country, List<City> objs) throws Exception {
        doNothing().when(service).deleteByCountryCode(country.getCode());

        mockMvc.perform(delete("/api/city/country_code/" + country.getCode()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountryNameValid(Country country, List<City> objs) throws Exception {
        doNothing().when(service).deleteByCountryName(country.getName());

        mockMvc.perform(delete("/api/city/country_name/" + country.getName()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByNameValid(City obj) throws Exception {
        doNothing().when(service).deleteByName(obj.getName());

        mockMvc.perform(delete("/api/city/name/" + obj.getName()))
                .andExpect(status().isOk());
    }
}
