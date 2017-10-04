package com.epam.wl.entities;

import com.epam.wl.enums.UserRole;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String passwordHash;
    private UserRole role;

}
