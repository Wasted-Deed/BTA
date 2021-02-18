package wastedgames.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.ArrayList;

import wastedgames.game.Tech.Technology;
import wastedgames.game.Ui.Battle.WinBattle;
import wastedgames.game.Ui.map.AppearanceProvince;
import wastedgames.game.Ui.map.UiProvince;
import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;

public class Province  implements  Comparable<Province> {
    private ArrayList<Province> neighbours=new ArrayList<>();
    private AppearanceProvince appearance;
    private String name;
    private ArrayList<Unit> unitsforbuy=new ArrayList<>();
    private ArrayList<Unit> unitsInCastle =new ArrayList<>();
    private ArrayList<Formation>  formationsOwner=new ArrayList<>();
    private ArrayList<Formation>  formationsEnemy=new ArrayList<>();
    private Unit queuemake=null;
    private Player owner;
    private int level;
     public Province(float[] vertices,String name)
     {
         Texture texture = null;
         if (name.equals("province0"))
             texture= ResourceLoader.getImage(Image.PROVINCE0);
         if (name.equals("province1"))
             texture= ResourceLoader.getImage(Image.PROVINCE1);
         if (name.equals("province2"))
             texture= ResourceLoader.getImage(Image.PROVINCE0);
         appearance=new AppearanceProvince(vertices,texture);
         this.name=name;
     }



    public  void addFormation(Formation new_formation)
    {
        new_formation.setLocation(this);
        if (new_formation.getOwner()==getOwner()) {
            formationsOwner.add(new_formation);
            new_formation.getAppearance().setPosition(this.appearance.getX()-25, this.appearance.getY()-25);
            new_formation.getAppearance().setSize(50, 50);
            appearance.addFormation(new_formation.getAppearance(), new_formation);
        }else
        {
            formationsEnemy.add(new_formation);
            new_formation.getAppearance().setPosition(this.appearance.getX()-25, this.appearance.getY()-25);
            new_formation.getAppearance().setSize(50, 50);
            appearance.addFormation(new_formation.getAppearance(), new_formation);
        }
    }
    public  boolean removeFormation(Formation formation)
    {
        if (formation.getOwner()==getOwner())formationsOwner.remove(formation);
        else formationsEnemy.remove(formation);
        appearance.removeFormation(formation);
        return  false;
    }


    public ArrayList<Formation> getFormationsOwner() {
        return formationsOwner;
    }

    public void setFormationsOwner(ArrayList<Formation> formationsOwner) {
        this.formationsOwner = formationsOwner;
    }

    public ArrayList<Formation> getFormationsEnemy() {
        return formationsEnemy;
    }

    public void setFormationsEnemy(ArrayList<Formation> formationsEnemy) {
        this.formationsEnemy = formationsEnemy;
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

    public void nextMove(Stage stage, Skin skin)
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

            if (formationsEnemy.size()!=0) {
                owner.getProvinceWithEnemy().add(this);
                WinBattle battle = new WinBattle(formationsOwner, formationsEnemy, skin,stage, (float) 0.8 * Gdx.graphics.getWidth(), (float) 0.8 * Gdx.graphics.getHeight());
                battle.setOrigin(10);
                battle.setVisible(true);
                battle.debug();
                battle.setPosition(battle.getX() + (Gdx.graphics.getWidth() - battle.getWidth()) / 2, battle.getY() + (Gdx.graphics.getHeight() - battle.getHeight()) / 2);
                stage.addActor(battle);
            }


    }

    public void update()
    {

    }
    public AppearanceProvince getAppearance() {
        return appearance;
    }
    public void setAppearance(AppearanceProvince appearance) {
        this.appearance = appearance;
    }
    public void setPosition(Vector2 position) {
        appearance.setPosition(position.x, position.y);
    }


    public ArrayList<Province> getNeighbours() {return neighbours;}

    public void setNeighbours(ArrayList<Province> neighbours) {this.neighbours = neighbours;}

    public String getName() {return this.name;}

    @Override
    public int compareTo(Province province) {
        return level;
    }
}
