package wastedgames.game.Ui.Battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

import wastedgames.game.map.Formation;
import wastedgames.game.map.Unit;

public class ResultBattle extends Group
{
    private ListArmy oneArmy;
    private ListArmy twoArmy;
    private Label name;
    public ResultBattle(ArrayList<Formation> one, ArrayList<Formation> two, boolean result, Skin skin)
    {
        if (result) name=new Label("You won",skin);
        else name=new Label("You lose",skin);
        name.setSize(getWidth()/4,getHeight()/10);
        name.setPosition((float) (name.getWidth()*1.5),getHeight()-name.getHeight());
        addActor(name);
        oneArmy=new ListArmy(one.get(0),null,getWidth()/2,(getHeight()-name.getHeight()));
        oneArmy.setPosition(0,0);
        addActor(oneArmy);
        twoArmy=new ListArmy(two.get(0),null,getWidth()/2,(getHeight()-name.getHeight()));
        twoArmy.setPosition(getWidth()/2,0);
        addActor(twoArmy);



    }

}
