package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.cli.scroller.helper.PrintHelper.GREEN;
import static com.cli.scroller.helper.PrintHelper.RESET;

@SuperBuilder
@NoArgsConstructor
public class TreeTexture extends Texture {
    private final TileType tileType = TileType.TREE;

    @Override
    public String print() {
        return GREEN + tileType.getIcon() + RESET;
    }

    @Override
    public void addToInventory(Texture texture) {}

    @Override
    public Texture copy() {
        return null;
    }
//    private boolean collision = false;
//    private int damage = 0;
}
