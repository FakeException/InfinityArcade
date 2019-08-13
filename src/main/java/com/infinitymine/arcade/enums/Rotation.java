package com.infinitymine.arcade.enums;

import com.infinitymine.arcade.Games.Minigame;
import com.infinitymine.arcade.Games.OITQGame;
import com.infinitymine.arcade.InfinityArcade;

public enum Rotation {

    OITQ(new OITQGame("One in the chamber"));

    Rotation(Minigame minigame) {

    }
}
