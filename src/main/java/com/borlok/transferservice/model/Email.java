package com.borlok.transferservice.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Entity
@Table(name = "email_data")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private String email;
}
