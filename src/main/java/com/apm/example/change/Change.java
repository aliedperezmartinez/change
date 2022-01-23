package com.apm.example.change;

public class Change {

    static final int[] COINS = {200, 100, 50, 20, 10, 5, 2, 1};
    private final int[] availableCoins = new int[8];

    public Change(int[] availableCoins) {
        System.arraycopy(availableCoins, 0, this.availableCoins, 0, COINS.length);
    }

    public Change() {
        this(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE});
    }

    public int[] getChange(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot change a negative amount");
        }
        final int[] availableCoins = new int[8];
        // Let's ue a copy here, in case e don't have enough coins we can abort the operation
        System.arraycopy(this.availableCoins, 0, availableCoins, 0, COINS.length);
        final int[] result = getChange(availableCoins, amount, new int[COINS.length], 0);
        System.arraycopy(availableCoins, 0, this.availableCoins, 0, COINS.length);
        return result;
    }

    private static int[] getChange(int[] availableCoins, final int amount, int[] result, int i) {
        if(i >= COINS.length) {
            if(amount > 0)
                throw new IllegalStateException("Not enough coins to provide change");
            return result;
        }
        if(amount == 0)
            return result;
        final int coin = COINS[i];
        if(availableCoins[i] > 0){
            result[i] = amount/coin;
            availableCoins[i]--;
            return getChange(availableCoins, amount%coin, result, ++i);
        }
        return getChange(availableCoins, amount, result, ++i);
    }

}
