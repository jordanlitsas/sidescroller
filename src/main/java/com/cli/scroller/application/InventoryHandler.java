package com.cli.scroller.application;

import com.cli.scroller.models.textures.InventoryTexture;
import com.cli.scroller.models.textures.PlayerTexture;
import com.cli.scroller.models.textures.Texture;
import org.apache.commons.collections4.CollectionUtils;

import static com.cli.scroller.helper.MapHelper.findAndGetPlayerTexture;

public class InventoryHandler {

    public void changeEquipped() {
        PlayerTexture player = findAndGetPlayerTexture();
        if (CollectionUtils.isEmpty(player.getHolding())) {
            return;
        }
        String equippedId = player.getHolding().get(0).getId();
        player.getHolding().remove(0);
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (equippedId.equals(player.getInventory().get(i).getId())) {
                if (i == player.getInventory().size() - 1) {
                    player.equip(player.getInventory().get(0));
                } else {
                    player.equip(player.getInventory().get(i + 1));
                }
            }
        }
    }

    public static void setInventoryItemOrder(PlayerTexture player, Texture item) {
        if (player.getInventory().size() == 0) {
            return;
        }
        int max = 0;
        for (int i = 0; i < player.getInventory().size(); i++) {
            InventoryTexture tempInventoryTexture = ((InventoryTexture) player.getInventory().get(i));
            if ( tempInventoryTexture.getInventoryOrder() > max) {
                max = tempInventoryTexture.getInventoryOrder();
            }

        }
        ((InventoryTexture) item).setInventoryOrder(max + 1);
    }
}
