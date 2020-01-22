package de.zenhomes.assignment.rest;

import de.zenhomes.assignment.model.CounterInfoDto;
import de.zenhomes.assignment.model.CounterRecordDto;
import de.zenhomes.assignment.service.CounterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CounterService counterService;

    @Test
    public void testGetCounterInfoEndpointSuccess() throws Exception {
        when(counterService.getCounterInfo(1L)).thenReturn(new CounterInfoDto(1L, "name"));
        this.mockMvc.perform(get("/counter?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"village_name\":\"name\"}")));
    }

    @Test
    public void testGetCounterInfoEndpointError() throws Exception {
        when(counterService.getCounterInfo(1L)).thenThrow(NoSuchElementException.class);
        this.mockMvc.perform(get("/counter?id=1")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("\"code\":\"BAD_REQUEST\",\"httpCode\":400,\"message\":\"No such element found.\"")));
    }


    @Test
    public void testPostCounterRecordEndpointError() throws Exception {
        doThrow(NoSuchElementException.class).when(counterService).saveCounterRecord(any(CounterRecordDto.class));
        this.mockMvc.perform(
                post("/counter_callback")
                        .contentType(APPLICATION_JSON)
                        .content("{\"counter_id\":1,\"amount\":200}"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("\"code\":\"BAD_REQUEST\",\"httpCode\":400,\"message\":\"No such element found.\"")));
    }

    @Test
    public void testPostCounterRecordEndpointSuccess() throws Exception {
        doNothing().when(counterService).saveCounterRecord(any(CounterRecordDto.class));
        this.mockMvc.perform(
                post("/counter_callback")
                        .contentType(APPLICATION_JSON)
                        .content("{\"counter_id\":1,\"amount\":200}"))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("")));
    }
}