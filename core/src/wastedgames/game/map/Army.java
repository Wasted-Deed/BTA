package wastedgames.game.map;

import java.util.ArrayList;

public class Army {
    private Player owner;
    private ArrayList<Formation> army;

    Army(ArrayList<Formation> army, Player owner) {
        this.owner = owner;
        this.army = army;
    }
}
