package com.cli.scroller.application;

import com.cli.scroller.helper.MoveHelper;
import com.cli.scroller.models.actions.Action;
import lombok.RequiredArgsConstructor;

import static com.cli.scroller.application.Engine.*;
import static com.cli.scroller.helper.MapHelper.*;

@RequiredArgsConstructor
public class PhysicsEngine {

    private MoveHelper moveHelper = new MoveHelper();
    public boolean gravity() throws Exception {
        if (!getTileBelowPlayer().getTexture().isCollision() && (movementQueue.isEmpty() || (movementQueue.get(0).equals(Action.JUMP) && movementQueue.get(0).equals(Action.MOVE_LEFT) && movementQueue.get(0).equals(Action.MOVE_RIGHT)))) {
//            int[] playerLocation = getPlayerLocation();
            movementQueue.add(0, Action.DROP);
//            moveHelper.playerDrop(playerLocation, board, board.get(playerLocation[0]).get(playerLocation[1]).getPlayer());
            return true;
        }
        return false;
    }

//    private boolean isJumping() {
//        int count = 0;
//        for (Action action : queue) {
//            if (count == 2) {
//                return true;
//            }
//            if (action == Action.JUMP) {
//                count++;
//            }
//        }
//        return false;
//    }
}
