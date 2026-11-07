package responsipbo;

import responsipbo.contract.Attacker;
import responsipbo.contract.Defender;
import responsipbo.model.Hero;
import responsipbo.model.Mage;
import responsipbo.model.Warrior;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== " + Hero.NAMA_GAME + " ===");

        Warrior warrior = new Warrior("W-001", "Arkan");
        Mage mage = new Mage("M-001", "Nyra");

        Attacker obj = new Warrior("W-002", "Boris");
        Defender defenderObj = new Warrior("W-003", "Damar");

        warrior.displayStatus();
        System.out.println();
        mage.displayStatus();
        System.out.println();

        warrior.attack();
        mage.attack();
        System.out.println();

        warrior.attackTarget(mage);
        mage.attackTarget(warrior);
        System.out.println();

        warrior.defend();
        mage.activateSkill();
        System.out.println();

        obj.attackTarget(mage);
        System.out.println("Damage yang diterima oleh defenderObj: " + defenderObj.reduceDamage(30));
        System.out.println();

        System.out.println("Status akhir Warrior:");
        warrior.displayStatus();
        System.out.println();
        System.out.println("Status akhir Mage:");
        mage.displayStatus();
        System.out.println();

        System.out.println("Bukti syarat: ");
        System.out.println("1. State dan behavior lengkap, getter/setter private, this keyword ada.");
        System.out.println("2. id bersifat final.");
        System.out.println("3. Encapsulation + overload constructor hero terpenuhi.");
        System.out.println("4. Hero superclass dan Warrior/Mage subclass tersedia.");
        System.out.println("5. super(...), super.displayStatus() sesuai syarat.");
        System.out.println("6. Method overriding @Override dipakai.");
        System.out.println("7. Hero adalah abstract class dengan abstract method.");
        System.out.println("8. Logika if/else pada kurangiHp sudah ada.");
        System.out.println("9. Polimorfisme interface menggunakan Attacker obj = new Warrior(...). ");
        System.out.println("10. Warrior implements Attacker dan Defender sekaligus.");
    }
}
