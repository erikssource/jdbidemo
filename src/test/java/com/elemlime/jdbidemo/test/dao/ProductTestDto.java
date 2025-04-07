package com.elemlime.jdbidemo.test.dao;

import java.util.UUID;

public record ProductTestDto(UUID id,
                             String name,
                             String description,
                             int price,
                             int inventory) {}
