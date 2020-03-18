package com.aor.hero.arena.strategies;

import com.aor.hero.arena.Position;

public interface MoveStrategy {
    Position getNextMove(Position position);
}
