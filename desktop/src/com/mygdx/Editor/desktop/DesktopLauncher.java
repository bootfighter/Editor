package com.mygdx.Editor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.Editor.Editor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 1280;
		config.height = 720;
		
		config.samples = 16;
		
		config.foregroundFPS = 100;
		config.backgroundFPS = 50;
		config.vSyncEnabled = false;
		
		
		
		new LwjglApplication(new Editor(), config);
	}
}
