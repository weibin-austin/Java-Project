package OOD.state;

import OOD.entiey.Coin;
import OOD.entiey.Item;
import OOD.machine.VendingMachine;

public class HasMoneyState implements VendingState{

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertCoin'");
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectItem'");
    }

    @Override
    public void dispense(VendingMachine machine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispense'");
    }

    @Override
    public void refund(VendingMachine machine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refund'");
    }

}
