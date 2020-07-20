package wastedgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import wastedgames.game.card.Card;
import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Map;

public class GameField extends ApplicationAdapter {
    Map map;
    OrthographicCamera camera;
    SpriteBatch batch;
    Card card;
    Stage stage;
    @Override
    public void create() {

        ResourceLoader.loadResources();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        camera.setToOrtho(false, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        map = new Map();
        map.fillMap();
        Viewport view = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        view.setCamera(camera);
        stage=new Stage(view,batch);
        Skin skin=new Skin();
        FileHandle fileHandle = Gdx.files.internal("uiskin.json");
        FileHandle atlasFile = fileHandle.sibling("uiskin.atlas");
        skin.addRegions(new TextureAtlas(atlasFile));
        skin.load(Gdx.files.internal("uiskin.json"));
        card=new Card("Event",skin );
        Gdx.input.setInputProcessor(stage);
        card.show(stage);
       card.setScale(3);


    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        batch.begin();
        map.draw(batch);
        batch.end();

       // stage.act(100);
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        ResourceLoader.dispose();
    }
}
