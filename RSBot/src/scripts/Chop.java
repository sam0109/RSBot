package scripts;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.ClientContext;

public abstract class Chop extends Task<ClientContext> {
    private int[] treeIds = {38616, 38627, 58006};

    public Chop(ClientContext ctx)
    {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28 &&
                !ctx.objects.select().id(treeIds).isEmpty() &&
                ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.nearest().poll();
        if(tree.inViewport()) {
            tree.interact("Chop");
        } else {
            ctx.movement.step(tree);
            ctx.camera.turnTo(tree);
        }
    }
}