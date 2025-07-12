package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.cli.scroller.helper.PrintHelper.*;

@SuperBuilder
@NoArgsConstructor
public class PlayerTexture extends Texture {
    private final TileType tileType = TileType.PLAYER;
//    private boolean collision = true;
//    private int damage = 0;
    @Override
    public String print() {
        return RED + tileType.getIcon() + RESET;
    }
}
