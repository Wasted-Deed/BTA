package wastedgames.game.map;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public abstract class Unit implements Cloneable
{

    @Override
    public Unit clone()
    {
        try {
            return (Unit) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UnitType unitType;
    private String name;
    private int current_health;
    private int max_health;



    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    private int power;
    private int speed;
    private int price;
    private int level;
    private int countYears;
    private int armor;
    private int morale;

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public enum UnitType {
        INFANTRY, CAVALRY
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent_health(int current_health)
    {
        this.morale-=  (this.current_health-current_health);
        this.current_health = current_health;

    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCountYears() {
        return countYears;
    }

    public void setCountYears(int countYears) {
        this.countYears = countYears;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public Unit(UnitType unitType, int current_health,int max_health, int power, int speed, int price, int armor, int level, int countYears, String  name,int morale) {
        this.unitType = unitType;
        this.current_health = current_health;
        this.max_health=max_health;
        this.power = power;
        this.speed = speed;
        this.price = price;
        this.level = level;
        this.armor = armor;
        this.countYears=countYears;
        this.name=name;
        this.morale=morale;
    }

    public int getCurrent_health() {
        return current_health;
    }

    public int getPower() {
        return power;
    }

    public int getPrice() {
        return price;
    }

    public int getSpeed() {
        return speed;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public int getLevel() {
        return level;
    }

    public int getArmor() {
        return armor;
    }

    protected void getDamage(int damage) {
        this.current_health -= damage / armor;
    }

    protected void getHeal(int heal) {
        this.current_health += heal;
    }
}
