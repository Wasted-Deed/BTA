package wastedgames.game.map;

public class Player {
    private Type mType;
    private String name;

    public enum Type {
        HERO, DIPLOMAT, FIGHTER, PEOPLE
    }

    public Player() {
    }

    public void makeMove() {

    }

    public Type getmType() {
        return mType;
    }


}