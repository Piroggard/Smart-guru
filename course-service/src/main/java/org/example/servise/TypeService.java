package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TypeService {
    public List<TypeEnum> getType() {
        log.info("Метод getType");
        List<TypeEnum> typeEnums = new ArrayList<>();
        for (TypeEnum day : TypeEnum.values()) {
            typeEnums.add(day);
        }
        return typeEnums;
    }
}
