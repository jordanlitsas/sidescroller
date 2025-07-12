package com.cli.scroller.helper;

import com.cli.scroller.models.tiles.Tile;
import com.cli.scroller.models.tiles.TileType;
import com.cli.scroller.models.textures.*;

import java.util.ArrayList;

import static com.cli.scroller.application.Engine.board;

public class MapHelper {

    public static Tile getTileAtCoordinate(int row, int col) {
        return board.get(row).get(col);
    }

    public static Tile getTileWithTypeAndCoordinates(char tile, int row, int col) {
        switch (String.valueOf(tile)) {
            case "+" -> {
                return getPlayerTile(row, col);
            }
            case "|" -> {
                return getTreeTile(row, col);
            }
            case " " -> {
                return getEmptyTile(row, col);
            }
            case "." -> {
                return getGroundTile(row, col);
            }
            case "$" -> {
                return getCoinTile(row, col);
            }
            default -> {
            }
        }
        return null;
    }
    public static Tile getEmptyTile() {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(EmptyTexture.builder()
                .icon(TileType.EMPTY.getIcon())
                .damage(0)
                .collision(false)
                .build());
        return Tile.builder()
                .textures(textures)
                .build();
    }

    public static Tile getGroundTile() {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(GroundTexture.builder()
                .icon(TileType.GROUND.getIcon())
                .damage(0)
                .collision(true)
                .build());
        return Tile.builder()
                .textures(textures)
                .build();
    }

    public static Tile getPlayerTile() {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(PlayerTexture.builder()
                .icon(TileType.PLAYER.getIcon())
                .damage(0)
                .collision(true)
                .build());
        return Tile.builder()
                .textures(textures)
                .build();
    }

    public static Tile getTreeTile() {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(TreeTexture.builder()
                .icon(TileType.TREE.getIcon())
                .damage(0)
                .collision(false) // todo need to figure out multiple items on tile
                .build());
        return Tile.builder()
                .textures(textures)
                .build();
    }

    public static Tile getCoinTile() {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(CoinTexture.builder()
                .icon(TileType.COIN.getIcon())
                .damage(0)
                .collision(false)
                .build());
        return Tile.builder()
                .textures(textures)
                .build();
    }

    public static Tile getEmptyTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(EmptyTexture.builder()
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

    public static Tile getGroundTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(GroundTexture.builder()
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

    public static Tile getPlayerTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(PlayerTexture.builder()
                .icon(TileType.PLAYER.getIcon())
                .damage(0)
                .collision(false)
                .build());
        return Tile.builder()
                .row(row)
                .col(col)
                .textures(textures)
                .build();
    }

    public static Tile getTreeTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(TreeTexture.builder()
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

    public static Tile getCoinTile(int row, int col) {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(CoinTexture.builder()
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
