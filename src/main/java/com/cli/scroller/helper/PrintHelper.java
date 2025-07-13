package com.cli.scroller.helper;

import com.cli.scroller.models.textures.Texture;

public class PrintHelper {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BROWN = "\u001B[0;33m";
    public static final String BRIGHT_RED = "\u001B[91m";


    public static void print(String output) {
        System.out.println(output);
    }
    public static void print(String colour, String output) {
        System.out.println(colour + output + RESET);
    }


    public static void printnl(String colour, String output) {
        System.out.println("\n" + colour + output + RESET);
    }

    public static String getInventoryItemName(Texture texture) {
        return switch (texture.getClass().getSimpleName()) {
            case "LandMineTexture" -> "Landmine";
            default -> "unknown";
        };
    }

}
