package wastedgames.game.Ui.CradProvince.Information;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

import wastedgames.game.map.Formation;
import wastedgames.game.map.Province;
import wastedgames.game.map.Unit;

public class ListUnit extends ScrollPane
{
    private Province province;
    private boolean   getoutUnits=false;
    private Formation formation=new Formation();
    private ArrayList<wastedgames.game.Ui.CradProvince.Information.ButtonUnit> b_units=new ArrayList<>();
    Table scrollTable = new Table();
    public boolean isGetoutUnits() {
        return getoutUnits;
        }

    public void setGetoutUnits(boolean getoutUnits) {
        this.getoutUnits = getoutUnits;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public ArrayList<wastedgames.game.Ui.CradProvince.Information.ButtonUnit> getB_units() {
        return b_units;
    }

    public void setB_units(ArrayList<wastedgames.game.Ui.CradProvince.Information.ButtonUnit> b_units) {
        this.b_units = b_units;
    }

    public ListUnit(Actor widget) {
        super(widget);
    }

    public ListUnit(Actor widget, Skin skin) {
        super(widget, skin);
    }

    public ListUnit(Actor widget, Skin skin, String styleName) {
        super(widget, skin, styleName);
    }

    public ListUnit(Actor widget, ScrollPaneStyle style) {
        super(widget, style);
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Table getScrollTable() {
        return scrollTable;
    }

    public void setScrollTable(Table scrollTable) {
        this.scrollTable = scrollTable;
    }

    public void settingListUnit(Skin skin)
    {
        scrollTable.setSize(getWidth(),getHeight());
        scrollTable.setPosition(getX(),getY());
        scrollTable.debug();
        for (final Unit unit:province.getUnitsInCastle())
        {
            final wastedgames.game.Ui.CradProvince.Information.ButtonUnit W_unit=new ButtonUnit("",skin);
            W_unit.setUnit(unit);
            W_unit.setSize(getWidth()/6,getHeight()/3);
            W_unit.addLabelName(0,scrollTable.getHeight()-50,unit.getName());
            W_unit.addButtonDelete();

            W_unit.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {

                    if (W_unit.isChecked())
                    {
                        formation.getUnits().add(unit);
                        W_unit.setColor(Color.BLUE);
                    }else
                    {
                        formation.getUnits().remove(unit);
                        W_unit.setColor(Color.GRAY);
                    }
                }
            });
            W_unit.getDelete().addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    province.getUnitsInCastle().remove(unit);
                    scrollTable.getCells().removeValue(scrollTable.getCell(W_unit),true);
                    W_unit.remove();

                }
            });
            b_units.add(W_unit);
            scrollTable.add(W_unit).growY().pad(5).width(getWidth()/6);

        }
        setActor(scrollTable);

    }

}
