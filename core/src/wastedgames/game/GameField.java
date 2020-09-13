package wastedgames.game;

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

import wastedgames.game.card.Card;
import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Map;

public class GameField implements Screen
{
    Main main;
    Map map;
    OrthographicCamera camera;
    SpriteBatch batch;
    Card event;
    Array<TextButton> ButtonsUI;
    Stage stage;
    Skin skin=new Skin();




    GameField(Skin skin, SpriteBatch batch, OrthographicCamera camera, final Main game,Stage stage)
    {
        this.main=game;
        this.batch=batch;
        this.camera=camera;
        this.skin=skin;
        this.stage=stage;
        ButtonsUI=new Array<>();
        map = new Map();
        map.fillMap();

        event =new Card("Event",skin );
        event.addButton("Yes");
        event.show(stage);
        event.setScale(3);
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
                main.setScreen(main.getGameScreens().get("Technologies"));
            }
        });
        stage.addActor(BTechTree);
        stage.addActor(event);
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
