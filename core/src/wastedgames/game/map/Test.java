package wastedgames.game.map;

import java.util.*;

public class Test {
    public static void test() {
        Unit unit1 = new Infantry(1);
        Unit unit2 = new Infantry(2);
        ArrayList<Province> a = new ArrayList<>();
        a.add(new Province( "Moscow"));
        a.add(new Province("Paris"));
        a.add(new Province("Chelyabinsk"));
        a.add(new Province("Saint"));
        a.add(new Province( "Sochi"));
        a.add(new Province( "Crimea"));
        a.add(new Province( "Field"));
        a.get(0).setNeighbours(new ArrayList<>(Arrays.asList(a.get(3), a.get(5))));
        a.get(1).setNeighbours(new ArrayList<>(Arrays.asList(a.get(2), a.get(3))));
        a.get(2).setNeighbours(new ArrayList<>(Arrays.asList(a.get(1), a.get(3), a.get(4))));
        a.get(3).setNeighbours(new ArrayList<>(Arrays.asList(a.get(1), a.get(2), a.get(4), a.get(0))));
        a.get(4).setNeighbours(new ArrayList<>(Arrays.asList(a.get(2), a.get(3), a.get(6))));
        a.get(5).setNeighbours(new ArrayList<>(Arrays.asList(a.get(0), a.get(6))));
        a.get(6).setNeighbours(new ArrayList<>(Arrays.asList(a.get(5), a.get(4))));

        ArrayList<Province> t = Map.findPath(a.get(1), a.get(5));
        for (Province e : t) {
            System.out.print(e.getName() + "@@@");
        }
        System.out.println();
    }
}
