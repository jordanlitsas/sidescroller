package com.cli.scroller.application;

import com.cli.scroller.helper.MoveHelper;
import com.cli.scroller.models.actions.Action;
import com.cli.scroller.models.tiles.Tile;
import com.cli.scroller.models.textures.Texture;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import static com.cli.scroller.application.Engine.*;
import static com.cli.scroller.helper.InputHelper.MOVEMENT_MAP;
import static com.cli.scroller.helper.MapHelper.getTileAtCoordinate;

//@RequiredArgsConstructor
@RequiredArgsConstructor
public class InputHandler {

    private final MoveHelper moveHelper;

    public void enactInput() {
        int[] playerLocation = getPlayerLocation();
        if (playerLocation.length == 0) {
            System.out.println("CANT FIND PLAYER");
            return;
        }
        if (MOVEMENT_MAP.containsValue(queue.get(0))) {
            movePlayer(queue.get(0), playerLocation);
        }
    }

    private void movePlayer(Action action, int[] playerLocation) {
        try {
            Texture player = board.get(playerLocation[0]).get(playerLocation[1]).getPlayer();
            if (checkCollision(action, playerLocation, player)) {
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

    private boolean checkCollision(Action action, int[] playerLocation, Texture player) {
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


    public void handleInput(Action action) {
        if (action == Action.JUMP) {
            queue.add(Action.JUMP);
            queue.add(Action.JUMP);
            queue.add(Action.JUMP);
            queue.add(Action.DROP);
            queue.add(Action.DROP);
            queue.add(Action.DROP);
        } else {
            queue.add(action);
        }
    }

    public void detectInAirLateralInput(Action action) {
        if(action == Action.MOVE_LEFT || action == Action.MOVE_RIGHT) {
            queue.add(0, action);
        }

    }
}
