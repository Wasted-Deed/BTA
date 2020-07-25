package wastedgames.game.Tech;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class TechTreeDrawer
{
    private ObjectMap<String, TextButton> layoutMapping;
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TechTreeDrawer(SpriteBatch batch, Skin skin) {
        this.batch = batch;
        this.skin=skin;
        layoutMapping=new ObjectMap<>();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
    public void show()
    {
        ObjectMap.Entries<String, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;
            current.setVisible(true);
        }
    }
    public void hide()
    {
        ObjectMap.Entries<String, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;
             current.setVisible(false);
        }
    }
    public void add(String name, Rectangle position)
    {
            final TextButton button=new TextButton(name,skin);
            button.getLabel().setFontScale((float) 2.5);
            button.setY(position.getY());
            button.setX(position.getX());
            button.setWidth(position.getWidth());
            button.setHeight(position.getHeight());
            button.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    button.getLabel().setColor(Color.ORANGE);

                }
            });
            layoutMapping.put(name,button);
    }
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void draw(Technology tech)
    {
        final TextButton button = layoutMapping.get(tech.getName());

        stage.addActor(button);


        button.draw(batch,1);
        Array<Technology> list= tech.getNextTechnologies();
        for (int i=0;i<list.size;i++)
        {
            Technology currentTech=list.get(i);
            draw(currentTech);
        }
    }
}
