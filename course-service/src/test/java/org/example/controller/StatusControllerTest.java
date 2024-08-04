package org.example.controller;

import org.example.enam.StatusEnum;
import org.example.servise.StatusService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты StatusController")
class StatusControllerTest {

    @Mock
    private StatusService statusService;

    @InjectMocks
    private StatusController statusController;

    @Test
    @DisplayName("getStatus возвращает список статусов")
    void getStatus_ValidRequest_ReturnsListOfStatusEnum() {
        // given
        List<StatusEnum> expectedStatuses = List.of(StatusEnum.ACTIVE, StatusEnum.INACTIVE);

        doReturn(expectedStatuses).when(statusService).getStatus();

        // when
        List<StatusEnum> actualStatuses = statusController.getStatus();

        // then
        assertEquals(expectedStatuses, actualStatuses);

        verify(statusService).getStatus();
        verifyNoMoreInteractions(statusService);
    }
}