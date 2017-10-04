package com.epam.wl.entities;

import com.epam.wl.enums.Options;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String passwordHash;
    private Options role;

}
