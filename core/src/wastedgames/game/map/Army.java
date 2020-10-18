package wastedgames.game.map;

import java.util.ArrayList;

public class Army {
    private Player owner;
    private ArrayList<Formation> formations=new ArrayList<>();


    public Player getOwner() {
        return owner;
    }

    public ArrayList<Formation> getFormations() {
        return formations;
    }

    Army(Player owner) {
        this.owner = owner;

    }
}
