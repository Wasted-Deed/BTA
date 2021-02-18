package wastedgames.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import sun.management.counter.Units;
import wastedgames.game.map.Formation;
import wastedgames.game.map.Province;
import wastedgames.game.map.Unit;

public class Battle
{
    private int front_width;
    public  boolean battle(ArrayList<Formation> oneArmy,ArrayList<Formation> twoArmy,int front_width)
    {
        this.front_width=front_width;

        int sizeOne= 0;
        int sizeTwo=0;
        ArrayList<Unit> one=new ArrayList<>();
        ArrayList<Unit> two=new ArrayList<>();

        for (Formation formation: oneArmy)
        {
           sizeOne+=formation.getUnits().size();
            for (Unit unit:formation.getUnits()) {
                one.add(unit);
            }
        }
        one.sort((unit, t1) -> {  if(unit.getMorale()>t1.getMorale())return 1; else return 0; });
        for (Formation formation: twoArmy) {
            sizeTwo+=formation.getUnits().size();
            for (Unit unit:formation.getUnits()) {
                two.add(unit);
            }
        }
        two.sort((unit, t1) -> {  if(unit.getMorale()>t1.getMorale())return 1; else return 0; });
        int i=0;
        while (one.size()!=0 && two.size()!=0)
        {

               double random=Math.random()/2;

               Unit current=one.get(0);
                double damage=(double)(one.size()/(one.size()+two.size()))+random;
                if (damage>1)
                {
                    int k=0;
                    k++;
                }
                System.out.println("Damage="+damage);
            current.setCurrent_health((int) (current.getCurrent_health()-(double)10*damage));
            if (current.getMorale()<=0||current.getCurrent_health()<=0)
                 one.remove(0);

                current=two.get(0);
                damage=(double)(one.size()/(one.size()+two.size()))+random;
                if (damage>1.0)
                {
                    int k=0;
                    k++;
                }
                current.setCurrent_health((int) (current.getCurrent_health()-(double)10*damage));
                if (current.getMorale()<=0||current.getCurrent_health()<=0)
                    two.remove(0);
        }
        System.out.println("КОНЕЦ!!!");
        if (one.size()==0) return false;
        else return true;
    }

}
