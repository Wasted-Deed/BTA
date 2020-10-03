package wastedgames.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import wastedgames.game.card.Province.CardProvince;
import wastedgames.game.chars.Drawable;
import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;

public class Map implements Drawable {
    ArrayList<Province> provinces;
    CardProvince info;
    public Map( Skin skin )
    {
        provinces = new ArrayList<>();

    }

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

    public void fillMap() {
        Province province0 = new Province("Paris");
        province0.setAppearance(ResourceLoader.getSprite(Image.PROVINCE_PARIS));
        province0.setPosition(new Vector2(200, 300));
        provinces.add(province0);
    }

    public static ArrayList<Province> findPath(Province a, Province end) {
        Province start = a;
        HashMap<Province, Boolean> us = new HashMap<>();
        HashMap<Province, Integer> dist = new HashMap<>();
        HashMap<Province, Province> par = new HashMap<>();
        PriorityQueue<MyPair<Integer, Province>> que = new PriorityQueue<>();
        dist.put(start, 0);
        us.put(start, true);
        que.add(new MyPair(0, start));
        while (!que.isEmpty()) {
            MyPair<Integer, Province> v = que.peek();
            if (v.second == end) {
                break;
            }
            que.remove(que.peek());
            for (int j = 0; j < v.second.getNeighbours().size(); ++j) {
                Province to = v.second.getNeighbours().get(j);
                if (!us.getOrDefault(to, false) || dist.get(to) > v.first + 1) {
                    us.put(to, true);
                    que.remove(new MyPair(dist.get(to), to));
                    par.put(to, v.second);
                    dist.put(to, v.first + 1);
                    que.add(new MyPair(dist.get(to), to));
                }
            }
        }
        ArrayList<Province> answer = new ArrayList<>();
        answer.add(end);
        while(par.getOrDefault(end, start) != start) {
            end = par.get(end);
            answer.add(end);
        }
        answer.add(start);
        return answer;
    }

    public void update(Stage stage,Skin skin)
    {

        Vector2 cursor=new Vector2(Gdx.input.getX(),Gdx.input.getY());
        for (Province current: provinces)
        {
            current.update();
            Rectangle rec=current.getAppearance().getBoundingRectangle();
            rec.setY(Gdx.graphics.getHeight()-(rec.y+rec.height));
            if (rec.contains(cursor)&&(info==null||info.getResult()!=-1))
            {

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
                info=new CardProvince("Province",skin,current );
                info.setProvince(current);


                info.show(stage);
            }
        }
    }
    @Override
    public void draw(SpriteBatch batch)
    {

        for (Province province : provinces) {
            province.draw(batch);
        }
    }
}
