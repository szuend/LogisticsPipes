package logisticspipes.network.guis.module.inhand;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.gui.GuiSupplierPipe;
import logisticspipes.modules.ModuleActiveSupplier;
import logisticspipes.modules.abstractmodules.LogisticsModule;
import logisticspipes.network.abstractguis.GuiProvider;
import logisticspipes.network.abstractguis.ModuleInHandGuiProvider;
import logisticspipes.utils.gui.DummyContainer;
import logisticspipes.utils.gui.DummyModuleContainer;

public class ActiveSupplierInHand extends ModuleInHandGuiProvider {

    public ActiveSupplierInHand(int id) {
        super(id);
    }

    @Override
    public Object getClientGui(EntityPlayer player) {
        LogisticsModule module = getLogisticsModule(player);
        if (!(module instanceof ModuleActiveSupplier)) {
            return null;
        }
        return new GuiSupplierPipe(
                player.inventory,
                ((ModuleActiveSupplier) module).getDummyInventory(),
                (ModuleActiveSupplier) module,
                false,
                new int[9]);
    }

    @Override
    public DummyContainer getContainer(EntityPlayer player) {
        DummyModuleContainer dummy = new DummyModuleContainer(player, getInvSlot());
        if (!(dummy.getModule() instanceof ModuleActiveSupplier)) {
            return null;
        }
        dummy.setInventory(((ModuleActiveSupplier) dummy.getModule()).getDummyInventory());
        dummy.addNormalSlotsForPlayerInventory(8, 60);

        // Pipe slots
        for (int pipeSlot = 0; pipeSlot < 9; pipeSlot++) {
            dummy.addDummySlot(pipeSlot, 8 + pipeSlot * 18, 18);
        }
        return dummy;
    }

    @Override
    public GuiProvider template() {
        return new ActiveSupplierInHand(getId());
    }
}
