package wastedgames.game.Ui.CradProvince;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import wastedgames.game.Ui.CradProvince.Information.InfoPage;
import wastedgames.game.Ui.CradProvince.Production.ProductionPage;
import wastedgames.game.map.Province;

public class CardProvince extends Dialog
{
    private int result=-1;
    private Province province;
    private InfoPage information;
    private ProductionPage production;
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




    public InfoPage getInformation() {
        return information;
    }

    public void setInformation(InfoPage information) {
        this.information = information;
    }

    public CardProvince(String title, Skin skin, final Province province)
    {
        super(title, skin);
        this.setPosition(0,0);
        this.province=province;
        production=new ProductionPage(skin,province);

        information=new InfoPage(skin,province,(float) (Gdx.graphics.getWidth()*0.7),(float) (Gdx.graphics.getHeight()*0.8));
        information.setProvince(province);
        information.setVisible(false);
        information.setSize(0,0);


        this.getContentTable().add(production);
        this.getContentTable().add(information);

        getButtonTable().debug();

        TextButton b_production=new TextButton("Production",skin);
        b_production.setWidth((float) (getWidth()*0.4));
        getButtonTable().add(b_production).growY().width((float) (getWidth()*0.4));
        TextButton b_info=new TextButton("Information",skin);
        b_info.setWidth((float) (getWidth()*0.4));
        getButtonTable().add(b_info).growX().width((float) (getWidth()*0.4));
        button("X",0);


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



    }
    public  void  update()
    {
        production.update();
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
