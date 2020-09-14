package wastedgames.game.Tech;

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
    /* private void showDescription()
    {
        ObjectMap.Entries<Technology, TextButton> its=layoutMapping.iterator();
        while (its.hasNext())
        {
            ObjectMap.Entry<Technology, TextButton> currents=its.next();
            TextButton current=currents.value;
            Vector3 mouseScreenPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            System.out.println("mouseScreenPosition: x="+mouseScreenPosition.x+" y="+mouseScreenPosition.y);
            current.getStage().getCamera().unproject(mouseScreenPosition);
            Vector2 mouseScreenPosition2 = new Vector2(mouseScreenPosition.x,mouseScreenPosition.y);
            Vector2 mouseLocalPosition = current.screenToLocalCoordinates(mouseScreenPosition2);
            Vector2 mouseStagePosition=stage.screenToStageCoordinates(mouseScreenPosition2);
            System.out.println("Button: x1="+current.getX()+" y1="+current.getY()+" right="+current.getRight()+" top="+current.getTop());
            System.out.println(" Gdx.graphics.getHeight()/2 ="+ Gdx.graphics.getHeight()/2);
            System.out.println("mouseScreenPosition: x="+mouseScreenPosition.x+" y="+mouseScreenPosition.y);
            System.out.println("mouseScreenPosition2: x="+mouseScreenPosition2.x+" y="+mouseScreenPosition2.y);
            System.out.println("mouseLocalPosition: x="+mouseLocalPosition.x+" y="+mouseLocalPosition.y);
            System.out.println("mouseStagePosition: x="+mouseStagePosition.x+" y="+mouseStagePosition.y);
            if (current.getParent()==null)
            {
                System.out.println("!!!!!!!!!!!-------!!!!!!!");

            }
            if (current.getStage()==null)
            {
                System.out.println("!!!!!!!!!!!+++++++!!!!!!!");
            }
            else
            {
                Stage stage1=current.getStage();
                int i=0;
                i++;
            }
            boolean f1=(current.getX()<mouseScreenPosition.x);
            boolean f2=(current.getRight()>mouseScreenPosition.x);
            boolean f3=(current.getY()<mouseScreenPosition.y);
            boolean f4=(current.getTop()>mouseScreenPosition.y);
            System.out.println("f1="+f1+" f2="+f2+" f3="+f3+" f4="+f4);
            if((f1)&&(f2)&& (f3)&&(f4))
            {
                descriptionTech.setPosition(mouseScreenPosition.x, mouseScreenPosition.y);
            }
            break;
        }
    } */
    public void update()
    {
        ObjectMap.Entries<Technology, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;
            Technology currentTech=layoutMapping.findKey(current,true);
            if (currentTech.getConditionTech()==ConditionTech.EXPLORE )
            {
                current.getLabel().setColor(Color.RED);
            }
            if (currentTech.getConditionTech()==ConditionTech.LEARNED )
            {
                current.getLabel().setColor(Color.ORANGE);
            }
            if (!currentTech.isCanExplore() )
            {
               // current.setChecked(false);
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
    public  void hide_selection()
    {
        ObjectMap.Entries<Technology, TextButton> it=layoutMapping.iterator();
        while (it.hasNext())
        {
            TextButton current=it.next().value;

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


    public void draw(Technology tech)
    {
        final TextButton button = layoutMapping.get(tech);
        button.draw(batch,1);
        Array<Technology> list= tech.getNextTechnologies();
        for (int i=0;i<list.size;i++)
        {
            Technology currentTech=list.get(i);
            draw(currentTech);
        }

    }
}
