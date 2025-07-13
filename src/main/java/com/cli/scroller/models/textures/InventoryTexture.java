package com.cli.scroller.models.textures;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class InventoryTexture extends Texture {
    private int inventoryOrder;
    @Override
    public String print() {
        return null;
    }

    @Override
    public void addToInventory(Texture texture) {

    }
}
