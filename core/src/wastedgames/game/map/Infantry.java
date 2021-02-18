package wastedgames.game.map;

public class Infantry extends Unit
{

   public Infantry(int level) {
        super(UnitType.INFANTRY, 20 * level,20 * level, 45 * level, 1 * level,
                100 * level, 3 * level, level,level,"Infatry", 20);
    }

    @Override
    public Unit clone()
    {

        return new Infantry(getLevel());
    }
}
