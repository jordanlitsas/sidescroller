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

    @Override
    public Texture copy() {
        return InventoryTexture.builder()
                .id(this.id)
                .icon(this.icon)
                .collision(this.collision)
                .damage(this.damage)
                .inventoryItem(this.isInventoryItem())
                .inventoryOrder(this.inventoryOrder)
                .build();
    }


}
