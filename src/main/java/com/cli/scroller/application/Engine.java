package com.cli.scroller.application;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.cli.scroller.helper.MapHelper;
import com.cli.scroller.helper.MoveHelper;
import com.cli.scroller.helper.PrintHelper;
import com.cli.scroller.models.actions.Action;
import com.cli.scroller.models.textures.InventoryTexture;
import com.cli.scroller.models.textures.PlayerTexture;
import com.cli.scroller.models.textures.Texture;
import com.cli.scroller.models.tiles.Tile;
import org.apache.commons.collections4.CollectionUtils;

import static com.cli.scroller.helper.MapHelper.*;
import static com.cli.scroller.helper.PrintHelper.RESET;
import static com.cli.scroller.helper.PrintHelper.YELLOW;

//@ShellComponent
public class Engine {
    public static ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
    public static ArrayList<Action> movementQueue = new ArrayList<>();
    public static ArrayList<Action> interactQueue = new ArrayList<>();
    private final InputHandler inputHandler;
    private final PhysicsEngine physicsEngine;
    public static boolean refresh = true;
    public static int points = 0;
    private static int printCount = 0;

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
                    if (c == 'M') {
                        String s = "";
                    }
                    rowList.add(MapHelper.getTileWithTypeAndCoordinates(c, row, col));
                }

                board.add(rowList);
                row++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        setPlayerInStartingPos();
        this.inputHandler = new InputHandler(new MoveHelper(), new InventoryHandler());
        this.physicsEngine = new PhysicsEngine();
    }

    private void setPlayerInStartingPos() {
        board.get(9).get(51).addPlayer(MapHelper.createPlayerTile(9, 51).getTexture());
    }


    public void run() throws Exception {
        Thread keyListenerThread = new Thread(new KeyListener());
        keyListenerThread.start();
        Thread actionListenerThread = new Thread(new ActionListener());
        actionListenerThread.start();
        System.out.print("\033[2J"); // Clear full screen once
        final int targetFps = 60;
        final long frameDurationNs = 1_000_000_000 / targetFps;
        refreshScreen();
        while (true) {
            long frameStart = System.nanoTime();
            if (refresh) {
                refreshScreen();
                refresh = false;
            }
            sleepRemainingFrameTime(frameStart);
        }
    }

    private void handleMapInteractions() throws Exception {
        playerIsTouchingInventoryItem();
        playerIsTouchingCoin();
    }

    public static void refreshScreen() {
        printScreen();

    }



    private static void printScreen() {
        printCount++;
        StringBuilder screenBuffer = new StringBuilder();
        int[] playerLocation = getPlayerLocation();

        for (ArrayList<Tile> row : board) {
            for (int i = playerLocation[1] - 50; i < playerLocation[1] + 50; i++) {
                screenBuffer.append(row.get(i).getTexture().print());
            }
            screenBuffer.append("\n");
        }

        screenBuffer.append("Points: ").append(points).append("\n");

        StringBuilder inventory = new StringBuilder();
        PlayerTexture player = findAndGetPlayerTexture();
        if (!CollectionUtils.isEmpty(player.getInventory())) {
            // Sort inventory items by inventoryOrder
            List<Texture> sortedInventory = new ArrayList<>(player.getInventory());
            sortedInventory.sort(Comparator.comparingInt(item -> {
                if (item instanceof InventoryTexture) {
                    return ((InventoryTexture) item).getInventoryOrder();
                }
                return Integer.MAX_VALUE; // Fallback for non-inventory items
            }));

            for (Texture item : player.getInventory()) {
                if (CollectionUtils.isNotEmpty(player.getHolding())) {
                    if (player.getHolding().get(0).getId().equals(item.getId())) {
                        inventory.append(YELLOW).append(PrintHelper.getInventoryItemName(item))
                                .append(RESET)
                                .append(" ");
                    } else {
                        inventory.append(PrintHelper.getInventoryItemName(item)).append(" ");
                    }
                }

            }
        }

        screenBuffer.append("Inventory: ").append(inventory).append("\n");
        screenBuffer.append("Print no. : ").append(printCount).append("\n");
        System.out.print("\033[H"); // Move cursor to top-left
        System.out.print(screenBuffer);
    }


    private void sleepRemainingFrameTime(long frameStart) {
        long elapsedTime = System.nanoTime() - frameStart;
        long sleepTimeMs = ((long) 16666666 - elapsedTime) / 1_000_000;

        if (sleepTimeMs > 0) {
            try {
                Thread.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void statRefresh() {
        System.out.print("\033[H\033[2J");
    }



}