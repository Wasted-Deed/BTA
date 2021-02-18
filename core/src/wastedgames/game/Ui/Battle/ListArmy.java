package wastedgames.game.Ui.Battle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.awt.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import wastedgames.game.map.Formation;
import wastedgames.game.map.Unit;

public class ListArmy extends ScrollPane
{
    private Table table;
    private ArrayList<Unit> units=new ArrayList<>();

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ListArmy(Formation armyone,Actor widget, float width, float height)
    {
        super(widget);
        units=armyone.getUnits();
        setSize(width,height);
        table = new Table();
        table.setSize(getWidth(),getHeight());
        table.setPosition(getX(),getY());
        float heightCell=getHeight()/3;
        float widthCell=getWidth()/3;
         float positionX=0;
        for (Unit current:units)
        {
            UnitCell cell=new UnitCell(current,widthCell,heightCell);
            cell.setPosition((float)positionX,0);
            table.add(cell).growY().width(widthCell);
            positionX+=widthCell;
            if (positionX>= getWidth())
            {
                positionX=0;
                table.row();
            }
        }
        setActor(table);
    }
}
