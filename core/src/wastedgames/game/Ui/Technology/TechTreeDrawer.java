package wastedgames.game.Ui.Technology;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import wastedgames.game.Tech.ConditionTech;
import wastedgames.game.Tech.Technology;

public class TechTreeDrawer
{
    private ObjectMap<Technology, TextButton> layoutMapping;
    
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;


    public TechTreeDrawer(SpriteBatch batch, Skin skin,Stage stage) {
        this.batch = batch;
        this.skin=skin;
        layoutMapping=new ObjectMap<>();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
    public void show()
    {
        ObjectMap.Entries<Technology, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;
            current.setVisible(true);
        }
    }

    public void update()
    {
        ObjectMap.Entries<Technology, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;
            Technology currentTech=layoutMapping.findKey(current,true);

            if (currentTech.getConditionTech()== ConditionTech.EXPLORE )
            {
                current.getLabel().setColor(Color.RED);
            }
            if (currentTech.getConditionTech()==ConditionTech.LEARNED )
            {
                current.getLabel().setColor(Color.ORANGE);
            }
            if (!currentTech.isCanExplore() )
            {
                current.setDisabled(true);
            }
        }
    }
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void hide()
    {
        ObjectMap.Entries<Technology, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;
             current.setVisible(false);
        }
    }
    public TextButton add(Technology technology, Rectangle position)
    {
            final TextButton button=new TextButton(technology.getName(),skin);
            button.getLabel().setFontScale((float) 2.5);
            button.setY(position.getY());
            button.setX(position.getX());
            button.setWidth(position.getWidth());
            button.setHeight(position.getHeight());


            stage.addActor(button);
            layoutMapping.put(technology,button);
            return button;
    }
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }



}
