package models;

import lombok.*;

@Getter
@Setter
@Builder
public class User {
    private String email;
    private String password;


}