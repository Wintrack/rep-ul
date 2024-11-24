package ca.ulaval.glo4003.repUL.domain.delivery.delivery_package;

import ca.ulaval.glo4003.repUL.domain.delivery.delivery_package.exception.LockerAlreadyTaken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LockerTest {
    @Test
    void givenALockerNotTaken_whenTakeLocker_thenIsTakenIsTrue() {
        // GIVEN
        Locker locker = new Locker();

        // WHEN
        locker.takeLocker();

        // THEN
        assertThat(locker.isTaken()).isTrue();
    }

    @Test
    void givenALockerTaken_whenTakeLocker_thenThrowLockerAlreadyTaken() {
        // GIVEN
        Locker locker = new Locker();

        locker.takeLocker();

        // WHEN
        assertThatThrownBy(locker::takeLocker)
                .isInstanceOf(LockerAlreadyTaken.class)
                .hasMessageContaining("Locker already taken");
    }
}