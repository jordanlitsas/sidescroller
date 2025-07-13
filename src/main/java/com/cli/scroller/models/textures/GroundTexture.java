package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.experimental.SuperBuilder;

import static com.cli.scroller.helper.PrintHelper.*;

@SuperBuilder
public class GroundTexture extends Texture {
    private final TileType tileType = TileType.GROUND;
//    private boolean collision = true;
//    private int damage = 0;

    @Override
    public String print() {
        return BROWN + tileType.getIcon() + RESET;
    }

    @Override
    public void addToInventory(Texture texture) {}

}
