package wastedgames.game.map;

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
    private int health;
    private int power;
    private int speed;
    private int price;
    private int level;
    private int countYears;
    private int armor;

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

    public void setHealth(int health) {
        this.health = health;
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

    public Unit(UnitType unitType, int health, int power, int speed, int price, int armor, int level, int countYears, String  name) {
        this.unitType = unitType;
        this.health = health;
        this.power = power;
        this.speed = speed;
        this.price = price;
        this.level = level;
        this.armor = armor;
        this.countYears=countYears;
        this.name=name;
    }

    public int getHealth() {
        return health;
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
        this.health -= damage / armor;
    }

    protected void getHeal(int heal) {
        this.health += heal;
    }
}
