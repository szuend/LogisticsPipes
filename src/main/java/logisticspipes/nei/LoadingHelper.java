package logisticspipes.nei;

import codechicken.nei.guihook.GuiContainerManager;
import cpw.mods.fml.client.FMLClientHandler;
import logisticspipes.config.Configs;

public class LoadingHelper {

    public static void LoadNeiNBTDebugHelper() {
        if (Configs.TOOLTIP_INFO && !NEILogisticsPipesConfig.added) {
            GuiContainerManager.addTooltipHandler(new DebugHelper());
            NEILogisticsPipesConfig.added = true;
            if (FMLClientHandler.instance().getClient() != null
                    && FMLClientHandler.instance().getClient().thePlayer != null) {
                FMLClientHandler.instance().getClient().thePlayer.sendChatMessage("Enabled.");
            }
        } else if (NEILogisticsPipesConfig.added) {
            if (FMLClientHandler.instance().getClient() != null
                    && FMLClientHandler.instance().getClient().thePlayer != null) {
                FMLClientHandler.instance().getClient().thePlayer.sendChatMessage("Already enabled.");
            }
        }
    }
}
