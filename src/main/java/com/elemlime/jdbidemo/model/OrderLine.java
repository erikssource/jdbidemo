package com.elemlime.jdbidemo.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
    private String name;
    private String description;
    private Integer price;
    private Instant created;
    private Instant updated;
}
