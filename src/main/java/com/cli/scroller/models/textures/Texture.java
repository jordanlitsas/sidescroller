package com.cli.scroller.models.textures;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Texture {
    String id;
    String icon;
    boolean collision;
    int damage;
    private boolean inventoryItem;
    public abstract String print();
    public abstract void addToInventory(Texture texture);
    public abstract Texture copy();

}
