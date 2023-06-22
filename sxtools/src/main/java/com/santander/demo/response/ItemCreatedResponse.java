package com.santander.demo.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemCreatedResponse {
    private Long id;
    private LocalDateTime createDate;
}
