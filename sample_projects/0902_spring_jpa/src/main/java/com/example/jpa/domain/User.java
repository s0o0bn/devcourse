package com.example.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder()
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tb")
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    private String password;
    private String name;

    public boolean isPasswordValid(String password) {
        return this.password.equals(password);
    }
}
