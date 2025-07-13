package com.cli.scroller.models.textures;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Texture {
    String icon;
    boolean collision;
    int damage;
    boolean inventoryItem;
    public abstract String print();
    public abstract void addToInventory(Texture texture);
}
