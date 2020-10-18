package wastedgames.game.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.ArrayList;

import wastedgames.game.Tech.Technology;
import wastedgames.game.Ui.map.UiProvince;

public class Province  implements  Comparable<Province> {
    private ArrayList<Province> neighbours=new ArrayList<>();
    private UiProvince appearance;
    private String name;
    private ArrayList<Unit> unitsforbuy=new ArrayList<>();
    private ArrayList<Unit> unitsInCastle =new ArrayList<>();
    private ArrayList<Formation>  formations=new ArrayList<>();
    private Unit queuemake=null;
    private Player owner;
    private int level;

    public Province(Drawable image)
    {
        appearance=new UiProvince(image);

    }

    public ArrayList<Formation> getFormations() {
        return formations;
    }
    public  void addFormation(Formation new_formation)
    {
        new_formation.setLocation(this);
        formations.add(new_formation);
        new_formation.getAppearance().setPosition(20,20);
        new_formation.getAppearance().setSize(50,50);
        appearance.addFormation(new_formation.getAppearance(),new_formation);
    }
    public  boolean removeFormation(Formation formation)
    {
        formations.remove(formation);
        appearance.removeFormation(formation);
        return  false;
    }
    public void setFormations(ArrayList<Formation> formations) {
        this.formations = formations;
    }


    public ArrayList<Unit> getUnitsInCastle() {
        return unitsInCastle;
    }

    public void setUnitsInCastle(ArrayList<Unit> unitsInCastle) {
        this.unitsInCastle = unitsInCastle;
    }

    public Unit getQueuemake() {
        return queuemake;
    }

    public void setQueuemake(Unit queuemake) {
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
            if (queuemake!=null)
            {

                queuemake.setCountYears(queuemake.getCountYears() - 1);
                if (queuemake.getCountYears() <= 0)
                {

                    unitsInCastle.add(queuemake);
                    queuemake = null;
                }
            }
    }



    public void setPosition(Vector2 position) {
        //appearance.setCenter(position.x, position.y);
        appearance.setPosition(position.x, position.y);
        appearance.setSize(100,100);
        appearance.getAppearance().getImageCell().size(100,100);
        appearance.getAppearance().setSize(100,100);

        appearance.getAppearance().debug();
    }


    public UiProvince getAppearance() {
        return appearance;
    }

    public void setAppearance(UiProvince appearance) {
        this.appearance = appearance;
    }

    public ArrayList<Province> getNeighbours() {return neighbours;}

    public void setNeighbours(ArrayList<Province> neighbours) {this.neighbours = neighbours;}

    public String getName() {return this.name;}

   /*@Override
    public void draw(SpriteBatch batch) {
        appearance.draw(batch);
    }*/

    @Override
    public int compareTo(Province province) {
        return level;
    }
}
