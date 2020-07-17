package wastedgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Map;

public class GameField extends ApplicationAdapter {
    Map map;
    OrthographicCamera camera;
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        ResourceLoader.loadResources();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        map = new Map();
        map.fillMap();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        map.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        ResourceLoader.dispose();
    }
}
