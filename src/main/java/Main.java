import com.games.snakeandladder.services.PlayerService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Snake and Ladder!");
        PlayerService playerService = new PlayerService();
        playerService.processInput();
    }
}