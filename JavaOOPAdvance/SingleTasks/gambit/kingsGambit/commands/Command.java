package kingsGambit.commands;

import kingsGambit.contracts.Executable;
import kingsGambit.contracts.Subject;

public abstract class Command implements Executable {

    private Subject king;
    private String[] data;

    protected Command(Subject king) {
        this.king = king;
    }

    protected Subject getKing() {
        return this.king;
    }

    protected String[] getData() {
        return this.data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
