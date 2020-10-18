package wastedgames.game.Tech;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import wastedgames.game.map.Unit;

public class Technology
{
    private  String name;
    private ArrayList<Unit> openedUnit=new ArrayList();
    private String description;
    private ConditionTech conditionTech=ConditionTech.NO_LEARNED;
    private int cost;
    private boolean canExplore=false;
    private  int id;
    private Array<Technology> nextTechnologies;

    public ArrayList<Unit> getOpenedUnit() {
        return openedUnit;
    }

    public void setOpenedUnit(ArrayList<Unit> openedUnit) {
        this.openedUnit = openedUnit;
    }



    public ConditionTech getConditionTech() {
        return conditionTech;
    }

    public void setConditionTech(ConditionTech conditionTech) {
        this.conditionTech = conditionTech;
    }

    public boolean isCanExplore() {
        return canExplore;
    }

    public void setCanExplore(boolean canExplore) {
        this.canExplore = canExplore;
    }
    public void update(Technology CurrentExplore)
    {

        if (conditionTech==ConditionTech.EXPLORE)
        {
           if (CurrentExplore==this){
               setCost(cost-1);
           }else{
               if(cost>0)this.setConditionTech(ConditionTech.NO_LEARNED);
           }
        }
        Array.ArrayIterator<Technology> iterator = (Array.ArrayIterator<Technology>) nextTechnologies.iterator();
        while(iterator.hasNext())
        {
            Technology current=iterator.next();
            current.update(CurrentExplore);
        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Technology(String name,int id)
    {
        this.name = name;
        this.id=id;
        nextTechnologies=new Array<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Array<Technology> getNextTechnologies() {
        return nextTechnologies;
    }

    public void setNextTechnologies(Array<Technology> nextTechnologies) {
        this.nextTechnologies = nextTechnologies;
    }

}
