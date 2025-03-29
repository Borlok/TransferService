package com.borlok.transferservice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "initial_balance", updatable = false)
    private BigDecimal initialBalance = BigDecimal.ZERO;
    @Column
    private BigDecimal balance = BigDecimal.ZERO;
}
