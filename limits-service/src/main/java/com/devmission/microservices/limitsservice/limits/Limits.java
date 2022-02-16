package com.devmission.microservices.limitsservice.limits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Limits {

    private int min;
    private int max;
}
