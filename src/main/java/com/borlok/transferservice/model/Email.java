package com.borlok.transferservice.model;

import lombok.Data;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public class Email {
    private Long id;
    private User userId;
    private String email;
}
