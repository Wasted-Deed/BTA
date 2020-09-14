package wastedgames.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import wastedgames.game.Main;
import wastedgames.game.Tech.Technology;
import wastedgames.game.card.Card;
import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Map;
import wastedgames.game.map.Player;
import wastedgames.game.map.Test;

public class GameField implements Screen
{
    Main main;
    Map map;
    OrthographicCamera camera;
    SpriteBatch batch;
    Card event;
    Array<TextButton> ButtonsUI;
    Stage stage;
    Skin skin;
    Player player=new Player();
    private int year;



    public void showEvent(String text)
    {
        event =new Card("Event",skin );
        event.addButton("Yes");
        event.text(text);
        event.show(stage);
        event.setScale(3);
    }

    public GameField(Skin skin, SpriteBatch batch, OrthographicCamera camera, final Main game, Stage stage)
    {
        this.main=game;
        this.batch=batch;
        this.camera=camera;
        this.skin=skin;
        this.stage=stage;
        ButtonsUI=new Array<>();
        map = new Map();
        map.fillMap();
        Technology tech1=new Technology("Technology №1",1);
        tech1.setDescription("Technology №1 costs 1 ");
        tech1.setCanExplore(true);
        tech1.setCost(1);
        Technology tech2=new Technology("Technology №2.1",2);
        tech2.setDescription("Technology №2.1 costs 1");
        tech2.setCost(1);
        Technology tech3=new Technology("Technology №2.2",3);
        tech3.setDescription("Technology №2.2 costs 1");
        tech3.setCost(2);
        Technology tech4=new Technology("Technology №2.3",4);
        tech4.setDescription("Technology №2.3 costs 1");
        tech4.setCost(1);
        Technology tech5=new Technology("Technology №3",5);
        tech5.setDescription("Technology №3 costs 1");
        tech5.setCost(1);
        tech1.getNextTechnologies().add(tech2);
        tech1.getNextTechnologies().add(tech3);
        tech1.getNextTechnologies().add(tech4);
        tech4.getNextTechnologies().add(tech5);
        player.setTechnologies(tech1);


        final TextButton BNextMove=new TextButton(String.valueOf(year),skin);
        BNextMove.setHeight(50);
        BNextMove.setWidth(300);
        BNextMove.setPosition(Gdx.graphics.getWidth()-BNextMove.getWidth(),0);
        BNextMove.getLabel().setFontScale((float) 2.5);
        BNextMove.addListener(new ChangeListener()
            {
                @Override
                public void changed(ChangeEvent event, Actor actor)
                {
                    /*Управление передается AI
                    ********
                    */
                    ResearchWindow win= (ResearchWindow) main.getGameScreens().get("Technologies");
                    player.setCurrentExlpore(win.getExploreTech());
                    player.getTechnologies().update(player.getCurrentExlpore());
                    year++;
                    BNextMove.setText("Year: "+year);
                    showEvent("Goodbye "+year);
                }
            });

        stage.addActor(BNextMove);
        TextButton BTechTree=new TextButton("Technologies",skin);
        BTechTree.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-50);
        BTechTree.setHeight(50);
        BTechTree.setWidth(300);
        BTechTree.getLabel().setFontScale((float) 2.5);
        BTechTree.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                ResearchWindow win= (ResearchWindow) main.getGameScreens().get("Technologies");
                win.setTechnologies(player.getTechnologies());
                win.setExploreTech(player.getCurrentExlpore());
                main.setScreen(win);

            }
        });
        showEvent("Hello!");
        stage.addActor(BTechTree);


        ButtonsUI.add(BTechTree);

        Gdx.input.setInputProcessor(stage);

    }



    @Override
    public void show()
    {
        for(int i=0;i<ButtonsUI.size;i++)
        {
            TextButton button=ButtonsUI.get(0);
            button.setVisible(true);
        }

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.begin();
        map.draw(batch);
        for (int i=0;i<ButtonsUI.size;i++)
        {
            TextButton currentButton=ButtonsUI.get(i);
            currentButton.draw(batch,1);
        }
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
       for(int i=0;i<ButtonsUI.size;i++)
       {
           TextButton button=ButtonsUI.get(0);
           button.setVisible(false);
       }
    }

    @Override
    public void dispose() {
        batch.dispose();
        ResourceLoader.dispose();
    }
}
