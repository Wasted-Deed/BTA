package wastedgames.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

import wastedgames.game.Ui.CradProvince.CardProvince;

public class Map
{
    ArrayList<Province> provinces;
    Skin skin;
    CardProvince info;
    Batch batch;

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    private Player human;
    PolygonSpriteBatch polygonSpriteBatch;
     boolean visible;
    public Player getHuman() {
        return human;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setHuman(Player human) {
        this.human = human;
    }

    public Map(Skin skin )
    {
        provinces = new ArrayList<>();
        this.skin=skin;
        polygonSpriteBatch=new PolygonSpriteBatch();
    }

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }



    public void click(Province province,Stage stage)
    {
        Formation selectedFormation=human.getSelectFormation();
        if (selectedFormation==null) {
            info = new CardProvince(province.getName(), skin, province);
            info.setProvince(province);
            info.show(stage);
        }else
        {
            selectedFormation.setTrack(findPath(selectedFormation.getLocation(),province));
            selectedFormation.getAppearance().setPressed(false);
        }

    }
    public void fillMap()
    {

        Province province0 = new Province(new float[]{0,-60,60,-40,60,40,0,60,-60,40,-60,-40},"province0");
        province0.setPosition(new Vector2(200, 300));
        provinces.add(province0);
        Province province1 = new Province(new float[]{-40,0,0,-40,-40,-40,60,-40,-40,40,0,100,60,60},"province1");
        province1.setPosition(new Vector2(300, 300));
        provinces.add(province1);
        province0.getNeighbours().add(province1);
        Province province2 = new Province(new float[]{-40,-40,-40,60,60,0},"province2");
        province2.setPosition(new Vector2(400, 300));
        provinces.add(province2);
        province1.getNeighbours().add(province2);
    }

    public static ArrayList<Province> findPath(Province a, Province end) {
        Province start = a;
        HashMap<Province, Integer> dist = new HashMap<>();
        HashMap<Province, Province> par = new HashMap<>();
        PriorityQueue<MyPair<Integer, Province>> que = new PriorityQueue<>();
        dist.put(start, 0);

        que.add(new MyPair(0, start));
        par.put(start, start);
        while (!que.isEmpty()) {
            MyPair<Integer, Province> v = que.poll();

            que.remove(que.peek());
            if (v.first > dist.get(v.second)) {
                break;
            }
            for (int j = 0; j < v.second.getNeighbours().size(); ++j) {
                Province to = v.second.getNeighbours().get(j);
                if(!dist.containsKey(v))dist.put(to, Integer.MAX_VALUE);
                if (dist.get(to) > v.first + 1) {
                    par.put(to, v.second);
                    dist.put(to, v.first + 1);
                    que.add(new MyPair(dist.get(to), to));
                }
            }
        }
        ArrayList<Province> answer = new ArrayList<>();
        answer.add(end);
        while(par.get(end) != start) {
            end = par.get(end);
            answer.add(end);
        }
        if (start != end) {
            answer.add(start);
        }
        Collections.reverse(answer);
        answer.remove(0);
        return answer;
    }


    public void nextMove(Stage stage)
    {
        for (Province current: provinces)
            current.nextMove(stage,skin);

    }
    public void update(Stage stage,Skin skin)
    {
        if (Gdx.input.justTouched())
        {
            Vector2 vector2=new Vector2( Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            for (Province current : provinces)
            {
                if (current.getAppearance().contains(vector2))
                {
                   if (current.getAppearance().CheckClickUnit(vector2)==null)
                       {
                           click(current, stage);
                           break;
                       }
                }
            }
        }
       if (info!=null)info.update();
    }

    public void draw()
    {
        if (visible) {
            for (Province current : provinces)
            {
                current.getAppearance().draw(polygonSpriteBatch);
            }
        }
    }

}
