package com.borlok.transferservice.model;

import lombok.Data;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@NamedEntityGraph (
        name = "email_user",
        attributeNodes = {
                @NamedAttributeNode(value = "user")
        }
)
@Entity
@Table(name = "email_data")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private String email;
}
