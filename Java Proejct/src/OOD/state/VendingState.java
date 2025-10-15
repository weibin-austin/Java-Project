package OOD.state;

import OOD.entiey.Coin;
import OOD.entiey.Item;
import OOD.machine.VendingMachine;

public interface VendingState {
    void insertCoin(VendingMachine machine, Coin coin);
    void selectItem(VendingMachine machine, Item item);
    void dispense(VendingMachine machine);
    void refund(VendingMachine machine);
}
