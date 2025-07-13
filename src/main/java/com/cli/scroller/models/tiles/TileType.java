package com.cli.scroller.models.tiles;

import java.util.HashMap;
import java.util.Map;

public enum TileType {
    PLAYER("+"),
    NPC("%"),
    GROUND("."),
    EMPTY(" "),
    TREE("|"),
    COIN("$"),
    LANDMINE("m");

    private final String icon;

    private static final Map<String, TileType> ICON_TO_TYPE_MAP = new HashMap<>();

    static {
        for (TileType type : values()) {
            ICON_TO_TYPE_MAP.put(type.icon, type);
        }
    }

    TileType(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public static TileType fromIcon(String icon) {
        return ICON_TO_TYPE_MAP.get(icon);
    }
}
