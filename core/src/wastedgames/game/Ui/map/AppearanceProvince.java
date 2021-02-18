package wastedgames.game.Ui.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.ObjectMap;


import wastedgames.game.maintenance.ResourceLoader;
import wastedgames.game.map.Formation;

public class AppearanceProvince extends Polygon
{
    PolygonRegion polygonRegion;
    private ObjectMap<UiFormation, Formation> units=new ObjectMap<>();


    public AppearanceProvince(ObjectMap<UiFormation, Formation> units) {
        this.units = units;
    }

    public AppearanceProvince(float[] vertices,Texture texture)
    {
        super(vertices);
        this.units = units;
        TextureRegion textureRegion=new TextureRegion(texture);
        short triangles[] = new EarClippingTriangulator()
                .computeTriangles(getVertices())
                .toArray();
         polygonRegion=new PolygonRegion(textureRegion,getVertices(),triangles);
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch)
    {
        polygonSpriteBatch.begin();
        polygonSpriteBatch.draw(polygonRegion,getX(),getY());
        for (UiFormation image:units.keys())
            image.draw(polygonSpriteBatch);
        polygonSpriteBatch.end();

    }
    public  Formation  CheckClickUnit(Vector2 mouse)
    {
        for (UiFormation uiFormation:units.keys())
        {
            if (uiFormation.contain(mouse))
            {
                uiFormation.setPressed(true);
                return units.get(uiFormation);
            }
        }
        return null;
    }
    public void addFormation(UiFormation b_unit, Formation formation)
    {
        units.put(b_unit,formation);
        b_unit.setImgNotPressed(new com.badlogic.gdx.scenes.scene2d.ui.Image(new SpriteDrawable(ResourceLoader.getSprite(wastedgames.game.maintenance.Image.FORMATION))));
        b_unit.setImgPressed(new com.badlogic.gdx.scenes.scene2d.ui.Image(new SpriteDrawable(ResourceLoader.getSprite(wastedgames.game.maintenance.Image.SELECT_FORMATION))));
    }
    public void removeFormation(Formation formation)
    {
        units.remove(formation.getAppearance());
    }
    public ObjectMap<UiFormation, Formation> getUnits() {
        return units;
    }

    public void setUnits(ObjectMap<UiFormation, Formation> units) {
        this.units = units;
    }
}
