package responsipbo.model;

import responsipbo.contract.Attacker;
import responsipbo.contract.GameElement;

public class Mage extends Hero implements Attacker, GameElement {
    private int mana;

    public Mage(String id, String name, int hp, int attackPower, int defensePower, int mana) {
        super(id, name, hp, attackPower, defensePower);
        this.mana = mana;
    }

    public Mage(String id, String name) {
        this(id, name, 120, 32, 10, 60);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " melepaskan mantra sihir!");
    }

    @Override
    public void attackTarget(Hero target) {
        int damage = getAttackPower() + (mana / 10);
        System.out.println(getName() + " menyerang " + target.getName() + " dengan spell damage " + damage);
        target.kurangiHp(damage);
    }

    @Override
    public void activateSkill() {
        if (mana >= 10) {
            mana -= 10;
            System.out.println(getName() + " menggunakan skill: Fireball! Mana tersisa: " + mana);
        } else {
            System.out.println(getName() + " mana tidak cukup untuk menggunakan skill.");
        }
    }

    @Override
    public void kurangiHp(int damage) {
        int actualDamage = damage - getDefensePower();
        if (actualDamage < 0) {
            actualDamage = 0;
        }

        if (actualDamage > 0) {
            int updatedHp = Math.max(0, getHp() - actualDamage);
            setHp(updatedHp);
            System.out.println(getName() + " menerima " + actualDamage + " damage. HP sekarang: " + getHp());
        } else {
            System.out.println(getName() + " berhasil menghindari sebagian besar serangan!");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Mana: " + this.mana);
        System.out.println("Jenis: Mage");
    }
}
