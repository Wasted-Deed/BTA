package wastedgames.game.Ui.CradProvince.Production;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.function.Consumer;

import wastedgames.game.map.Province;
import wastedgames.game.map.Unit;

public class ProductionPage extends Group
{
    private Group army;
    private Group building;
    private Province province;
    private ArrayList<CardBuy> cards=new ArrayList<>();
    public void fillcardsArmy(Skin skin)
    {
        int count=0;
        building=new Group();
        final TextButton.TextButtonStyle style=new TextButton.TextButtonStyle();
        style.checkedFontColor= Color.ORANGE;
        for (final Unit unit:province.getUnitsforbuy())
        {
             final Unit newUnit=unit.clone();
            final CardBuy card=new CardBuy(skin);

            card.setUnit(newUnit);
            card.setSize(army.getWidth()/2-2,army.getHeight()/5);
            if (count%2==0) card.setPosition(2,(army.getHeight()-card.getHeight())-count*card.getHeight());
            else card.setPosition(army.getWidth()/2,(army.getHeight()-card.getHeight())-(count-1)*card.getHeight());
            card.customization();

            card.getB_buy().addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    province.setQueuemake(newUnit);
                    for (CardBuy current: cards)
                       if (current!=card ) current.reset();
                }
            });
            cards.add(card);
            army.addActor(card);
            count++;
        }
    }
    public   ProductionPage(Skin skin,Province province)
    {
        this.province=province;
        setPosition(0,0);
        setVisible(true);
        setSize((float) (Gdx.graphics.getWidth()*0.7),(float) (Gdx.graphics.getHeight()*0.8));
        TextButton armyButton=new TextButton("Army",skin);
        TextButton buildingButton=new TextButton("Building",skin);
        armyButton.setPosition(0 ,getHeight()-getHeight()/10 );
        armyButton.setSize(getWidth()/2,getHeight()/10);
        armyButton.getLabel().setFontScale(3,3);
        addActor(armyButton);
        buildingButton.setPosition(getWidth()/2 ,getHeight()-getHeight()/10 );
        buildingButton.setSize(getWidth()/2,getHeight()/10);
        buildingButton.getLabel().setFontScale(3,3);
        addActor(buildingButton);
        army=new Group();
        army.setHeight(getHeight()-buildingButton.getHeight());
        army.setWidth(getWidth());
        addActor(army);
        fillcardsArmy(skin);

        armyButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                building.setVisible(false);
                army.setVisible(true);
            }
        });

        buildingButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                building.setVisible(true);
                army.setVisible(false);
            }
        });
    }

    public  void  update()
    {
        cards.forEach(cardBuy -> { if (cardBuy.getB_buy().isChecked())cardBuy.getB_buy().getLabel().setColor(Color.ORANGE); });
    }

}
