package wastedgames.game.Tech;

import com.badlogic.gdx.utils.Array;

public class Technology
{
    String name;
    String description;
    private Array<Technology> nextTechnologies;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
