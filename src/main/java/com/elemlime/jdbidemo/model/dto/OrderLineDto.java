package com.elemlime.jdbidemo.model.dto;

import java.util.UUID;

public record OrderLineDto(UUID orderId, UUID productId, int quantity, int price) {}
