package wastedgames.game.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import wastedgames.game.chars.Drawable;

public class Province implements Drawable {
    private ArrayList<Province> neighbours;
    private Sprite appearance;
    private String name;
    private int level;

    public Province(ArrayList<Province> neighbours, String name, int level) {
        this.neighbours = neighbours;
        this.name = name;
        this.level = level;
    }

    public Province(ArrayList<Province> neighbours, String name) {
        this(neighbours, name, 1);
    }

    public Province(String name) {
        this(null, name, 1);
    }

    public void setAppearance(Sprite appearance) {
        this.appearance = appearance;
    }

    public void setPosition(Vector2 position) {
        appearance.setCenter(position.x, position.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        appearance.draw(batch);
    }
}
