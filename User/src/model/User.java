package model;

import lombok.*;

import java.util.UUID;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private UUID uuid;
    private String name;
    private String email;
    private boolean isDeleted;
}
