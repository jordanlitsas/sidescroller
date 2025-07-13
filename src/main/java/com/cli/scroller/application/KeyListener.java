package com.cli.scroller.application;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

import static com.cli.scroller.application.InputHandler.readInput;
import static com.cli.scroller.helper.PrintHelper.print;

public class KeyListener  implements  Runnable{
    private volatile boolean running = true;

    @Override
    public void run() {
//        print("Background thread is running...");
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder()
                    .jna(true)
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
//        print("Background thread has stopped.");
    }

    public void stop() {
        running = false;
    }
}
