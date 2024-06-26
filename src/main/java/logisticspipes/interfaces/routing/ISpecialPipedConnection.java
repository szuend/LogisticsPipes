package logisticspipes.interfaces.routing;

import java.util.EnumSet;
import java.util.List;

import net.minecraftforge.common.util.ForgeDirection;

import logisticspipes.proxy.specialconnection.SpecialPipeConnection.ConnectionInformation;
import logisticspipes.routing.PipeRoutingConnectionType;
import logisticspipes.routing.pathfinder.IPipeInformationProvider;

public interface ISpecialPipedConnection {

    boolean init();

    boolean isType(IPipeInformationProvider startPipe);

    List<ConnectionInformation> getConnections(IPipeInformationProvider startPipe,
            EnumSet<PipeRoutingConnectionType> connection, ForgeDirection side);
}
