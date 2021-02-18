package wastedgames.game.map;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;


import wastedgames.game.Ui.map.UiFormation;
import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;

public class Formation implements Cloneable {
    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    private int counter;
   private UiFormation appearance;
    private Province location;

    private ArrayList<Unit>  units= new ArrayList<>();
    private ArrayList<Province> track=new ArrayList<>();
    public Province getLocation() {
        return location;
    }

    public void move()
    {
            if (track.size() != 0) {
                location.removeFormation(this);
                location = track.get(0);
                track.get(0).addFormation(this);
                track.remove(0);
            }
    }
    public void setLocation(Province location)
    {
        this.location = location;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ArrayList<Province> getTrack() {
        return track;
    }

    public void setTrack(ArrayList<Province> track) {
        this.track = track;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }
    @Override
    public Formation clone()
    {
        return new Formation(this.counter,this.track,this.units,this.appearance,this.owner);
    }

    public Formation()
    {
       appearance=new UiFormation();
    }

    public UiFormation getAppearance() {
        return appearance;
    }

    public void setAppearance(UiFormation appearance) {
        this.appearance = appearance;
    }
    public Formation(int counter, ArrayList<Province> track, ArrayList<Unit> units,UiFormation image,Player owner)
    {
        this.counter = counter;
        this.track = track;
        this.units = new ArrayList<>(units);
        this.appearance=image;
        this.owner=owner;
    }

}
