package wastedgames.game.Ui.CradProvince.Information;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import wastedgames.game.map.Unit;

public class ButtonUnit extends TextButton
{
    Label name;
    ImageButton delete;
    ImageButton getOutCastle;
    Skin skin;
    Unit unit;




    public Skin getSkin() {
        return skin;
    }

    @Override
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Label getname() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public ImageButton getDelete() {
        return delete;
    }

    public void setDelete(ImageButton delete) {
        this.delete = delete;
    }

    public ImageButton getGetOutCastle() {
        return getOutCastle;
    }

    public void setGetOutCastle(ImageButton getOutCastle) {
        this.getOutCastle = getOutCastle;
    }

    public ButtonUnit(String text, Skin skin)
    {
        super(text, skin);
        this.skin=skin;
    }

    public ButtonUnit(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public ButtonUnit(String text, TextButtonStyle style) {
        super(text, style);
    }
    public void addLabelName(float x,float y,String sname)
    {
         name=new Label( sname+"",skin);
        name.setPosition(x,y);
        name.setFontScale((float) 2);
        addActor(name);
    }
    public void addButtonDelete()
    {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        Drawable sprite=new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Button_icon/X.png"))));
        style.up=sprite;
        style.down         = sprite;
        style.checked      = sprite;
        delete=new ImageButton(style);
       // delete.debug();
        delete.setStyle(style);
        delete.setSize(getWidth()/7,40);
        delete.setPosition(getWidth()-delete.getWidth(),name.getY());
        addActor(delete);
    }

}
