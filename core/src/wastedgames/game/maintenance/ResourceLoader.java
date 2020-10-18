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
        resources.put(Image.PROVINCE_PARIS, new Texture("province_paris.png"));
        resources.put(Image.CLOSE, new Texture("Button_icon/X.png"));
        resources.put(Image.GET_OUT, new Texture("Button_icon/getOut.png"));
        resources.put(Image.FORMATION, new Texture("units/icon_formation.png"));
        resources.put(Image.SELECT_FORMATION, new Texture("units/icon_selected_formation.png"));
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
