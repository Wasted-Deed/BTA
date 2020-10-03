package wastedgames.game.map;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import wastedgames.game.Tech.ConditionTech;
import wastedgames.game.Tech.Technology;

public class Player {
    private Type mType;
    private String name;
    private Technology AllTechnologies;
    private ArrayList<Technology> Learned_technologies;
    private Technology currentExlpore=new Technology("",-1);
    private ArrayList<Province> provinces;

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

    public Technology getCurrentExlpore() {
        return currentExlpore;
    }

    public void setCurrentExlpore(Technology currentExlpore) {
        this.currentExlpore = currentExlpore;
    }

    public Technology getAllTechnologies() {
        return AllTechnologies;
    }

    public void setAllTechnologies(Technology allTechnologies) {
        AllTechnologies = allTechnologies;
    }


    public enum Type {
        HERO, DIPLOMAT, FIGHTER, PEOPLE
    }

    public Player()
    {
        Learned_technologies=new ArrayList<>();
        provinces=new ArrayList<>();
        currentExlpore.setCost(10000);
    }

    public void makeMove()
    {

    }

    public Type getmType() {
        return mType;
    }

    public void  update()
    {

        if (currentExlpore.getCost()<=0&&currentExlpore.getConditionTech()==ConditionTech.EXPLORE)
        {
            currentExlpore.setConditionTech(ConditionTech.LEARNED) ;
            Array.ArrayIterator<Technology> iterator = (Array.ArrayIterator<Technology>) currentExlpore.getNextTechnologies().iterator();
            while(iterator.hasNext())
            {
                Technology current=iterator.next();
                current.setCanExplore(true);
            }
            Learned_technologies.add(currentExlpore);
            for (Province province:provinces)
            {
                 province.addTech(currentExlpore);
            }

        }
    }
}