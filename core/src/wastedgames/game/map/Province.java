package wastedgames.game.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import wastedgames.game.chars.Drawable;

public class Province implements Drawable, Comparable<Province> {
    private ArrayList<Province> neighbours;
    private Sprite appearance;
    private String name;
    private int level;
    private Player owner;


    public Province(ArrayList<Province> neighbours, String name, int level, Player owner) {
        this.neighbours = neighbours;
        this.name = name;
        this.level = level;
        this.owner = owner;
    }

    public Province(ArrayList<Province> neighbours, String name) {
        this(neighbours, name, 1, null);
    }

    public Province(ArrayList<Province> neighbours, String name, Player owner) {
        this(neighbours, name, 1, owner);
    }

    public Province(String name) {
        this(null, name, 1, null);
    }

    public void setAppearance(Sprite appearance) {
        this.appearance = appearance;
    }

    public void setPosition(Vector2 position) {
        appearance.setCenter(position.x, position.y);
    }

    public ArrayList<Province> getNeighbours() {return neighbours;}

    public void setNeighbours(ArrayList<Province> neighbours) {this.neighbours = neighbours;}

    public String getName() {return this.name;}

    @Override
    public void draw(SpriteBatch batch) {
        appearance.draw(batch);
    }

    @Override
    public int compareTo(Province province) {
        return level;
    }
}
