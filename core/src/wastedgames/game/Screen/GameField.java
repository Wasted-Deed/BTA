package wastedgames.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import wastedgames.game.Main;
import wastedgames.game.Tech.Technology;
import wastedgames.game.Ui.Card;
import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Cavalry;
import wastedgames.game.map.Formation;
import wastedgames.game.map.Infantry;
import wastedgames.game.map.Map;
import wastedgames.game.map.Player;

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
    Player player2=new Player();
    private int year;



    public void showEvent(String text)
    {
        event =new Card("Event",skin );
        event.addButton("Yes");
        event.text(text);
        event.show(stage);
        event.setScale(3);
    }

    public Map getMap() {
        return map;
    }

    public void initTechTree()
    {
        Technology tech1=new Technology("Basic infantry",1);
        tech1.getOpenedUnit().add(new Infantry(1));
        tech1.setDescription("Costs 1 years.You will unlock the infantry ");
        tech1.setCanExplore(true);
        tech1.setCost(1);
        Technology tech2=new Technology("Basic cavalry",2);
        tech2.getOpenedUnit().add(new Cavalry(1));
        tech2.setDescription("Costs 1 years.You will unlock the cavalry");
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
        player.setAllTechnologies(tech1);
    }
    public GameField(Skin skin, SpriteBatch batch, OrthographicCamera camera, final Main game, Stage stage)
    {
        this.main=game;
        this.batch=batch;
        this.camera=camera;
        this.skin=skin;
        this.stage=stage;
        ButtonsUI=new Array<>();
        initTechTree();



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

                    ResearchWindow win= (ResearchWindow) main.getGameScreens().get("Technologies");
                    player.setCurrentExlpore(win.getExploreTech());
                    player.getAllTechnologies().update(player.getCurrentExlpore());

                    player.update();
                    map.nextMove(stage);
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
                win.setTechnologies(player.getAllTechnologies());
                win.setExploreTech(player.getCurrentExlpore());

                main.setScreen(win);

            }
        });

        map = new Map(skin);
        map.setBatch(batch);
        map.setHuman(player);
      //  map.setPosition(0,50);
       // map.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-100);
        map.fillMap();

        map.getProvinces().get(0).setOwner(player);
        map.getProvinces().get(1).setOwner(player);
        map.getProvinces().get(2).setOwner(player2);

        Formation enemy=new Formation();
        enemy.setOwner(player2);
        enemy.setLocation(map.getProvinces().get(2));
        enemy.getUnits().add(new Infantry(3));


        map.getProvinces().get(2).addFormation(enemy);

        //stage.addActor(map);
        stage.addActor(BTechTree);
        showEvent("Hello!");
        ButtonsUI.add(BTechTree);

        Gdx.input.setInputProcessor(stage);
        map.setVisible(true);
    }



    @Override
    public void show()
    {
        for(int i=0;i<ButtonsUI.size;i++)
        {
            TextButton button=ButtonsUI.get(0);
            button.setVisible(true);
        }
        map.setVisible(true);

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        map.update(stage,skin);

        batch.begin();
        map.draw();
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

        map.setVisible(false);
    }

    @Override
    public void dispose() {
        batch.dispose();
        ResourceLoader.dispose();
    }
}
