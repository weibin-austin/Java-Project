package OOD.machine;

import OOD.entiey.Coin;
import OOD.entiey.Inventory;
import OOD.entiey.Item;
import OOD.entiey.Payment;
import OOD.state.IdleState;
import OOD.state.VendingState;

public class VendingMachine {
    private VendingState state;
    private final Inventory inventory;
    private final Payment payment;

    public VendingMachine(){
        this.state = new IdleState();
        this.inventory = new Inventory();
        this.payment = new Payment();
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public VendingState getState() {
        return state;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Payment getPayment(){
        return payment;
    }

    public void insertCoin(Coin coin) {
        state.insertCoin(this, coin);
    }

    public void selectItem(Item item) {
        state.selectItem(this, item);
    }

    public void dispense(){
        state.dispense(this);
    }

    public void refund(){
        state.refund(this);
    }
}
