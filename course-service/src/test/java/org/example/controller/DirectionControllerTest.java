package org.example.controller;

import org.example.enam.DirectionEnum;
import org.example.servise.DirectionService;
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
@DisplayName("Модульные тесты DirectionController")
class DirectionControllerTest {

    @Mock
    private DirectionService directionService;

    @InjectMocks
    private DirectionController directionController;

    @Test
    @DisplayName("getDirection возвращает список направлений")
    void getDirection_ValidRequest_ReturnsListOfDirectionEnum() {
        // given
        List<DirectionEnum> expectedDirections = List.of(DirectionEnum.DEVELOPMENT, DirectionEnum.DANCING);

        doReturn(expectedDirections).when(directionService).getDirection();

        // when
        List<DirectionEnum> actualDirections = directionController.getDirection();

        // then
        assertEquals(expectedDirections, actualDirections);

        verify(directionService).getDirection();
        verifyNoMoreInteractions(directionService);
    }
}