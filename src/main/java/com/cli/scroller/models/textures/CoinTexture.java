package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.experimental.SuperBuilder;

import static com.cli.scroller.helper.PrintHelper.*;

@SuperBuilder
public class CoinTexture extends Texture {
    private final TileType tileType = TileType.COIN;
//    private boolean collision = true;
//    private int damage = 0;

    @Override
    public String print() {
        return YELLOW + tileType.getIcon() + RESET;
    }

    @Override
    public void addToInventory(Texture texture) {}
}

