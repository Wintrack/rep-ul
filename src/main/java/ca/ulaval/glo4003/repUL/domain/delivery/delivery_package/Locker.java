package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.exception.LockerAlreadyTaken;

public class Locker {
    private boolean isTaken;

    public Locker() {
        isTaken = false;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void takeLocker() {
        if (isTaken) {
            throw new LockerAlreadyTaken();
        }
        isTaken = true;
    }
}
