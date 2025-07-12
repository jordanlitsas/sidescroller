package com.cli.scroller.models.tiles;

public enum TileType {
    PLAYER ("+"),
    NPC("%"),
    GROUND("."),
    EMPTY(" "),
    TREE("|"),
    COIN("$");
    private final String icon;

    TileType(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
