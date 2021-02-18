package wastedgames.game.maintenance;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private static HashMap<Image, Texture> resources = new HashMap<>();
    private static final int SCALE = 2;

    public static Sprite getSprite(Image image) {
        Sprite sprite = new Sprite(resources.get(image));
        sprite.scale(SCALE);
        return sprite;
    }

    public static void loadResources() {
        resources.put(Image.PROVINCE0, new Texture("province0.png"));
        resources.put(Image.PROVINCE1, new Texture("province1.png"));
        resources.put(Image.CLOSE, new Texture("Button_icon/X.png"));
        resources.put(Image.GET_OUT, new Texture("Button_icon/getOut.png"));
        resources.put(Image.FORMATION, new Texture("units/icon_formation.png"));
        resources.put(Image.SELECT_FORMATION, new Texture("units/icon_selected_formation.png"));
        resources.put(Image.INFATRY, new Texture("units/infantry.png"));
        resources.put(Image.CAVALRY, new Texture("units/cavalry.png"));
        resources.put(Image.BattleBackground, new Texture("background.jpg"));
    }

    public static Texture getImage(Image image) {
        return resources.get(image);
    }

    public static void dispose() {
        for (Map.Entry<Image, Texture> entry : resources.entrySet()) {
            entry.getValue().dispose();
        }
    }
}
