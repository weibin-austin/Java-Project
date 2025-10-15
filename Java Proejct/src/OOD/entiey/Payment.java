package OOD.entiey;

import java.util.ArrayList;
import java.util.List;

public class Payment {
    private int currentBalance = 0;
    private final List<Coin> insertedCoins = new ArrayList<>();

    public void insertCoin(Coin coin) {
        insertedCoins.add(coin);
        currentBalance += coin.getValue();
    }
    
    public int getBalance(){
        return currentBalance;
    }

    public int refund(){
        int amount = currentBalance;
        currentBalance = 0;
        insertedCoins.clear();
        return amount;
    }

    public void deduct(int amount){
        if(amount > currentBalance) throw new IllegalArgumentException("Insufficient balance");
        currentBalance -= amount;
    }
}
