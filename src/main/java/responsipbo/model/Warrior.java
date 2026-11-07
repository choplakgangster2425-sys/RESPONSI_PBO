package responsipbo.model;

import responsipbo.contract.Attacker;
import responsipbo.contract.Defender;

public class Warrior extends Hero implements Attacker, Defender {
    private int armor;

    public Warrior(String id, String name, int hp, int attackPower, int defensePower, int armor) {
        super(id, name, hp, attackPower, defensePower);
        this.armor = armor;
    }

    public Warrior(String id, String name) {
        this(id, name, 170, 28, 18, 12);
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " melakukan serangan pedang!");
    }

    @Override
    public void attackTarget(Hero target) {
        int damage = getAttackPower() + 8;
        System.out.println(getName() + " menyerang " + target.getName() + " dengan damage " + damage);
        target.kurangiHp(damage);
    }

    @Override
    public void defend() {
        System.out.println(getName() + " mengangkat perisai untuk bertahan.");
    }

    @Override
    public int reduceDamage(int incomingDamage) {
        int reducedDamage = incomingDamage - (getDefensePower() + armor);
        if (reducedDamage < 0) {
            reducedDamage = 0;
        }
        return reducedDamage;
    }

    @Override
    public void kurangiHp(int damage) {
        int actualDamage = reduceDamage(damage);
        if (actualDamage > 0) {
            int updatedHp = Math.max(0, getHp() - actualDamage);
            setHp(updatedHp);
            System.out.println(getName() + " menerima " + actualDamage + " damage. HP sekarang: " + getHp());
        } else {
            System.out.println(getName() + " berhasil menahan seluruh serangan!");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Armor: " + this.armor);
        System.out.println("Jenis: Warrior");
    }
}
