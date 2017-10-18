package com.epam.wl.entities;

import com.epam.wl.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String passwordHash;
    private UserRole role;
}
