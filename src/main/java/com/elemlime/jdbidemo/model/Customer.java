package com.elemlime.jdbidemo.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant created;
    private Instant updated;
}
