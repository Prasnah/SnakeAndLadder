package com.games.snakeandladder.services;

import com.games.snakeandladder.GameBoard;
import com.games.snakeandladder.interfaces.GameServiceI;
import com.games.snakeandladder.model.Dice;
import com.games.snakeandladder.model.Ladder;
import com.games.snakeandladder.model.Players;
import com.games.snakeandladder.model.Snake;

import java.util.List;

public class GameService implements GameServiceI {
    @Override
    public void startGame(GameBoard gameBoard) {
        GameBoard.startPoint = 0;
        GameBoard.endPoint = 100;
        int updatedPosition = 0;
        int playerNewPosition = 0;
        while (playerNewPosition < 100) {
            for (int player = 0; player < gameBoard.getAllPlayers().size(); player++) {
                int diceValue = Dice.getDiceValue();
                playerNewPosition = 0;
                playerNewPosition = diceValue + gameBoard.getAllPlayers().get(player).getPlayerPosition();
                while (checkSnakeBit(playerNewPosition, gameBoard.getListOfSnakes()) || checkLadderHike(playerNewPosition, gameBoard.getListOfLadders())) {
                    updatedPosition = checkAndUpdateSnakeBit(playerNewPosition, gameBoard.getListOfSnakes());
                    updatedPosition = checkAndUpdateLadderHike(playerNewPosition, gameBoard.getListOfLadders());
                    playerNewPosition = updatedPosition;
                }

                if (checkPlayerWinMoment(playerNewPosition, GameBoard.endPoint)) {
                    System.out.println("Player " + gameBoard.getAllPlayers().get(player).getName() + " won the game");
                    break;
                }
                if (playerNewPosition <= 100) {
                    gameBoard.getAllPlayers().get(player).setPlayerPosition(playerNewPosition);
                }
                if (playerNewPosition > 100) {
                    playerNewPosition = gameBoard.getAllPlayers().get(player).getPlayerPosition();
                }
                System.out.println(gameBoard.getAllPlayers().get(player).getName() + " rolled dice " + diceValue +  " and moved to " + playerNewPosition);
                playerNewPosition = gameBoard.getAllPlayers().stream().map(Players::getPlayerPosition).max(Integer::compare).get();
            }
        }
    }

    private boolean checkPlayerWinMoment(int playerNewPosition, int endPoint) {
        return playerNewPosition == endPoint;
    }

    private int checkAndUpdateSnakeBit(int playerNewPosition, List<Snake> listOfSnakes) {
        List<Snake> bitedSnake = listOfSnakes.stream().filter(snake -> snake.getStartingPoint() == playerNewPosition).toList();
        if (!bitedSnake.isEmpty()) return bitedSnake.get(0).getEndingPoint();
        return playerNewPosition;
    }

    private int checkAndUpdateLadderHike(int playerNewPosition, List<Ladder> listOfLadders) {
        List<Ladder> hikeLadder = listOfLadders.stream().filter(ladder -> ladder.getStartingPoint() == playerNewPosition).toList();
        if (!hikeLadder.isEmpty()) return hikeLadder.get(0).getEndingPoint();
        return playerNewPosition;
    }

    private boolean checkSnakeBit(int playerPosition, List<Snake> listOfSnakes) {
        List<Snake> bitedSnake = listOfSnakes.stream().filter(snake -> snake.getStartingPoint() == playerPosition).toList();
        return !bitedSnake.isEmpty();
    }

    private boolean checkLadderHike(int playerPosition, List<Ladder> listOfLadders) {
        List<Ladder> hikeLadder = listOfLadders.stream().filter(ladder -> ladder.getStartingPoint() == playerPosition).toList();
        return !hikeLadder.isEmpty();
    }

}
