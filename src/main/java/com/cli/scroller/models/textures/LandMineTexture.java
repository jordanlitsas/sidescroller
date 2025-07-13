package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.cli.scroller.helper.PrintHelper.*;

@SuperBuilder
@NoArgsConstructor
public class LandMineTexture extends Texture {
    private final TileType tileType = TileType.LANDMINE;

    @Override
    public String print() {
        return BRIGHT_RED + tileType.getIcon() + RESET;
    }

    @Override
    public void addToInventory(Texture texture) {}

}
