package logisticspipes.network.packets.block;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.blocks.LogisticsSecurityTileEntity;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.network.abstractpackets.NBTCoordinatesPacket;

public class SaveSecurityPlayerPacket extends NBTCoordinatesPacket {

    public SaveSecurityPlayerPacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new SaveSecurityPlayerPacket(getId());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        LogisticsSecurityTileEntity tile = this.getTile(player.worldObj, LogisticsSecurityTileEntity.class);
        if (tile != null) {
            tile.saveNewSecuritySettings(getTag());
        }
    }
}
