package com.games.snakeandladder;

import com.games.snakeandladder.model.Ladder;
import com.games.snakeandladder.model.Players;
import com.games.snakeandladder.model.Snake;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GameBoard {
    public static int startPoint;
    public static int endPoint;
    List<Snake> listOfSnakes;
    List<Ladder> listOfLadders;
    List<Players> allPlayers;

    public void addSnakes(Snake snake) {
        this.listOfSnakes.add(snake);
    }

    public void removeSnakes(Snake snake) {
        this.listOfSnakes.remove(snake);
    }

    public void addLadders(Ladder ladder) {
        this.listOfLadders.add(ladder);
    }

    public void removeLadders(Ladder ladder) {
        this.listOfLadders.remove(ladder);
    }
}
