package wastedgames.game.Ui.Battle;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Unit;

public class UnitCell extends Group
{
    Image icon_unit;
    Image health;

    public UnitCell(Unit unit,float width,float height)
    {
        this.setSize(width/2,height);
        icon_unit= new Image();
        icon_unit.setSize(getWidth(),getHeight());
       switch (unit.getUnitType())
       {
           case CAVALRY:
               icon_unit.setDrawable(new TextureRegionDrawable(new TextureRegion(ResourceLoader.getImage(wastedgames.game.maintenance.Image.CAVALRY))));
               break;
           case INFANTRY:
               icon_unit.setDrawable(new TextureRegionDrawable(new TextureRegion(ResourceLoader.getImage(wastedgames.game.maintenance.Image.INFATRY))));
               break;
       }
        addActor(icon_unit);

    }
}
