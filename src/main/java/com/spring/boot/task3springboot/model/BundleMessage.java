package com.spring.boot.task3springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BundleMessage {
    @JsonProperty("message_ar")
    private String messageAr;
    @JsonProperty("message_en")
    private String messageEn;
}
