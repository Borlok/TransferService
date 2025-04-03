package com.borlok.transferservice.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@NamedEntityGraph (
        name = "phone_user",
        attributeNodes = {
                @NamedAttributeNode(value = "user")
        }
)
@Entity
@Table(name = "phone_data")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private String phone;
}
