package com.infinitymine.arcade.Games;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Minigame {

    private String name;

    public Minigame(String name) {
        this.name = name;
    }
}
