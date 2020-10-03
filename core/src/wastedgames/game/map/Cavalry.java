package wastedgames.game.map;

public class Cavalry extends Unit{
    public Cavalry(int level) {
        super(UnitType.CAVALRY, 110 * level, 60 * level, 2 * level,
                150 * level, 2 * level, level,level,"Cavalry");
    }
    @Override
    public Unit clone()
    {

        return new Cavalry(getLevel());
    }

}
