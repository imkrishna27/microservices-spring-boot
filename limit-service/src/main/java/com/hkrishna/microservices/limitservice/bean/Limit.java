package com.hkrishna.microservices.limitservice.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Limit {
    private int minLimit;
    private int maxLimit;
}
