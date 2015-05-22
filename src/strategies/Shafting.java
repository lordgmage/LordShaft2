package strategies;

import data.Constants;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;

public class Shafting implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.getCount(Constants.LOGS) > 25 && !Bank.isOpen() && Players.getMyPlayer().getAnimation() <= 1 && Inventory.getCount(Constants.KNIFE) == 1;
    }

    @Override
    public void execute() {
        Item KNIFE_ID = Inventory.getItem(Constants.KNIFE);
        Item LOGS_ID = Inventory.getItem(Constants.LOGS);
        KNIFE_ID.interact(Items.Option.USE_WITH);
        Time.sleep(500);
        LOGS_ID.interact(Items.Option.USE);
        Time.sleep(() -> Game.getOpenBackDialogId() == 8880, 2500);
        Menu.sendAction(315, 0, 54, 8890);
        Time.sleep(() -> Inventory.getCount() == 2, 60000);
    }
}