package wastedgames.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import wastedgames.game.GameField;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BTA";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new GameField(), config);
	}
}
