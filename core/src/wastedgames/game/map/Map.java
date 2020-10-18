package wastedgames.game.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.function.Consumer;

import wastedgames.game.Ui.CradProvince.CardProvince;
import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;
public class Map extends Group {
    ArrayList<Province> provinces;
    Skin skin;
    CardProvince info;
    private Player human;

    public Player getHuman() {
        return human;
    }

    public void setHuman(Player human) {
        this.human = human;
    }

    public Map(Skin skin )
    {
        provinces = new ArrayList<>();
        this.skin=skin;
    }

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }



    public void click(Province province)
    {
        Formation selectedFormation=human.getSelectFormation();
        if (selectedFormation==null) {
            info = new CardProvince("province1", skin, province);
            info.setProvince(province);
            info.show(getStage());
        }else
        {
            selectedFormation.setTrack(findPath(selectedFormation.getLocation(),province));
        }
    }
    public void fillMap()
    {

        Province province0 = new Province(new SpriteDrawable(ResourceLoader.getSprite(Image.PROVINCE_PARIS)));
        province0.setPosition(new Vector2(200, 300));
        province0.setName("province0");
        this.addActor(province0.getAppearance());
        provinces.add(province0);
        Province province1 = new Province(new SpriteDrawable(ResourceLoader.getSprite(Image.PROVINCE_PARIS)));
        province1.setPosition(new Vector2(300, 300));
        province1.setName("province1");
        this.addActor(province1.getAppearance());
        provinces.add(province1);
        province0.getNeighbours().add(province1);

        Province province2 = new Province(new SpriteDrawable(ResourceLoader.getSprite(Image.PROVINCE_PARIS)));
        province2.setPosition(new Vector2(400, 300));
        province2.setName("province2");
        this.addActor(province2.getAppearance());
        provinces.add(province2);
        province1.getNeighbours().add(province2);

        province2.getAppearance().getAppearance().addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                click(province2);
            }
        });
        province1.getAppearance().getAppearance().addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                click(province1);
            }
        });
        province0.getAppearance().getAppearance().addListener(new ChangeListener()
                {
                    @Override
                    public void changed(ChangeEvent event, Actor actor)
                    {
                        click(province0);
                    }
                });
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


    public void nextMove()
    {
        for (Province current: provinces)
            current.update();

    }
    public void update(Stage stage,Skin skin)
    {

       if (info!=null)info.update();
    }

}
