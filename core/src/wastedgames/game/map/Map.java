package wastedgames.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import wastedgames.game.chars.Drawable;
import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;

public class Map implements Drawable {
    ArrayList<Province> provinces;

    public Map() {
        provinces = new ArrayList<>();
    }

    public void fillMap() {
        Province province0 = new Province("Paris");
        province0.setAppearance(ResourceLoader.getSprite(Image.PROVINCE_PARIS));
        province0.setPosition(new Vector2(200, 300));
        provinces.add(province0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (Province province : provinces) {
            province.draw(batch);
        }
    }
}
