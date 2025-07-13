package com.cli.scroller.models.textures;

import com.cli.scroller.application.InventoryHandler;
import com.cli.scroller.models.tiles.TileType;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.cli.scroller.helper.PrintHelper.*;

@SuperBuilder
@NoArgsConstructor
public class LandMineTexture extends InventoryTexture {
    private final TileType tileType = TileType.LANDMINE;
    private boolean armed;

    @Override
    public String print() {
        return BRIGHT_RED + tileType.getIcon() + RESET;
    }

    @Override
    public void addToInventory(Texture texture) {}

}
