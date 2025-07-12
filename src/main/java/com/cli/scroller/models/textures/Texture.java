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
    public abstract String print();
}
