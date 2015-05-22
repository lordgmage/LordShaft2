package strategies;


import data.Constants;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;

public class BankingShaft implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(Constants.LOGS) <= 0 && !Bank.isOpen() && Players.getMyPlayer().getAnimation() <= 1;
    }

    @Override
    public void execute() {

        Bank.open();
        Time.sleep(() -> Bank.isOpen(), 2500);
        Bank.depositAllExcept(Constants.KNIFE);
        Time.sleep(() -> Inventory.getCount() == 1, 2500);
        Bank.withdraw(Constants.LOGS, 27, 500);
        Time.sleep(() -> {
            return Inventory.isFull();
        }, 2500);
        Bank.close();
    }
}