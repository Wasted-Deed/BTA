package wastedgames.game.Ui.CradProvince.Information;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import wastedgames.game.maintenance.Image;
import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Formation;
import wastedgames.game.map.Player;
import wastedgames.game.map.Province;

public class InfoPage extends Group
{
    private Label TitleInfo;
    private Group statistics;
    private ListUnit  units;
    private Province province;



    public InfoPage(Skin skin, Province province, float width, float height)
    {

        setSize(width,height);

        this.province=province;
        TitleInfo=new Label("Description",skin);
        TitleInfo.setSize(getWidth()/3,100);
        TitleInfo.setPosition((float) (1.25*getWidth()/3),getHeight()-100);
        TitleInfo.setFontScale(3,3);

        statistics=new Group();
        statistics.setWidth(getWidth());
        statistics.setHeight(getHeight()/3);
        statistics.setPosition(0,TitleInfo.getY()-statistics.getHeight());
        Label CountUnit=new Label("\nNumber of troops in the province:  "+province.getUnitsInCastle().size(),skin);
        CountUnit.setPosition(0,statistics.getHeight()-CountUnit.getHeight());
        CountUnit.setSize(statistics.getWidth()/2,statistics.getHeight()/5);
        CountUnit.setFontScale(2);

        statistics.addActor(CountUnit);

        addActor(TitleInfo);
        addActor(statistics);


        Label nameListUnit=new Label("Troops in the castle",skin);
        nameListUnit.setSize(getWidth()/3,100);
        nameListUnit.setPosition((float) (1.25*getWidth()/3),statistics.getY()-nameListUnit.getHeight());
        nameListUnit.setFontScale((float) 3);

        units=new ListUnit(null);
        units.setSize(getWidth(),getHeight()/3);
        units.setPosition(0,nameListUnit.getY()-units.getHeight());
        units.setProvince(province);
        units.settingListUnit(skin);

        addActor(nameListUnit);
        addActor(units);
        addbuttonOnList();

    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


    public void  addbuttonOnList()
    {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        Drawable sprite=new SpriteDrawable(ResourceLoader.getSprite(Image.CLOSE));
        style.up           = sprite;
        style.down         = sprite;

        ImageButton cancel=new ImageButton(style);
        cancel.setStyle(style);
        cancel.setSize(units.getWidth()/25,units.getWidth()/25);
        cancel.setPosition(units.getX()+units.getWidth()-cancel.getWidth(),units.getY()+units.getHeight()-cancel.getHeight());
        System.out.println("cancel Width="+cancel.getWidth()+" Heigth="+cancel.getHeight()+" Position Y="+cancel.getY()+" position x = "+cancel.getX());
        addActor(cancel);

                cancel.addListener(new ChangeListener()
                    {
                        @Override
                        public void changed(ChangeEvent event, Actor actor)
                        {
                            units.getFormation().getUnits().clear();
                            units.getB_units().forEach(textButton -> { textButton.setColor(Color.GRAY);});
                        }
                    });

        style = new ImageButton.ImageButtonStyle();

        sprite=new SpriteDrawable(ResourceLoader.getSprite(Image.GET_OUT));
        style.up=sprite;
        style.down         = sprite;
        style.checked      = sprite;

        ImageButton getOutCastle=new ImageButton(style);
        getOutCastle.setStyle(style);
        getOutCastle.setSize(units.getWidth()/25,units.getWidth()/25);
        getOutCastle.setPosition(cancel.getX()-getOutCastle.getWidth(),units.getY()+units.getHeight()-getOutCastle.getHeight());
        System.out.println("cancel Width="+cancel.getWidth()+" Heigth="+cancel.getHeight()+" Position Y="+cancel.getY()+" position x = "+cancel.getX());
        getOutCastle.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {

                    units.getB_units().forEach(buttonUnit -> {
                        if (buttonUnit.isChecked())
                        {
                            province.getUnitsInCastle().remove(buttonUnit.getUnit());
                            units.getScrollTable().getCells().removeValue(units.getScrollTable().getCell(buttonUnit),true);
                            buttonUnit.remove();
                        } });
                    Formation formation=units.getFormation().clone();



                    province.addFormation(formation);
                    province.getOwner().getArmy().getFormations().add(formation);
                    units.getFormation().getUnits().clear();

                }
            });
        addActor(getOutCastle);


    }
}
