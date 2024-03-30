package com.bootcamp.ejercicio_link_tracker.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Link {
    int id;
    String password;
    String redirection;
    int redirectionsQuantity;
    boolean valid;

    public void incrementRedirectionQuantity(){
        this.redirectionsQuantity ++;
    }
}
