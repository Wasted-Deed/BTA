package wastedgames.game.map;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;

import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;

public class Formation implements Cloneable {
    // number of units
    private int counter;
    private ImageButton appearance;
    private Province location;

    private ArrayList<Unit>  units= new ArrayList<>();
    private ArrayList<Province> track=new ArrayList<>();
    public Province getLocation() {
        return location;
    }



    public void move()
    {
        if (track.size()!=0) {
            location.removeFormation(this);
            location = track.get(0);
            track.get(0).addFormation(this);
            track.remove(0);
        }
    }
    public void setLocation(Province location) {
        this.location = location;
    }


    public void setAppearance(ImageButton appearance) {
        this.appearance = appearance;
    }

    // the current way


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
        return new Formation(this.counter,this.track,this.units,this.appearance);
    }

    public ImageButton getAppearance() {
        return appearance;
    }

    public Formation()
    {
        appearance=new ImageButton(new SpriteDrawable(ResourceLoader.getSprite(Image.FORMATION)),new SpriteDrawable(ResourceLoader.getSprite(Image.FORMATION)),new SpriteDrawable(ResourceLoader.getSprite(Image.SELECT_FORMATION)));
    }

    public Formation(int counter, ArrayList<Province> track, ArrayList<Unit> units,ImageButton image)
    {
        this.counter = counter;
        this.track = track;
        this.units = units;
        this.appearance=image;
    }
}
