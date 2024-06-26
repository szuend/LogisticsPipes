package logisticspipes.network.packets.pipe;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.modules.ModuleCrafter;
import logisticspipes.network.abstractpackets.IntegerModuleCoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;

public class FluidCraftingPipeAdvancedSatelliteNextPacket extends IntegerModuleCoordinatesPacket {

    public FluidCraftingPipeAdvancedSatelliteNextPacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new FluidCraftingPipeAdvancedSatelliteNextPacket(getId());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        ModuleCrafter module = this.getLogisticsModule(player, ModuleCrafter.class);
        if (module == null) {
            return;
        }
        module.setNextFluidSatellite(player, getInteger());
    }
}
