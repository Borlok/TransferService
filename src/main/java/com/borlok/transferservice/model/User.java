package com.borlok.transferservice.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public class User {
    private Long id;
    private String name;
    private LocalDateTime dateOfBirth;
    @ToString.Exclude
    private String password;
}
