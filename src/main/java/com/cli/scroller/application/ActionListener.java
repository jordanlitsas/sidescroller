package com.cli.scroller.application;

import com.cli.scroller.helper.MapHelper;
import com.cli.scroller.helper.MoveHelper;
import com.cli.scroller.models.actions.Action;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

import static com.cli.scroller.application.Engine.*;
import static com.cli.scroller.application.InputHandler.readInput;
import static com.cli.scroller.helper.PrintHelper.print;

public class ActionListener  implements  Runnable {

    private final InputHandler inputHandler;
    private final PhysicsEngine physicsEngine;

    private final MapHelper mapHelper = new MapHelper();
    private volatile boolean running = true;

    public ActionListener() {
        this.inputHandler = new InputHandler(new MoveHelper(), new InventoryHandler());
        this.physicsEngine = new PhysicsEngine();
    }

    @Override
    public void run() {

        while (running) {
            try {
                mapHelper.handleMapInteractions();
//                if () {
//                    refreshScreen();
//                }


                if (!movementQueue.isEmpty()) {
                    inputHandler.handleMovement();
                    refresh=true;
//                    movementQueue.remove(0);
//                    refreshScreen();
                }

                if (!interactQueue.isEmpty()) {
                    inputHandler.handleInteraction();
                    refresh=true;
//                    interactQueue.remove(0);
//                    refreshScreen();
                }
                if (physicsEngine.gravity()) {
                    refresh=true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        running = false;
    }
}
