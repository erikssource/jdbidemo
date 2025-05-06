package com.elemlime.jdbidemo.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private UUID id;
    private OrderStatus status;
    private Instant created;
    private Instant updated;
    private Customer customer;
    private List<OrderLine> orderLines = new ArrayList<>();
}
