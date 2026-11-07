package responsipbo.contract;

import responsipbo.model.Hero;

public interface Attacker {
    void attack();
    void attackTarget(Hero target);
}
