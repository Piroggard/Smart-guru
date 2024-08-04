package org.example.controller;

import org.example.enam.TypeEnum;
import org.example.servise.TypeService;
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
@DisplayName("Модульные тесты TypeController")
class TypeControllerTest {

    @Mock
    private TypeService typeService;

    @InjectMocks
    private TypeController typeController;

    @Test
    @DisplayName("getType возвращает список типов")
    void getType_ValidRequest_ReturnsListOfTypes() {
        // given
        List<TypeEnum> types = List.of(TypeEnum.ONLINE, TypeEnum.OFFLINE);

        doReturn(types).when(typeService).getType();

        // when
        List<TypeEnum> result = typeController.getType();

        // then
        assertEquals(types, result);

        verify(typeService).getType();
        verifyNoMoreInteractions(typeService);
    }
}