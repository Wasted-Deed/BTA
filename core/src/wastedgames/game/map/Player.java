package wastedgames.game.map;

import wastedgames.game.Tech.Technology;

public class Player {
    private Type mType;
    private String name;
    private Technology Technologies;
    private Technology currentExlpore=new Technology("",-1);

    public Technology getCurrentExlpore() {
        return currentExlpore;
    }

    public void setCurrentExlpore(Technology currentExlpore) {
        this.currentExlpore = currentExlpore;
    }

    public Technology getTechnologies() {
        return Technologies;
    }

    public void setTechnologies(Technology technologies) {
        Technologies = technologies;
    }


    public enum Type {
        HERO, DIPLOMAT, FIGHTER, PEOPLE
    }

    public Player() {
    }

    public void makeMove()
    {

    }

    public Type getmType() {
        return mType;
    }


}