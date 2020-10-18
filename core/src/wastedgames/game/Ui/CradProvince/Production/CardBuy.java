package wastedgames.game.Ui.CradProvince.Production;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import wastedgames.game.map.Unit;

public class CardBuy extends Group
{
    private Image image;
    private TextButton b_buy;
    private Group Description;
    private Label name;
    private Skin skin;
    private Unit unit;
    public Image getImage() {
        return image;
    }

    public TextButton getB_buy() {
        return b_buy;
    }

    public Group getDescription() {
        return Description;
    }

    public Label getname() {
        return name;
    }
     public void reset()
     {
         getB_buy().getLabel().setColor(Color.WHITE);
         getB_buy().setChecked(false);
     }
    public CardBuy(Skin skin)
    {

        this.b_buy=new TextButton("Make",skin);
        this.skin=skin;
        Description=new Group();
        name=new Label("",skin);
        image=new Image();
    }
     public void addDescription()
     {


         Description.setSize(name.getWidth(),getHeight()-name.getHeight()-b_buy.getHeight());
         Description.setPosition(1,getHeight()-name.getHeight()-Description.getHeight());
         Label price=new Label("Price: "+unit.getPrice(),skin);
         price.setSize(Description.getWidth(),Description.getHeight()/3);
         price.setFontScale((float) 2.5);
         price.setPosition(1,Description.getHeight()-price.getHeight());
         Label level=new Label("Level: "+unit.getLevel(),skin);
         level.setFontScale((float) 2.5);
         level.setSize(Description.getWidth(),Description.getHeight()/3);
         level.setPosition(1,Description.getHeight()-price.getHeight()-level.getHeight());
         Description.addActor(price);
         Description.addActor(level);
         addActor(Description);
     }
    public void customization()
    {

            name.setFontScale(3);
            name.setSize((float) (getWidth() * 0.8), getHeight() / 6);
            name.setPosition(1, getHeight() - name.getHeight());
            name.setText(unit.getName());

            b_buy.setWidth(getWidth());
            b_buy.setHeight(getHeight() / 7);
            b_buy.setPosition(1, b_buy.getY());
            b_buy.getLabel().setFontScale(2, 2);
            b_buy.setText(b_buy.getText()+"( "+unit.getCountYears()+" years)");
            image.setHeight(getHeight() - b_buy.getHeight());
            image.setPosition((float) (getWidth() * 0.8)-2,b_buy.getHeight());
            image.setWidth((float) (getWidth() * 0.2));
            addDescription();
            addActor(b_buy);
            addActor(image);
            addActor(name);

    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
