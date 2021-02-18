package wastedgames.game.Ui.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class UiFormation extends Rectangle
{
    private   boolean pressed;
    Image imgPressed;
    Image imgNotPressed;
    public boolean contain(Vector2 mouse)
     {
         if (mouse==null) return false;
           else if(mouse.x>getX()&&mouse.x<(getX()+getWidth())&&(mouse.y>getY()&&mouse.y<getY()+getHeight())) return  true;
                  else return false;
     }
     public void draw(Batch batch)
     {
         if (pressed)
             imgPressed.draw(batch,1);
         else imgNotPressed.draw(batch,1);
     }

    public Image getImgPressed() {
        return imgPressed;
    }

    public void setImgPressed(Image imgPressed)
    {
        imgPressed.setSize(getWidth(),getHeight());
        imgPressed.setPosition(getX(),getY());
        this.imgPressed = imgPressed;
    }

    public Image getImgNotPressed() {

        return imgNotPressed;

    }

    public void setImgNotPressed(Image imgNotPressed) {
        imgNotPressed.setSize(getWidth(),getHeight());
        imgNotPressed.setPosition(getX(),getY());
        this.imgNotPressed = imgNotPressed;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
