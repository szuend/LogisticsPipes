package logisticspipes.pipes.upgrades;

import logisticspipes.modules.abstractmodules.LogisticsModule;
import logisticspipes.pipes.basic.CoreRoutedPipe;

public class OpaqueUpgrade implements IPipeUpgrade {

    @Override
    public boolean needsUpdate() {
        return true;
    }

    @Override
    public boolean isAllowedForPipe(CoreRoutedPipe pipe) {
        return true;
    }

    @Override
    public boolean isAllowedForModule(LogisticsModule pipe) {
        return false;
    }

    @Override
    public String[] getAllowedPipes() {
        return new String[] { "all" };
    }

    @Override
    public String[] getAllowedModules() {
        return new String[] {};
    }
}
