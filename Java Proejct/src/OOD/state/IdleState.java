package OOD.state;

import OOD.entiey.Coin;
import OOD.entiey.Item;
import OOD.machine.VendingMachine;

public class IdleState implements VendingState{

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {

    }

    @Override
    public void dispense(VendingMachine machine) {

    }

    @Override
    public void refund(VendingMachine machine){}

}
