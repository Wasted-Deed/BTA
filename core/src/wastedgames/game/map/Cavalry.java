package wastedgames.game.map;

public class Cavalry extends Unit{
    public Cavalry(int level)
    {
        super(UnitType.CAVALRY, 15* level,15 * level, 60 * level, 2 * level,
                150 * level, 2 * level, level,level,"Cavalry",(int) (Math.random()*5));

    }
    @Override
    public Unit clone()
    {

        return new Cavalry(getLevel());
    }

}
