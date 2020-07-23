package wastedgames.game.map;

import java.util.*;

public class Test {
    public static void test() {
        ArrayList<Province> a = new ArrayList<>();
        Province a1, a2 = null, a3 = null, a4 = null;
        a1 = new Province("Paris");
        a2 = new Province("Chelyabinsk");
        a3 = new Province("Saint");
        a4 = new Province( "Sochi");
        a1.setNeighbours(new ArrayList<Province>(Arrays.asList(a2, a3)));
        a2.setNeighbours(new ArrayList<>(Arrays.asList(a1, a3, a4)));
        a3.setNeighbours(new ArrayList<>(Arrays.asList(a1, a2, a4)));
        a4.setNeighbours(new ArrayList<>(Arrays.asList(a2, a3)));
        a.add(a1);
        a.add(a2);
        a.add(a3);
        a.add(a4);
        ArrayList<Province> t = Map.findPath(a1, a4);
        for (Province e : t) {
            System.out.print(e.getName() + "@@@");
        }
        System.out.println();
    }
}
