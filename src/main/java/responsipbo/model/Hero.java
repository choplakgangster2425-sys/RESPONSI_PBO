package responsipbo.model;

public abstract class Hero {
    public static final String NAMA_GAME = "RESPONSI PBO Game";

    private final String id;
    private String name;
    private int hp;
    private int attackPower;
    private int defensePower;
    private int level;

    public Hero(String id, String name, int hp, int attackPower, int defensePower) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.level = 1;
    }

    public Hero(String id, String name) {
        this(id, name, 100, 15, 10);
    }

    public Hero() {
        this("H-000", "Hero Tanpa Nama");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void levelUp() {
        this.level += 1;
    }

    public void displayStatus() {
        System.out.println("ID: " + this.id);
        System.out.println("Nama: " + this.name);
        System.out.println("HP: " + this.hp);
        System.out.println("Attack: " + this.attackPower);
        System.out.println("Defense: " + this.defensePower);
        System.out.println("Level: " + this.level);
    }

    public abstract void kurangiHp(int damage);
}
