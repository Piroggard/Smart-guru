package org.example.servise;

import org.example.enam.DirectionEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты DirectionService")
class DirectionServiceTest {

    @InjectMocks
    DirectionService directionService;

    @Test
    @DisplayName("getDirection возвращает список направлений")
    void getDirection_ReturnsAllDirectionEnums() {
        // when
        List<DirectionEnum> result = directionService.getDirection();

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(DirectionEnum.values().length, result.size(), "The size of the list must match the number of items in the DirectionEnum");
        assertTrue(result.containsAll(List.of(DirectionEnum.values())), "The list must contain all DirectionEnum values");
    }
}