package logisticspipes.network.packets.block;

import java.io.IOException;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.blocks.LogisticsSecurityTileEntity;
import logisticspipes.network.LPDataInputStream;
import logisticspipes.network.LPDataOutputStream;
import logisticspipes.network.abstractpackets.CoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SecurityStationId extends CoordinatesPacket {

    @Getter
    @Setter
    private UUID uuid;

    public SecurityStationId(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new SecurityStationId(getId());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        LogisticsSecurityTileEntity tile = this.getTile(player.worldObj, LogisticsSecurityTileEntity.class);
        if (tile != null) {
            tile.setClientUUID(getUuid());
        }
    }

    @Override
    public void writeData(LPDataOutputStream data) throws IOException {
        super.writeData(data);
        data.writeLong(uuid.getMostSignificantBits());
        data.writeLong(uuid.getLeastSignificantBits());
    }

    @Override
    public void readData(LPDataInputStream data) throws IOException {
        super.readData(data);
        uuid = new UUID(data.readLong(), data.readLong());
    }
}
