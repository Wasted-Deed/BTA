package wastedgames.game.Tech;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DescribeTech extends Table
{
    Label describe;
    TextButton b_explore;
    Technology currentTech;
    public Technology getCurrentTech() {
        return currentTech;
    }

    public void setCurrentTech(Technology currentTech)
    {
        this.currentTech = currentTech;
        describe.setText(currentTech.getDescription());
        describe.invalidate();
    }


    public Label getDescribe() {
        return describe;
    }

    public void setDescribe(Label describe) {
        this.describe = describe;
    }

    public TextButton getB_explore() {
        return b_explore;
    }

    public void setB_explore(TextButton b_explore)
    {
        this.b_explore = b_explore;
    }

    public DescribeTech(Skin skin, Stage stage)
    {
        super();
        setPosition(0,0);
        setSize(Gdx.graphics.getWidth(),200);
        b_explore=new TextButton("Explore",skin);
        b_explore.getLabel().setFontScale(3);
        describe=new Label("",skin);
        describe.setFontScale(4,4);
        this.add(describe).width(3*getWidth()/4).height(getHeight()-10).left();
        this.add(b_explore).width(getWidth()/4).height(getHeight()-10).right();

    }
}
