/*
 * Copyright (c) Krapht, 2011 "LogisticsPipes" is distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package logisticspipes.gui.modules;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import logisticspipes.modules.ModuleFluidSupplier;
import logisticspipes.utils.gui.DummyContainer;

public class GuiFluidSupplier extends ModuleBaseGui {

    private final ModuleFluidSupplier _liquidSupplier;

    public GuiFluidSupplier(IInventory playerInventory, ModuleFluidSupplier module) {
        super(null, module);
        _liquidSupplier = module;
        DummyContainer dummy = new DummyContainer(playerInventory, _liquidSupplier.getFilterInventory());
        dummy.addNormalSlotsForPlayerInventory(8, 60);

        // Pipe slots
        for (int pipeSlot = 0; pipeSlot < 9; pipeSlot++) {
            dummy.addDummySlot(pipeSlot, 8 + pipeSlot * 18, 18);
        }

        inventorySlots = dummy;
        xSize = 175;
        ySize = 142;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        mc.fontRenderer.drawString(_liquidSupplier.getFilterInventory().getInventoryName(), 8, 6, 0x404040);
        mc.fontRenderer.drawString("Inventory", 8, ySize - 92, 0x404040);
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation("logisticspipes", "textures/gui/itemsink.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(GuiFluidSupplier.TEXTURE);
        int j = guiLeft;
        int k = guiTop;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
    }
}
