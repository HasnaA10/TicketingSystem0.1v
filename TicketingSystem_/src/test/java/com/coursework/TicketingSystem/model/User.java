package com.coursework.TicketingSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t01user")
@Data

public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String password;

        @Enumerated(EnumType.STRING)
        private UserType userType;

        public String generateUsername() {
            return firstName.toLowerCase().replace(" ", "_") + "_" + (int) (Math.random() * 1000);
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }


