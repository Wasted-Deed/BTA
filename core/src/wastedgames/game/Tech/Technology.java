package wastedgames.game.Tech;

import com.badlogic.gdx.utils.Array;

public class Technology
{
    String name;
    private Array<Technology> nextTechnologies;

    public String getName() {
        return name;
    }

    public Technology(String name)
    {
        this.name = name;
        nextTechnologies=new Array<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Array<Technology> getNextTechnologies() {
        return nextTechnologies;
    }

    public void setNextTechnologies(Array<Technology> nextTechnologies) {
        this.nextTechnologies = nextTechnologies;
    }

}
