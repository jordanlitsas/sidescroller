package com.cli.scroller.helper;

import com.cli.scroller.models.tiles.Tile;
import com.cli.scroller.models.textures.Texture;

import java.util.ArrayList;

public class MoveHelper {

    public void playerJump(int[] playerLocation, ArrayList<ArrayList<Tile>> board, Texture player) throws Exception {
        board.get(playerLocation[0] - 1).get(playerLocation[1]).addPlayer(player);
        board.get(playerLocation[0]).get(playerLocation[1]).removePlayer();
        Thread.sleep(50);
    }

    public void playerDrop(int[] playerLocation, ArrayList<ArrayList<Tile>> board, Texture player) throws Exception {
        board.get(playerLocation[0] + 1).get(playerLocation[1]).addPlayer(player);
        board.get(playerLocation[0]).get(playerLocation[1]).removePlayer();
    }

    public void movePlayerRight(int[] playerLocation, ArrayList<ArrayList<Tile>> board, Texture player) throws Exception {
        board.get(playerLocation[0]).get(playerLocation[1] + 1).addPlayer(player);
        board.get(playerLocation[0]).get(playerLocation[1]).removePlayer();
    }

    public void movePlayerLeft(int[] playerLocation, ArrayList<ArrayList<Tile>> board, Texture player) throws Exception {
        board.get(playerLocation[0]).get(playerLocation[1] - 1).addPlayer(player);
        board.get(playerLocation[0]).get(playerLocation[1]).removePlayer();
    }


}
