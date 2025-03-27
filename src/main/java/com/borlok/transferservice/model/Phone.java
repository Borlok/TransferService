package com.borlok.transferservice.model;

import lombok.Data;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public class Phone {
    private Long id;
    private User userId;
    private String phone;
}
