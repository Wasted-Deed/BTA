package wastedgames.game.card.Province;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import wastedgames.game.card.Province.Information.InfoPage;
import wastedgames.game.map.Province;
import wastedgames.game.map.Unit;

public class CardProvince extends Dialog
{
    private int result=-1;
    private Province province;
    private Group production;
    private Group army;
    private Group building;

   // private Group information;
    private wastedgames.game.card.Province.Information.InfoPage information;
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    /*private void settingChildInfo()
    {
        TitleInfo.setSize(information.getWidth()/3,100);
        TitleInfo.setPosition((float) (1.25*information.getWidth()/3),information.getHeight()-100);
        TitleInfo.setFontScale(3,3);
    }*/

    public void fillcardsArmy(Skin skin)
    {
        int count=0;
        for (final Unit unit:province.getUnitsforbuy())
        {

            CardBuy card=new CardBuy(skin);
            card.setUnit(unit);
            card.setSize(army.getWidth()/2,army.getHeight()/5);
            if (count%2==0) card.setPosition(0,(army.getHeight()-card.getHeight())-count*card.getHeight());
               else card.setPosition(army.getWidth()/2,(army.getHeight()-card.getHeight())-(count-1)*card.getHeight());
            card.customization();
            card.getB_buy().addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                  province.getQueuemake().add(unit.clone());
                }
            });
            army.addActor(card);
            count++;
        }
    }

    public wastedgames.game.card.Province.Information.InfoPage getInformation() {
        return information;
    }

    public void setInformation(wastedgames.game.card.Province.Information.InfoPage information) {
        this.information = information;
    }

    public CardProvince(String title, Skin skin, final Province province)
    {
        super(title, skin);

        this.setPosition(0,0);

        this.province=province;
        production=new Group();
        production.setPosition(0,0);
        production.setVisible(true);
        production.setSize((float) (Gdx.graphics.getWidth()*0.7),(float) (Gdx.graphics.getHeight()*0.8));

        information=new InfoPage(skin,province,(float) (Gdx.graphics.getWidth()*0.7),(float) (Gdx.graphics.getHeight()*0.8));
        information.setProvince(province);
        information.setVisible(false);
        information.setSize(0,0);

        TextButton armyButton=new TextButton("Army",skin);
        TextButton buildingButton=new TextButton("Building",skin);
        armyButton.setPosition(0 ,production.getHeight()-production.getHeight()/10 );
        armyButton.setSize(production.getWidth()/2,production.getHeight()/10);
        armyButton.getLabel().setFontScale(3,3);
        buildingButton.setPosition(production.getWidth()/2 ,production.getHeight()-production.getHeight()/10 );
        buildingButton.setSize(production.getWidth()/2,production.getHeight()/10);
        buildingButton.getLabel().setFontScale(3,3);

        army=new Group();
        army.setHeight(production.getHeight()-buildingButton.getHeight());
        army.setWidth(production.getWidth());
        fillcardsArmy(skin);



        building=new Group();
        building.setHeight(production.getHeight()-buildingButton.getHeight());
        building.setWidth(production.getWidth());
        building.setVisible(false);

        production.addActor(buildingButton);
        production.addActor(armyButton);
        production.addActor(army);
        production.addActor(building);

        TextButton b_production=new TextButton("Production",skin);
        b_production.setWidth((float) (getWidth()*0.4));
        getButtonTable().add(b_production);
        TextButton b_info=new TextButton("Information",skin);
        b_info.setWidth((float) (getWidth()*0.4));
        getButtonTable().add(b_info);
        button("X",0);

        this.getContentTable().add(production);
        this.getContentTable().add(information);

        b_info.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    production.setVisible(false);
                    production.setPosition(-10000,-10000);
                    production.setSize(0,0);
                    information.setVisible(true);
                    information.setPosition(0,getTitleTable().getPadBottom());
                    information.setSize((float) (Gdx.graphics.getWidth()*0.7),(float) (Gdx.graphics.getHeight()*0.8));
                    information.settingChildInfo();
                    setResizable(true);
                    invalidateHierarchy();
                }
            });
        b_production.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                information.setVisible(false);
                information.setPosition(-10000,-10000 );
                information.setSize(0,0);
                production.setVisible(true);
                production.setPosition(0,0);
                production.setSize((float) (Gdx.graphics.getWidth()*0.7),(float) (Gdx.graphics.getHeight()*0.8));
                setResizable(true);
                invalidateHierarchy();
            }
        });
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

    public CardProvince(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public CardProvince(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }
    protected void result(Object object)
    {
        result= (int) object;
        this.hide();
    }
}
