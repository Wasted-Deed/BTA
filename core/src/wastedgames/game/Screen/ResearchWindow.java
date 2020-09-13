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

import wastedgames.game.Main;
import wastedgames.game.Tech.DescribeTech;
import wastedgames.game.Tech.TechTreeDrawer;
import wastedgames.game.Tech.Technology;


public class ResearchWindow implements Screen
{
    Main main;
    Technology Technologies;
    DescribeTech describeTech;
    OrthographicCamera camera;
    SpriteBatch batch;
    TechTreeDrawer DrawerTree;
    Skin skin;
    Stage stage;
    TextButton ButtonExit;
    public void setListenerTech(final TextButton button, final Technology tech)
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
    public ResearchWindow(OrthographicCamera camera, SpriteBatch batch, Skin skin, Stage stage, final Main main)
    {
        this.camera=camera;
        this.skin=skin;
        this.stage=stage;
        this.batch=batch;
        this.main=main;
        final Technology tech1=new Technology("Technology №1");
        tech1.setDescription("Description of Technology №1 ");
        Technology tech2=new Technology("Technology №2.1");
        tech2.setDescription("Description of Technology №2.1");
        Technology tech3=new Technology("Technology №2.2");
        tech3.setDescription("Description of Technology №2.2");
        Technology tech4=new Technology("Technology №2.3");
        tech4.setDescription("Description of Technology №2.3");
        Technology tech5=new Technology("Technology №3");
        tech4.setDescription("Description of Technology №3 is");
        Technologies=tech1;
        tech1.getNextTechnologies().add(tech2);
        tech1.getNextTechnologies().add(tech3);
        tech1.getNextTechnologies().add(tech4);
        tech4.getNextTechnologies().add(tech5);

        describeTech=new DescribeTech(skin,stage);
        describeTech.setVisible(false);
        describeTech.setColor(Color.PURPLE);
        describeTech.debug();
        DrawerTree=new TechTreeDrawer(batch, skin,stage);
        DrawerTree.setStage(stage);
        float height=100;
        float width=300;
        //Установка слушателя на нажатие кнопок технологий
        setListenerTech(DrawerTree.add(tech1,new Rectangle(0,Gdx.graphics.getHeight()/2,width,height)),  tech2);
        setListenerTech(DrawerTree.add(tech2,new Rectangle(width*2,Gdx.graphics.getHeight()/2-2*height,width,height)),  tech2);
        setListenerTech(DrawerTree.add(tech3,new Rectangle(width*2,Gdx.graphics.getHeight()/2,width,height)),  tech3);
        setListenerTech(DrawerTree.add(tech4,new Rectangle(width*2,Gdx.graphics.getHeight()/2+2*height,width,height)),  tech4);
        setListenerTech(DrawerTree.add(tech5,new Rectangle(width*4,Gdx.graphics.getHeight()/2+2*height,width,height)),  tech4);
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
