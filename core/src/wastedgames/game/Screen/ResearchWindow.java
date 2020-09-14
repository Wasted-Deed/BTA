package wastedgames.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import wastedgames.game.Main;
import wastedgames.game.Tech.ConditionTech;
import wastedgames.game.Tech.DescribeTech;
import wastedgames.game.Tech.TechTreeDrawer;
import wastedgames.game.Tech.Technology;


public class ResearchWindow implements Screen
{
    Main main;
    Technology Technologies;
    Technology ExploreTech;
    DescribeTech describeTech;
    OrthographicCamera camera;
    SpriteBatch batch;

    TechTreeDrawer DrawerTree;
    Skin skin;
    Stage stage;
    TextButton ButtonExit;

    public Technology getExploreTech() {
        return ExploreTech;
    }

    public void setExploreTech(Technology exploreTech) {
        ExploreTech = exploreTech;
    }

    public Technology getTechnologies() {
        return Technologies;
    }

    public void setTechnologies(Technology technologies) {
        Technologies = technologies;
    }

    private void setListenerTech(final TextButton button, final Technology tech)
    {
        button.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                describeTech.setCurrentTech(tech);

            }
        });
    }
    private void RecursiveTechnology(Technology tech)
    {
        float height=100;
        float width=300;
        switch (tech.getId()) {
            case 1:
                setListenerTech(DrawerTree.add(tech,new Rectangle(0,Gdx.graphics.getHeight()/2,width,height)),  tech);
                break;
            case 2:
                setListenerTech(DrawerTree.add(tech,new Rectangle(width*2,Gdx.graphics.getHeight()/2-2*height,width,height)), tech);
                break;
            case 3:
                setListenerTech(DrawerTree.add(tech,new Rectangle(width*2,Gdx.graphics.getHeight()/2,width,height)),  tech);
                break;
            case 4:
                setListenerTech(DrawerTree.add(tech,new Rectangle(width*2,Gdx.graphics.getHeight()/2+2*height,width,height)),  tech);
                break;
            case 5:
                setListenerTech(DrawerTree.add(tech,new Rectangle(width*4,Gdx.graphics.getHeight()/2+2*height,width,height)),  tech);
                break;

        }

        Array.ArrayIterator<Technology> iterator = (Array.ArrayIterator<Technology>) tech.getNextTechnologies().iterator();
        while(iterator.hasNext())
        {
            Technology current=iterator.next();

            RecursiveTechnology(current);
        }
    }
    public ResearchWindow(OrthographicCamera camera, SpriteBatch batch, Skin skin, Stage stage, final Main main)
    {
        this.camera=camera;
        this.skin=skin;
        this.stage=stage;
        this.batch=batch;
        this.main=main;


        describeTech=new DescribeTech(skin,stage);
        describeTech.setVisible(false);
        describeTech.setColor(Color.PURPLE);
        describeTech.debug();
        describeTech.getB_explore().addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ExploreTech = describeTech.getCurrentTech();
                    ExploreTech.setConditionTech(ConditionTech.EXPLORE);
                }
            });
        DrawerTree=new TechTreeDrawer(batch, skin,stage);
        DrawerTree.setStage(stage);



        DrawerTree.hide();

        ButtonExit=new TextButton("X",skin);
        ButtonExit.setPosition(Gdx.graphics.getWidth()-50,Gdx.graphics.getHeight()-50);
        ButtonExit.setHeight(50);
        ButtonExit.setWidth(50);
        ButtonExit.getLabel().setFontScale((float) 2.5);
        ButtonExit.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                main.setScreen(main.getGameScreens().get("Field"));
            }
        });
        ButtonExit.setVisible(false);
        stage.addActor(ButtonExit);
        stage.addActor(describeTech);
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show()
    {
        RecursiveTechnology(Technologies);
        describeTech.setVisible(true);
        DrawerTree.show();
        ButtonExit.setVisible(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        DrawerTree.update();
        batch.end();
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide()
    {
        describeTech.setVisible(false);
        DrawerTree.hide();
        ButtonExit.setVisible(false);
    }

    @Override
    public void dispose() {

    }
}
