package com.aor.hero;

import com.aor.hero.arena.*;
import com.aor.hero.gui.Gui;
import com.aor.hero.creator.ArenaCreator;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        ArenaCreator creator = new ArenaCreator();
        Arena arena = creator.createArena(50, 25, 10, 20, 50);

        Gui gui = new Gui(arena);

        while (!arena.isFinished()) {
            gui.draw();

            Gui.MOVE movement = gui.getNextMovement();

            if (movement == Gui.MOVE.DOWN) arena.moveHeroDown();
            if (movement == Gui.MOVE.UP) arena.moveHeroUp();
            if (movement == Gui.MOVE.LEFT) arena.moveHeroLeft();
            if (movement == Gui.MOVE.RIGHT) arena.moveHeroRight();

            if (movement == Gui.MOVE.QUIT) gui.close();
            if (movement == Gui.MOVE.EOF) break;

            arena.step();
        }

        gui.draw();
    }
}
