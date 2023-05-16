package com.ra.contentcalendar.model;

import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;


public record Content(
    Integer id,
    @NotBlank //to do validations
    String title,
    String desc,
    Status status,
    Type contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String url
){
}
