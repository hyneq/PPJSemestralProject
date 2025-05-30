package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.MeasurementTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.MeasurementService;
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
public class MeasurementControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MeasurementService service;

    @Autowired
    private MeasurementTestData data;

    private Stream<Measurement> objsValid() {
        return data.objsValid();
    }

    private Stream<Arguments> objsValidByCity() {
        return mapToArguments(data.objsValidGroupedByCity());
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        when(service.getAll())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/measurement/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(List.of())));
    }

    @Test
    public void testGetAllValid() throws Exception {
        List<Measurement> objs = objsValid().toList();

        when(service.getAll())
                .thenReturn(objs);

        mockMvc.perform(get("/api/measurement/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(objs.size())));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCity")
    public void testGetByCityIdValid(City city, List<Measurement> objs) throws Exception {
        when(service.getByCityId(city.getId()))
                .thenReturn(Optional.of(objs));

        mockMvc.perform(get("/api/measurement/city_id/" + city.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(objs.size())));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCity")
    public void testGetByCityIdEmpty(City city, List<Measurement> objs) throws Exception {
        when(service.getByCityId(city.getId()))
                .thenReturn(Optional.of(List.of()));

        mockMvc.perform(get("/api/measurement/city_id/" + city.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(List.of())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdValid(Measurement obj) throws Exception {
        when(service.getById(obj.getCityId(), obj.getDatetime()))
                .thenReturn(Optional.of(obj));

        mockMvc.perform(get("/api/measurement/id/" + obj.getCityId() + "/" + obj.getDatetime()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city.id", Matchers.is(obj.getCityId())))
                .andExpect(jsonPath("$.datetime", Matchers.is(obj.getDatetime().toString())));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdEmpty(Measurement obj) throws Exception {
        when(service.getById(obj.getCityId(), obj.getDatetime()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/measurement/id/" + obj.getId()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testCreateValid(Measurement obj) throws Exception {
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
    public void testUpdateValid(Measurement obj) throws Exception {
        doNothing().when(service).update(obj);

        mockMvc.perform(get("/api/measurement/id/" + obj.getCityId() + "/" + obj.getDatetime())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAll() throws Exception {
        doNothing().when(service).deleteAll();

        mockMvc.perform(delete("/api/measurement/all"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteValid(Measurement obj) throws Exception {
        doNothing().when(service).deleteById(obj.getCityId(), obj.getDatetime());

        mockMvc.perform(get("/api/measurement/id/" + obj.getCityId() + "/" + obj.getDatetime()))
                .andExpect(status().isOk());
    }

}
