package logisticspipes.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

import logisticspipes.LogisticsPipes;
import logisticspipes.items.LogisticsItemCard;
import logisticspipes.utils.gui.DummyContainer;
import logisticspipes.utils.gui.GuiGraphics;
import logisticspipes.utils.gui.LogisticsBaseGuiScreen;

public class GuiFreqCardContent extends LogisticsBaseGuiScreen {

    public GuiFreqCardContent(EntityPlayer player, IInventory card) {
        super(180, 130, 0, 0);
        DummyContainer dummy = new DummyContainer(player.inventory, card);
        dummy.addRestrictedSlot(0, card, 82, 15, itemStack -> {
            if (itemStack == null) {
                return false;
            }
            if (itemStack.getItem() != LogisticsPipes.LogisticsItemCard) {
                return false;
            }
            return itemStack.getItemDamage() == LogisticsItemCard.FREQ_CARD;
        });
        dummy.addNormalSlotsForPlayerInventory(10, 45);
        inventorySlots = dummy;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GuiGraphics.drawGuiBackGround(mc, guiLeft, guiTop, right, bottom, zLevel, true);
        GuiGraphics.drawPlayerInventoryBackground(mc, guiLeft + 10, guiTop + 45);
        GuiGraphics.drawSlotBackground(mc, guiLeft + 81, guiTop + 14);
    }
}
