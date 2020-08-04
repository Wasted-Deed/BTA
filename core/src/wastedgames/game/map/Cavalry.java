package wastedgames.game.map;

public class Cavalry extends Unit{
    Cavalry(int level) {
        super(UnitType.CAVALRY, 110 * level, 60 * level, 2 * level,
                150 * level, 2 * level, level);
    }


}
