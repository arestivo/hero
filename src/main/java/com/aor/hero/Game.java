package com.aor.hero;

import com.aor.hero.arena.*;
import com.aor.hero.commands.Command;
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

            Command command = gui.getNextCommand();
            command.execute();
        }

        gui.draw();
    }
}
