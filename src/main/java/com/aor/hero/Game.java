package com.aor.hero;

import com.aor.hero.arena.*;
import com.aor.hero.commands.Command;
import com.aor.hero.gui.Gui;
import com.aor.hero.creator.ArenaCreator;

import java.io.IOException;

public class Game implements ArenaObserver {
    private Arena arena;
    private Gui gui;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        ArenaCreator creator = new ArenaCreator();
        arena = creator.createArena(50, 25, 10, 20, 50);
        arena.addObserver(this);

        gui = new Gui(arena);
        gui.draw();

        while (!arena.isFinished()) {
            Command command = gui.getNextCommand();
            command.execute();
        }
    }

    @Override
    public void arenaChanged() {
        try {
            gui.draw();
        } catch (IOException e) {
            // Nothing to do if drawing fails
        }
    }
}
