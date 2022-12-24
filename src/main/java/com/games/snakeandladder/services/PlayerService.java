package com.games.snakeandladder.services;

import com.games.snakeandladder.GameBoard;
import com.games.snakeandladder.exception.InputMisMatchException;
import com.games.snakeandladder.exception.WrongInputException;
import com.games.snakeandladder.interfaces.PlayServiceI;
import com.games.snakeandladder.model.Ladder;
import com.games.snakeandladder.model.Players;
import com.games.snakeandladder.model.Snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerService implements PlayServiceI {

    Scanner scanner = new Scanner(System.in);
    BufferedReader in = new BufferedReader(
            new InputStreamReader(
                    System.in));
    List<Snake> snakesList = new ArrayList<>();
    List<Ladder> laddersList = new ArrayList<>();
    List<Players> playersList = new ArrayList<>();


    public void processInput() throws IOException {
        System.out.println("Enter number of snakes : ");
        int numberOfSnakes = scanner.nextInt();
        String snakeLength;
        while (numberOfSnakes > 0) {
            snakeLength = in.readLine();
            Snake snake = new Snake();
            snake.setStartingPoint(derivedFromStringInput(snakeLength).get(0));
            snake.setEndingPoint((derivedFromStringInput(snakeLength).get(1)));
            snakesList.add(snake);
            numberOfSnakes--;
        }

        if (numberOfSnakes != 0) {
            throw new InputMismatchException("Number of snakes are lesser or greater than actual");
        }
        if (snakesList.stream().anyMatch(snake -> snake.getStartingPoint() <= snake.getEndingPoint())) {
            throw new WrongInputException("Snake start point is smaller than end point");
        }
        System.out.println("Enter number of ladders : ");
        int numberOfLadders = scanner.nextInt();

        String ladderLength;
        while ( numberOfLadders > 0) {
            ladderLength = in.readLine();
            Ladder ladder = new Ladder();
            ladder.setStartingPoint(derivedFromStringInput(ladderLength).get(0));
            ladder.setEndingPoint((derivedFromStringInput(ladderLength).get(1)));
            laddersList.add(ladder);
            numberOfLadders--;
        }
        if (numberOfLadders != 0) {
            throw new InputMisMatchException("Number of snakes are lesser or greater than actual");
        }
        if (laddersList.stream().anyMatch(ladder -> ladder.getStartingPoint() >= ladder.getEndingPoint())) {
            throw new WrongInputException("Ladder start point is greater than end point");
        }
        System.out.println("Enter name of Players : ");
        int numberOfPlayers = scanner.nextInt();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter the name of player " + (i + 1) + " : ");
            String playerName = scanner.next();
            Players players = new Players(playerName, 0);
            playersList.add(players);
        }

        GameBoard gameBoard = new GameBoard();
        gameBoard.setListOfSnakes(snakesList);
        gameBoard.setListOfLadders(laddersList);
        gameBoard.setAllPlayers(playersList);
        new GameService().startGame(gameBoard);

    }

    private List<Integer> derivedFromStringInput(String stringInput) {
        String[] splitedInput;
        List<Integer> intInput = new ArrayList<>();
        splitedInput = stringInput.split(" ");
        intInput.add(Integer.parseInt(splitedInput[0]));
        intInput.add(Integer.parseInt(splitedInput[1]));
        return intInput;
    }
}
