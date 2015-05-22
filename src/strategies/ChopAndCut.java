package strategies;


import data.Constants;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class ChopAndCut implements Strategy {
    @Override
    public boolean activate() {
        return !Inventory.isFull();
    }

    @Override
    public void execute() {
        SceneObject[] trees = SceneObjects.getNearest(Constants.TREE_ID);
        if (trees != null && trees.length > 0 && trees[0].distanceTo() > 0) {
            trees[0].interact(SceneObjects.Option.CHOP_DOWN);
            final int previous = Inventory.getCount();
            Time.sleep(() -> Inventory.getCount() != previous, 2000);
        }
    }
}