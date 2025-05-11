package com.example.pets;

import com.example.pets.controllers.OwnerController;
import com.example.pets.service.OwnerService;
import com.example.pets.mappers.OwnerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OwnerController.class)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private OwnerMapper ownerMapper;

    @Test
    void testGetAllOwners() throws Exception {
        when(ownerService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/owners"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllOwners() throws Exception {
        doNothing().when(ownerService).deleteAll();

        mockMvc.perform(delete("/api/owners"))
                .andExpect(status().isOk());
    }
}
