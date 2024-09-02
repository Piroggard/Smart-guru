package org.example.servise;

import org.example.enam.TypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты TypeService")
class TypeServiceTest {

    @InjectMocks
    private TypeService typeService;

    @Test
    @DisplayName("getType возвращает список типов")
    void getType_ReturnsAllTypeEnums() {
        // when
        List<TypeEnum> result = typeService.getType();

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(TypeEnum.values().length, result.size(), "The size of the list must match the number of items in the TypeEnum");
        assertTrue(result.containsAll(List.of(TypeEnum.values())), "The list must contain all the values of the TypeEnum");
    }
}