package logisticspipes.network.packets.hud;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.interfaces.IModuleWatchReciver;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.network.abstractpackets.ModuleCoordinatesPacket;

public class HUDStopModuleWatchingPacket extends ModuleCoordinatesPacket {

    public HUDStopModuleWatchingPacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new HUDStopModuleWatchingPacket(getId());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        IModuleWatchReciver handler = this.getLogisticsModule(player, IModuleWatchReciver.class);
        if (handler == null) {
            return;
        }
        handler.stopWatching(player);
    }
}
