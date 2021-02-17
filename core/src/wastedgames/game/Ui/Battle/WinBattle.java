package wastedgames.game.Ui;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class WinBattle extends Group
{
    private Label name;
    private TextButton Attack;
    private TextButton Back;
    private Group ArmyOne;
    private Group ArmyTwo;
    private ProgressBar BalancePower;

    public  WinBattle(Skin skin)
    {
        Attack=new TextButton("Атаковать",skin);
        Back=new TextButton("Отступить",skin);

    }

}
