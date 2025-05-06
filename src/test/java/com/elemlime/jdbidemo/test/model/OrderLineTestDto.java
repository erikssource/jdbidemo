package com.elemlime.jdbidemo.test.model;

import java.util.UUID;

public record OrderLineTestDto(
    UUID id,
    UUID orderId,
    UUID productId,
    int quantity,
    int price
) {}
