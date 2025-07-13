package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class EmptyTexture extends Texture {
    private final TileType tileType = TileType.EMPTY;

    @Override
    public String print() {
        return tileType.getIcon();
    }

    @Override
    public void addToInventory(Texture texture) {}

    @Override
    public Texture copy() {
        return null;
    }
}
