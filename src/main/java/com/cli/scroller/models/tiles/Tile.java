package com.cli.scroller.models.tiles;

import com.cli.scroller.models.textures.Texture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class Tile {
    private ArrayList<Texture> textures;
    private int row;
    private int col;

    public Texture getTexture () {
        return this.textures.get(0);
    }

    public String getIcon() {
        return this.getTexture().getIcon();
    }

    public void addPlayer(Texture texture) {
        ArrayList<Texture> newTextures = new ArrayList<>();
        newTextures.add(texture);
        newTextures.addAll(textures);
        this.textures = newTextures;
    }

    public Texture getPlayer() throws Exception {
        Optional<Texture> playerOptional = this.textures.stream().filter(texture -> texture.getIcon().equals(TileType.PLAYER.getIcon())).findFirst();
        if (playerOptional.isEmpty()) {
            throw new Exception("Player not found");
        }
        return playerOptional.get();
    }

    public void removePlayer() {
        for (int i = 0; i < textures.size(); i++) {
            if (textures.get(i).getIcon().equals(TileType.PLAYER.getIcon())) {
                textures.remove(i);
            }
        }
    }
}
