package com.aor.hero.commands;

import com.aor.hero.arena.Arena;
import com.aor.hero.arena.Position;

public class MoveHeroDownCommand extends Command {
    private final Arena arena;

    public MoveHeroDownCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getHeroPosition().down();
        arena.moveHeroTo(position);
        arena.step();
    }
}
