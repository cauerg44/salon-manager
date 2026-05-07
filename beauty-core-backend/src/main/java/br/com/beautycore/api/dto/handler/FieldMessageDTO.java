package br.com.beautycore.api.dto.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessageDTO {

    private String fieldName;
    private String message;
}
