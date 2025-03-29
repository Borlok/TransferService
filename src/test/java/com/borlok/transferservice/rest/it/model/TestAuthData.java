package com.borlok.transferservice.rest.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Erofeevskiy Yuriy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAuthData {
    private String username;
    private String password;
}
