package com.example.inventrolabstask3.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Period {

    DAY(1),
    MONTH(30),
    QUARTER(90),
    YEAR(365),
    ALL(0);

    int days;
}
