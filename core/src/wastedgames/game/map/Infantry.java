package wastedgames.game.map;

public class Infantry extends Unit {

    Infantry(int level) {
        super(UnitType.INFANTRY, 90 * level, 45 * level, 1 * level,
                100 * level, 3 * level, level);
    }


}
