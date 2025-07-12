package com.cli.scroller;

import java.io.IOException;

import com.cli.scroller.application.Engine;

public class ScrollerApplication {
	public static void main(String[] args) throws Exception {
		new Engine("level1").run();
	}
}
