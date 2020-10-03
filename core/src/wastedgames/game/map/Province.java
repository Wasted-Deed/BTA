package wastedgames.game.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import wastedgames.game.Tech.Technology;
import wastedgames.game.chars.Drawable;

public class Province implements Drawable, Comparable<Province> {
    private ArrayList<Province> neighbours;
    private Sprite appearance;
    private String name;
    private ArrayList<Unit> unitsforbuy=new ArrayList<>();
    private ArrayList<Unit> units=new ArrayList<>();
    private ArrayList<Unit> queuemake=new ArrayList<>();
    private Player owner;
    private int level;

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Unit> getQueuemake() {
        return queuemake;
    }

    public void setQueuemake(ArrayList<Unit> queuemake) {
        this.queuemake = queuemake;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Unit> getUnitsforbuy() {
        return unitsforbuy;
    }

    public void setUnitsforbuy(ArrayList<Unit> unitsforbuy) {
        this.unitsforbuy = unitsforbuy;
    }

    public Player getOwner() {
        return owner;
    }
    public void addTech(Technology newTech)
    {
       for (Unit unit:newTech.getOpenedUnit())
       {
           unitsforbuy.add(unit);
       }
    }
    public void setOwner(Player owner)
    {
        this.owner = owner;
        owner.getProvinces().add(this);

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void update()
    {
        for(Unit unit:queuemake)
        {
            unit.setCountYears(unit.getCountYears()-1);
            if (unit.getCountYears()<=0)
            {
                units.add(unit);
                queuemake.remove(unit);
            }
        }
    }


    public Province(ArrayList<Province> neighbours, String name, int level, Player owner) {
        this.neighbours = neighbours;
        this.name = name;
        this.level = level;
        this.owner = owner;
    }

    public Sprite getAppearance() {
        return appearance;
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
