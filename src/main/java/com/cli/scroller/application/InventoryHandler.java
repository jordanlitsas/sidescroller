package com.cli.scroller.application;

import com.cli.scroller.models.textures.InventoryTexture;
import com.cli.scroller.models.textures.LandMineTexture;
import com.cli.scroller.models.textures.PlayerTexture;
import com.cli.scroller.models.textures.Texture;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;

import static com.cli.scroller.application.Engine.statRefresh;
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

    public void useEquipped() {
        PlayerTexture player = findAndGetPlayerTexture();
        if (CollectionUtils.isEmpty(player.getHolding())) {
            return;
        }

        InventoryTexture equipped = (InventoryTexture) player.getHolding().get(0);
        int index = player.getInventory().indexOf(equipped);
        if (equipped instanceof LandMineTexture) {
            useLandmine(player, equipped);
        }
        if (player.getInventory().size() == 0) {
            return;
        }
        InventoryTexture newEquipped = null;
        try {
            newEquipped = (InventoryTexture) player.getInventory().get(index);
        } catch (Exception e) {
            newEquipped = (InventoryTexture) player.getInventory().get(index - 1);
        }
        player.equip(newEquipped);
    }

    private void useLandmine(PlayerTexture player, InventoryTexture equipped) {
        ArrayList<Texture> newInven = new ArrayList<>();
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (!player.getInventory().get(i).getId().equals(equipped.getId())) {
                newInven.add(player.getInventory().get(i));
            }
        }
        ArrayList<Texture> newHolding = new ArrayList<>();
        for (int i = 0; i < player.getHolding().size(); i++) {
            if (player.getHolding().get(i).getId().equals(equipped.getId())) {
                player.getHolding().remove(i);

            }
        }
        player.setInventory(newInven);
        if (CollectionUtils.isNotEmpty(player.getInventory())) {
            newHolding.add(player.getInventory().get(0));

        }
        player.setHolding(newHolding);
        statRefresh();
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
