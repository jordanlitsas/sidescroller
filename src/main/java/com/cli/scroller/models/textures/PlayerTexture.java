package com.cli.scroller.models.textures;

import com.cli.scroller.models.tiles.TileType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

import static com.cli.scroller.helper.PrintHelper.*;

@SuperBuilder
@NoArgsConstructor
public class PlayerTexture extends Texture {
    private final TileType tileType = TileType.PLAYER;
    @Getter
    @Setter
    private ArrayList<Texture> inventory = new ArrayList<>();
    @Getter
    @Setter
    private ArrayList<Texture> holding = new ArrayList<>();
    @Override
    public String print() {
        return RED + tileType.getIcon() + RESET;
    }

    @Override
    public void addToInventory(Texture texture) {
        this.inventory.add(texture);
    }

    public void equip(Texture texture) {
        this.holding.add(texture);
    }
}
