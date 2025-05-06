package com.elemlime.jdbidemo.test.model;

import java.util.UUID;

public record ProductTestDto(UUID id,
                             UUID categoryId,
                             String name,
                             String description,
                             int price,
                             int inventory) {}
