package logisticspipes.network.packets.satpipe;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.network.LPDataInputStream;
import logisticspipes.network.LPDataOutputStream;
import logisticspipes.network.abstractpackets.CoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.pipes.PipeFluidSatellite;
import logisticspipes.pipes.PipeItemsSatelliteLogistics;
import logisticspipes.pipes.basic.LogisticsTileGenericPipe;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SatPipeSetID extends CoordinatesPacket {

    public SatPipeSetID(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new SatPipeSetID(getId());
    }

    @Getter
    @Setter
    private int satID;

    @Override
    public void writeData(LPDataOutputStream data) throws IOException {
        data.writeInt(satID);
        super.writeData(data);
    }

    @Override
    public void readData(LPDataInputStream data) throws IOException {
        satID = data.readInt();
        super.readData(data);
    }

    @Override
    public void processPacket(EntityPlayer player) {
        final LogisticsTileGenericPipe pipe = getPipe(player.worldObj);
        if (pipe == null) {
            return;
        }

        if (pipe.pipe instanceof PipeItemsSatelliteLogistics) {
            ((PipeItemsSatelliteLogistics) pipe.pipe).setSatelliteId(getSatID());
        }
        if (pipe.pipe instanceof PipeFluidSatellite) {
            ((PipeFluidSatellite) pipe.pipe).setSatelliteId(getSatID());
        }
    }
}
