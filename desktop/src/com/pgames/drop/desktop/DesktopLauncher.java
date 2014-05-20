package com.pgames.drop.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pgames.drop.DropGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title= "Drop Game";
        config.width=800;
        config.height=480;
		new LwjglApplication(new DropGame(), config);
	}
}
