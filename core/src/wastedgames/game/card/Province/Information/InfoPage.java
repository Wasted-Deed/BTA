package wastedgames.game.card.Province.Information;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import wastedgames.game.map.Province;
import wastedgames.game.map.Unit;

public class InfoPage extends Group
{
    private Label TitleInfo;
    private Group statistics;
    private ScrollPane  ListUnit;
    private Province province;
    public void settingListUnit(Skin skin)
    {
        final Table scrollTable = new Table();
        scrollTable.setSize(getWidth(),getHeight()/3);
        statistics.debug();
        scrollTable.debug();
         for (Unit unit:province.getUnits())
        {
            TextButton W_unit=new TextButton(unit.getName()+"",skin);
            W_unit.getLabel().setFontScale((float) 2.5);
            System.out.println("W_unit Width="+W_unit.getWidth()+" Heigth="+W_unit.getHeight());
            scrollTable.add(W_unit).growY().width(getWidth()/6);
        }
        ListUnit=new ScrollPane(scrollTable,skin);
        ListUnit.setSize(getWidth(),getHeight()/3);
        ListUnit.setPosition(0,getHeight()-TitleInfo.getHeight()-statistics.getHeight()-ListUnit.getHeight());
        addActor(ListUnit);

    }
    public InfoPage(Skin skin,Province province,float width,float height)
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
        statistics.setPosition(0,getHeight()-TitleInfo.getHeight()-statistics.getHeight());
        Label CountUnit=new Label("\nNumber of troops in the province:  "+province.getUnits().size(),skin);
        CountUnit.setPosition(0,statistics.getHeight()-CountUnit.getHeight());
        CountUnit.setSize(statistics.getWidth()/2,statistics.getHeight()/5);
        CountUnit.setFontScale(2);

        statistics.addActor(CountUnit);

        CountUnit.debug();
        addActor(TitleInfo);
        addActor(statistics);
        settingListUnit(skin);
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void settingChildInfo()
    {
       //TitleInfo.setSize(getWidth()/3,100);
       // TitleInfo.setPosition((float) (1.25*getWidth()/3),getHeight()-100);
       // TitleInfo.setFontScale(3,3);
       // statistics.setWidth(getWidth());
        //statistics.setHeight(getHeight()/3);
       // statistics.setPosition(0,getHeight()-TitleInfo.getHeight()-statistics.getHeight());
     // ListUnit.setSize(getWidth(),getHeight()/10);
        //ListUnit.setPosition(0,getHeight()-TitleInfo.getHeight()-statistics.getHeight()-ListUnit.getHeight());
        System.out.println("TitleInfo Width="+TitleInfo.getWidth()+" Heigth="+TitleInfo.getHeight()+" Position Y="+TitleInfo.getY());
        System.out.println("statistics Width="+statistics.getWidth()+" Heigth="+statistics.getHeight()+" Position Y="+statistics.getY());

        System.out.println("ListUnit Width="+ListUnit.getWidth()+" Heigth="+ListUnit.getHeight()+" Position Y="+ListUnit.getY());

    }
}
