package com.cli.scroller.application;

import java.io.*;
import java.util.ArrayList;

import com.cli.scroller.helper.MapHelper;
import com.cli.scroller.helper.MoveHelper;
import com.cli.scroller.models.actions.Action;
import com.cli.scroller.models.tiles.Tile;
import com.cli.scroller.models.tiles.TileType;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import static com.cli.scroller.helper.InputHelper.MOVEMENT_MAP;

//@ShellComponent
public class Engine {
    public static ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
    public static ArrayList<Action> queue = new ArrayList<>();
    private final InputHandler inputHandler;
    private PhysicsEngine physicsEngine;
    public Engine(String startingLevel) {
        String path = "maps/" + startingLevel + ".txt";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new RuntimeException("Map file not found: " + path);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                ArrayList<Tile> rowList = new ArrayList<>();

                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    rowList.add(MapHelper.getTileWithTypeAndCoordinates(c, row, col));

                    // Example: Log coordinates and character
                    // System.out.printf("Character '%c' at row %d, column %d%n", c, row, col);
                }

                board.add(rowList);
                row++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        setPlayerInStartingPos();
        this.inputHandler = new InputHandler(new MoveHelper());
        this.physicsEngine = new PhysicsEngine();
    }

    private void setPlayerInStartingPos() {
        board.get(9).get(51).addPlayer(MapHelper.getPlayerTile(9, 51).getTexture());
    }


    public void run() throws Exception {
        KeyListener keyListener = new KeyListener();
        Thread keyListenderThread = new Thread(keyListener);
        keyListenderThread.start();

        final int targetFps = 60;
        final long frameDurationNs = 1_000_000_000 / targetFps; // in nanoseconds
        boolean refresh = true;
        while (true) {
            long frameStart = System.nanoTime();
            if (physicsEngine.gravity()) {
                refreshScreen();
                refresh = false;
            }

            if (refresh) {
                refreshScreen();
            }
            if (!queue.isEmpty()) {
                inputHandler.enactInput();
                queue.remove(0);
                refresh = true;
            } else {
                refresh = false;
            }
//            physicsEngine.gravity();
            long frameTime = System.nanoTime() - frameStart;

            long sleepTime = (frameDurationNs - frameTime) / 1_000_000; // convert to ms

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public static void refreshScreen() {
        clearScreen();
        printScreen();
    }


    public static int[] getPlayerLocation() {
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.get(row).size(); col++) {
                if (board.get(row).get(col).getIcon().equals(TileType.PLAYER.getIcon())) {
                    return new int[] {row, col};
                }
            }
        }
        return new int[] {};
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printScreen() {
        int[] playerLocation = getPlayerLocation();
        for (ArrayList<Tile> row : board) {
            StringBuilder line = new StringBuilder();
            for (int i = playerLocation[1] - 50; i < playerLocation[1] + 50; i++) {
              line.append(row.get(i).getTexture().print());
            }
            System.out.println(line);
        }
    }

    public static void readInput(int input) {
        if (MOVEMENT_MAP.containsKey(input)) {
            Action action = MOVEMENT_MAP.get(input);
            if (action == Action.JUMP) {
                queue.add(Action.JUMP);
                queue.add(Action.JUMP);
                queue.add(Action.JUMP);
                queue.add(Action.JUMP);
                queue.add(Action.JUMP);
            } else {
                if (queue.size() > 0 && (action == Action.MOVE_LEFT || action == Action.MOVE_RIGHT)) {
                    queue.set(0, action);
                } else {
                    queue.add(action);
                }
            }
        }
    }



}
