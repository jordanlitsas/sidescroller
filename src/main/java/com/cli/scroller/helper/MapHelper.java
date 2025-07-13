package com.cli.scroller.helper;

import com.cli.scroller.application.InventoryHandler;
import com.cli.scroller.models.tiles.Tile;
import com.cli.scroller.models.tiles.TileType;
import com.cli.scroller.models.textures.*;

import java.util.ArrayList;
import java.util.UUID;

import static com.cli.scroller.application.Engine.*;
import static com.cli.scroller.helper.PrintHelper.getInventoryItemName;
import static com.cli.scroller.helper.PrintHelper.print;

public class MapHelper {

    public static void playerIsTouchingCoin() throws Exception {
        Tile tile = getPlayerTile();
        for (Texture texture :  tile.getTextures()) {
            if (texture.getIcon().equals(TileType.COIN.getIcon())) {
                removeTextureAtTile(board.get(tile.getRow()).get(tile.getCol()), TileType.COIN);
                points++;
            }
        }
    }

    public static void playerIsTouchingInventoryItem() throws Exception {
        Tile tile = getPlayerTile();
        for (Texture texture :  tile.getTextures()) {
            if (texture.isInventoryItem() && !textureIsPlayer(texture)) {
                PlayerTexture player = findAndGetPlayerTexture();
                InventoryHandler.setInventoryItemOrder(player, texture);
                player.addToInventory(texture);
                if (player.getInventory().size() == 1) {
                    player.equip(texture);
                }
                removeTextureAtTile(tile, TileType.fromIcon(texture.getIcon()));
            }
        }
    }

    private static void removeTextureAtTile(Tile tile, TileType tileType) {
        for (int i = 0; i < tile.getTextures().size(); i++) {
            if (tile.getTextures().get(i).getIcon().equals(tileType.getIcon())) {
                tile.getTextures().remove(i);
                tile.getTextures().add(i, createEmptyTile(tile.getRow(), tile.getCol()).getTexture());

            }
        }
    }

    public static Tile getTileBelowPlayer() {
        int[] playerLocation = getPlayerLocation();
        return  board.get(playerLocation[0] + 1).get(playerLocation[1]);

    }

    public static Tile getTileAtCoordinate(int row, int col) {
        return board.get(row).get(col);
    }


    public static PlayerTexture findAndGetPlayerTexture() {
        int[] playerLocation = getPlayerLocation();
        for (Texture texture :  board.get(playerLocation[0]).get(playerLocation[1]).getTextures()) {
            if (texture.getIcon().equals(TileType.PLAYER.getIcon())) {
                return (PlayerTexture) texture;
            }
        }
        return null;
    }

    public static Tile getPlayerTile() {
        int[] playerLocation = getPlayerLocation();
        return board.get(playerLocation[0]).get(playerLocation[1]);

    }

    public static boolean textureIsPlayer(Texture texture) {
        return texture.getIcon().equals(TileType.PLAYER.getIcon());
    }

    public static int[] getPlayerLocation() {
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.get(row).size(); col++) {
                if (board.get(row).get(col).getIcon().equals(TileType.PLAYER.getIcon())) {
                    return new int[]{row, col};
                }
            }
        }
        print("CANT FIND PLAYER");;
        return new int[]{};
    }





    // Methods to init the board
    public static Tile getTileWithTypeAndCoordinates(char tile, int row, int col) {
        switch (String.valueOf(tile)) {
            case "+" -> {
                return createPlayerTile(row, col);
            }
            case "|" -> {
                return createTreeTile(row, col);
            }
            case " " -> {
                return createEmptyTile(row, col);
            }
            case "." -> {
                return createGroundTile(row, col);
            }
            case "$" -> {
                return createCoinTile(row, col);
            }
            case "M" -> {
                return createLandmineTile(row, col);
            }
            default -> {
            }
        }
        return null;
    }

    public static Tile createLandmineTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(LandMineTexture.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .icon(TileType.LANDMINE.getIcon())
                .damage(0)
                .collision(false)
                .inventoryItem(true)
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }


    public static Tile createEmptyTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(EmptyTexture.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .icon(TileType.EMPTY.getIcon())
                .damage(0)
                .collision(false)
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }

    public static Tile createGroundTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(GroundTexture.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .icon(TileType.GROUND.getIcon())
                .damage(0)
                .collision(true)
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }

    public static Tile createPlayerTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(PlayerTexture.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .icon(TileType.PLAYER.getIcon())
                .damage(0)
                .collision(false)
                .inventory(new ArrayList<>())
                .holding(new ArrayList<>())
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }

    public static Tile createTreeTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(TreeTexture.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .icon(TileType.TREE.getIcon())
                .damage(0)
                .collision(true)
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }

    public static Tile createCoinTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(CoinTexture.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .icon(TileType.COIN.getIcon())
                .damage(0)
                .collision(false)
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }


}