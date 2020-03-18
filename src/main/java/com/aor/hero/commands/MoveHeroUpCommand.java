package com.aor.hero.commands;

import com.aor.hero.arena.Arena;
import com.aor.hero.arena.Position;

public class MoveHeroUpCommand extends Command {
    private final Arena arena;

    public MoveHeroUpCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getHeroPosition().up();
        arena.moveHeroTo(position);
        arena.step();
    }
}
