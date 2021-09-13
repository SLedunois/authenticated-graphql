package fr.sledunois;

import java.util.UUID;

public class User {
    public UUID id;

    public User() {
        this.id = UUID.randomUUID();
    }
}
