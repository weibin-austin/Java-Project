package OOD.main;

import OOD.entiey.Coin;
import OOD.entiey.Item;
import OOD.machine.VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Item coke = new Item("Coke", 125);
        machine.getInventory().addItem(coke, 5);

        machine.insertCoin(Coin.DOLLAR);
        machine.insertCoin(Coin.QUARTER);
        machine.selectItem(coke);
        machine.dispense();
        System.err.println("Finished");
    }
}
