//package com.org.example.controller.service;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.UUID;
//
//import com.org.example.controller.CertificateController;
//import com.org.example.dto.CertificateDto;
//import com.org.example.service.CertificatesService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//@WebMvcTest(CertificateController.class)
//public class CertificateControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    CertificatesService certificatesService;
//    ;
//
//    @InjectMocks
//    CertificateController certificateController;
//
//    private UUID certificateId;
//    private CertificateDto certificateDto;
//
//    @BeforeEach
//    void setUp() {
//        certificateId = UUID.randomUUID();
//        certificateDto = CertificateDto.builder()
//                .id(certificateId)
//                .name("Test Certificate")
//                .courseId(UUID.randomUUID())
//                .userId(UUID.randomUUID())
//                .build();
//    }
//
//    @Test
//    void testFindById() throws Exception {
//        when(certificatesService.getCertificate(certificateId)).thenReturn(certificateDto);
//
//        mockMvc.perform(get("/api/v1/certificate/{id}", certificateId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(certificateDto.getId().toString()));
//    }
//
//    @Test
//    void testCreateCertificate() throws Exception {
//        when(certificatesService.saveCertificate(any(CertificateDto.class))).thenReturn(certificateDto);
//        mockMvc.perform(post("/api/v1/certificate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(certificateDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(certificateDto.getId().toString()));
//    }
//
//    @Test
//    void testDeleteCertificate() throws Exception {
//        doNothing().when(certificatesService).deleteCertificate(certificateId);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/certificate/{id}", certificateId))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testUpdate() throws Exception {
//        when(certificatesService.updateCertificate(any(CertificateDto.class))).thenReturn(certificateDto);
//
//        mockMvc.perform(patch("/api/v1/certificate/{id}", certificateId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(certificateDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(certificateDto.getId().toString()));
//    }
//
//}
