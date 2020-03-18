package com.aor.hero.commands;

import com.aor.hero.arena.Arena;
import com.aor.hero.arena.Position;

public class MoveHeroRightCommand extends Command {
    private final Arena arena;

    public MoveHeroRightCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getHeroPosition().right();
        arena.moveHeroTo(position);
        arena.step();
    }
}
