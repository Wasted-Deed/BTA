package wastedgames.game.Ui.map;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;

import wastedgames.game.maintenance.Image;
import wastedgames.game.map.Formation;
import wastedgames.game.map.Unit;


public class UiProvince extends Group
{
    private ImageButton  appearance;
    private ObjectMap<ImageButton, Formation> units=new ObjectMap<>();
    public UiProvince(Drawable image)
    {
        appearance=new ImageButton(image);

        addActor(appearance);
    }
    public void addFormation(ImageButton b_unit, Formation formation)
    {
        units.put(b_unit,formation);
        addActor(b_unit);
    }
    public void removeFormation(Formation formation)
    {
        //units.remove(formation.getAppearance());
       // removeActor(formation.getAppearance());
    }
    public ImageButton getAppearance() {
        return appearance;
    }

    public void setAppearance(ImageButton appearance) {
        this.appearance = appearance;
    }

    public ObjectMap<ImageButton, Formation> getUnits() {
        return units;
    }

    public void setUnits(ObjectMap<ImageButton, Formation> units) {
        this.units = units;
    }
}
