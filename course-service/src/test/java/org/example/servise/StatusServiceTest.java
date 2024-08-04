package org.example.servise;

import org.example.enam.StatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты StatusService")
class StatusServiceTest {

    @InjectMocks
    StatusService statusService;

    @Test
    @DisplayName("getStatus возвращает список статусов")
    void getStatus_ReturnsAllStatusEnumValues() {
        // when
        List<StatusEnum> result = statusService.getStatus();

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(StatusEnum.values().length, result.size(), "The size of the list must match the number of items in the StatusEnum");
        assertTrue(result.containsAll(List.of(StatusEnum.values())), "The list must contain all the values of the StatusEnum");
    }
}