package com.cli.scroller.application;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

import static com.cli.scroller.application.Engine.readInput;

public class KeyListener  implements  Runnable{
    private volatile boolean running = true;

    @Override
    public void run() {
        System.out.println("Background thread is running...");
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder()
                    .jna(true)  // enables native mode (required for unbuffered input)
                    .system(true)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (running) {
            try {
                readInput(terminal.reader().read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Background thread has stopped.");
    }

    public void stop() {
        running = false;
    }
}
