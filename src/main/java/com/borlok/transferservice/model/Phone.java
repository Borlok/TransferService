package com.borlok.transferservice.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Entity
@Table(name = "phone_data")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private String phone;
}
