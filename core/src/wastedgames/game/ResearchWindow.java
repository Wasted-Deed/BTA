package wastedgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.soap.Text;

import wastedgames.game.Tech.TechTreeDrawer;
import wastedgames.game.Tech.Technology;


public class ResearchWindow implements Screen
{
    Main main;
    Technology Technologies;
    OrthographicCamera camera;
    SpriteBatch batch;
    TechTreeDrawer DrawerTree;
    Skin skin;
    Stage stage;
    TextButton ButtonExit;

    public ResearchWindow(OrthographicCamera camera, SpriteBatch batch, Skin skin, Stage stage, final Main main)
    {
        this.camera=camera;
        this.skin=skin;
        this.stage=stage;
        Gdx.input.setInputProcessor(stage);
        this.main=main;
        Technology tech1=new Technology("Technology №1");
        Technology tech2=new Technology("Technology №2.1");
        Technology tech3=new Technology("Technology №2.2");
        Technology tech4=new Technology("Technology №2.3");
        Technology tech5=new Technology("Technology №3");
        Technologies=tech1;
        tech1.getNextTechnologies().add(tech2);
        tech1.getNextTechnologies().add(tech3);
        tech1.getNextTechnologies().add(tech4);
        tech4.getNextTechnologies().add(tech5);
        Gdx.input.setInputProcessor(stage);
        float height=100;
        float width=300;
        DrawerTree=new TechTreeDrawer(batch, skin);
        DrawerTree.add(tech1.getName(),new Rectangle(0,Gdx.graphics.getHeight()/2,width,height));
        DrawerTree.add(tech2.getName(),new Rectangle(width*2,Gdx.graphics.getHeight()/2-2*height,width,height));
        DrawerTree.add(tech3.getName(),new Rectangle(width*2,Gdx.graphics.getHeight()/2,width,height));
        DrawerTree.add(tech4.getName(),new Rectangle(width*2,Gdx.graphics.getHeight()/2+2*height,width,height));
        DrawerTree.add(tech5.getName(),new Rectangle(width*4,Gdx.graphics.getHeight()/2+2*height,width,height));
        DrawerTree.setStage(stage);
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


        this.batch=batch;

    }
    @Override
    public void show()
    {
        DrawerTree.show();
        ButtonExit.setVisible(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        DrawerTree.draw(Technologies);
        batch.end();
        stage.act(100);
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
        DrawerTree.hide();
        ButtonExit.setVisible(false);
    }

    @Override
    public void dispose() {

    }
}
