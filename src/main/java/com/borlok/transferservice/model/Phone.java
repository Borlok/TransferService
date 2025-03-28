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
    @JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
    private User user;
    private String phone;
}
