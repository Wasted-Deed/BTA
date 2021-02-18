package wastedgames.game.Ui.Battle;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import wastedgames.game.Battle;
import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Formation;

public class WinBattle extends WidgetGroup
{

    private Label name;
    private TextButton Attack;
    private TextButton Back;
    private ListArmy ArmyOne;
    private ListArmy ArmyTwo;
    private ProgressBar BalancePower;
    private Image background;

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public  WinBattle(ArrayList<Formation> armyone, ArrayList<Formation> armyTwo, Skin skin, Stage stage, float width, float height)
    {
        setSize(width,height);
        WinBattle win=this;

        background=new Image(new TextureRegionDrawable(new TextureRegion(ResourceLoader.getImage(wastedgames.game.maintenance.Image.BattleBackground))));
        background.setSize(getWidth(),getHeight());
        addActor(background);
        Attack=new TextButton("Attack",skin);
        Attack.setSize(getWidth()/2,getHeight()/10);
        addActor(Attack);
        Attack.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                win.setVisible(false);
                Battle battle=new Battle();
                ResultBattle resultBattle=new ResultBattle(armyone,armyTwo,battle.battle(armyone,armyTwo,3),skin);
                resultBattle.setSize(getWidth()/2,getHeight()/2);
                resultBattle.setPosition((float) (getWidth()*0.25), (float) (getWidth()*0.25));

                resultBattle.debug();
                stage.addActor(resultBattle);
            }
        });

        Back=new TextButton("Back",skin);
        Back.setSize(getWidth()/2,getHeight()/10);
        Back.setPosition(getWidth()/2,0);
        addActor(Back);
        Back.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                win.setVisible(false);

            }
        });

        ArmyOne=new ListArmy(armyone.get(0),null,getWidth()/2,getHeight()/3);
        ArmyOne.setPosition(0,Back.getHeight());
        ArmyOne.debug();
        addActor(ArmyOne);


        ArmyTwo=new ListArmy(armyTwo.get(0),null,getWidth()/2,getHeight()/3);
        ArmyTwo.setPosition(ArmyOne.getWidth(),Back.getHeight());
        ArmyTwo.debug();
        addActor(ArmyTwo);
        name=new Label("Battle",skin);
        name.setSize(getWidth()/4,getHeight()/10);
        name.setPosition((float) (name.getWidth()*1.5),getHeight()-name.getHeight());
        addActor(name);
        BalancePower=new ProgressBar(0,100,1,false,skin) ;
        BalancePower.setWidth(getWidth()-2*getWidth()/10);
        BalancePower.setHeight(getHeight()/10);

        BalancePower.debug();

        BalancePower.setPosition(getWidth()/10,name.getY()-BalancePower.getHeight());

        addActor(BalancePower);




    }

}
