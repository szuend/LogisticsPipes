package logisticspipes.network.packets.pipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

import logisticspipes.network.LPDataInputStream;
import logisticspipes.network.LPDataOutputStream;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.renderer.LogisticsHUDRenderer;
import logisticspipes.routing.LaserData;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class RoutingLaserPacket extends ModernPacket {

    @Setter
    @Getter
    List<LaserData> lasers = new ArrayList<>();

    public RoutingLaserPacket(int id) {
        super(id);
    }

    @Override
    public void readData(LPDataInputStream data) throws IOException {
        while (data.readBoolean()) {
            lasers.add(new LaserData().readData(data));
        }
    }

    @Override
    public void processPacket(EntityPlayer player) {
        LogisticsHUDRenderer.instance().setLasers(getLasers());
    }

    @Override
    public void writeData(LPDataOutputStream data) throws IOException {
        for (LaserData laser : lasers) {
            data.writeBoolean(true);
            laser.writeData(data);
        }
        data.writeBoolean(false);
    }

    @Override
    public ModernPacket template() {
        return new RoutingLaserPacket(getId());
    }
}
