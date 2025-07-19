package com.cli.scroller.application;

import com.cli.scroller.helper.MoveHelper;
import com.cli.scroller.models.actions.Action;
import com.cli.scroller.models.tiles.Tile;
import com.cli.scroller.models.textures.Texture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import static com.cli.scroller.application.Engine.*;
import static com.cli.scroller.helper.InputHelper.INTERACT_MAP;
import static com.cli.scroller.helper.InputHelper.MOVEMENT_MAP;
import static com.cli.scroller.helper.MapHelper.*;
import static com.cli.scroller.helper.MapHelper.findAndGetPlayerTexture;

//@RequiredArgsConstructor
@RequiredArgsConstructor
public class InputHandler {

    private final MoveHelper moveHelper;
    private final InventoryHandler inventoryHandler;

    public void handleMovement() {
        if (MOVEMENT_MAP.containsValue(movementQueue.get(0))) {
            movePlayer(movementQueue.get(0));
            movementQueue.remove(0);
        }
    }

    public void handleInteraction() {
        if (INTERACT_MAP.containsValue(interactQueue.get(0))) {
            doInteraction(interactQueue.get(0));
            interactQueue.remove(0);
        }
    }

    private void doInteraction(Action action) {
        if (Objects.requireNonNull(action) == Action.CHANGE_EQUIPPED) {
            inventoryHandler.changeEquipped();
        }
        if (Objects.requireNonNull(action) == Action.USE_EQUIPPED) {
            inventoryHandler.useEquipped();
        }
//        if (Objects.requireNonNull(action) == Action.DUMP) {
//            Gson gson = new GsonBuilder()
//                    .setPrettyPrinting()
//                    .create();
//
//            String json = gson.toJson(findAndGetPlayerTexture().getInventory());
//            System.out.println("\n\n");
//            System.out.println(json);
//        }
    }

    private void movePlayer(Action action) {
        int[] playerLocation = getPlayerLocation();
        try {
            Texture player = board.get(playerLocation[0]).get(playerLocation[1]).getPlayer();
            if (checkCollision(action, playerLocation)) {
                return;
            }
            if (Action.JUMP.equals(action)) {
                moveHelper.playerJump(playerLocation, board, player);
            }
            if (Action.MOVE_RIGHT.equals(action)) {
                moveHelper.movePlayerRight(playerLocation, board, player);
            }
            if (Action.DROP.equals(action)) {
                moveHelper.playerDrop(playerLocation, board, player);
            }
            if (Action.MOVE_LEFT.equals(action)) {
                moveHelper.movePlayerLeft(playerLocation, board, player);
            }
        } catch (Exception e) {
//            move player back to start location

        }
    }

    private boolean checkCollision(Action action, int[] playerLocation) {
        switch(action) {
            case MOVE_RIGHT -> {
                Tile adjacentTile = getTileAtCoordinate(playerLocation[0], playerLocation[1] + 1);
                return adjacentTile.getTexture().isCollision();

            }
            case MOVE_LEFT -> {
                Tile adjacentTile = getTileAtCoordinate(playerLocation[0], playerLocation[1] - 1);
                return adjacentTile.getTexture().isCollision();
            }
            case JUMP -> {
                Tile adjacentTile = getTileAtCoordinate(playerLocation[0] - 1, playerLocation[1]);
                return adjacentTile.getTexture().isCollision();
            }
            case DROP -> {
                Tile adjacentTile = getTileAtCoordinate(playerLocation[0] + 1, playerLocation[1]);
                return adjacentTile.getTexture().isCollision();
            }
        }
        return false;
    }


    public static void readInput(int input) {
        if (MOVEMENT_MAP.containsKey(input)) {
            Action action = MOVEMENT_MAP.get(input);
            if (action == Action.JUMP) {
                movementQueue.add(Action.JUMP);
                movementQueue.add(Action.JUMP);
                movementQueue.add(Action.JUMP);
//                movementQueue.add(Action.JUMP);
//                movementQueue.add(Action.JUMP);
            } else if (movementQueue.size() > 0 && (action == Action.MOVE_LEFT || action == Action.MOVE_RIGHT)) {
                movementQueue.add(0, action);
                movementQueue.add(0, action);
                movementQueue.add(0, action);

            } else {
                movementQueue.add(action);
            }
        }
        if (INTERACT_MAP.containsKey(input)) {
            Action action = INTERACT_MAP.get(input);
            interactQueue.add(action);
        }
        String s = "input read";
    }

}
