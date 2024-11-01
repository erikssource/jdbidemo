package com.elemlime.jdbidemo.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private String description;
    private String category;
    private Integer price;
    private Integer inventory;
    private Instant created;
    private Instant updated;
}
