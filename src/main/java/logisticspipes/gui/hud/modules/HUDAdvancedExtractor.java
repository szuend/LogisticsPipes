package logisticspipes.gui.hud.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import logisticspipes.interfaces.IHUDButton;
import logisticspipes.interfaces.IHUDModuleRenderer;
import logisticspipes.modules.ModuleAdvancedExtractor;
import logisticspipes.utils.Color;
import logisticspipes.utils.gui.GuiGraphics;
import logisticspipes.utils.gui.hud.BasicHUDButton;
import logisticspipes.utils.item.ItemIdentifierStack;
import logisticspipes.utils.item.ItemStackRenderer;
import logisticspipes.utils.item.ItemStackRenderer.DisplayAmount;

public class HUDAdvancedExtractor implements IHUDModuleRenderer {

    private final List<IHUDButton> buttons = new ArrayList<>();
    private int selected = 0;

    private final ModuleAdvancedExtractor module;

    public HUDAdvancedExtractor(ModuleAdvancedExtractor moduleAdvancedExtractor) {
        module = moduleAdvancedExtractor;
        buttons.add(new TabButton("Side", 0, -30, -50, 25, 10));
        buttons.add(new TabButton("Inv", 1, -5, -50, 25, 10));
    }

    @Override
    public void renderContent(boolean shifted) {
        if (selected == 0) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            ForgeDirection d = module.getSneakyDirection();
            mc.fontRenderer.drawString("Extract", -22, -22, 0);
            mc.fontRenderer.drawString("from:", -22, -9, 0);
            mc.fontRenderer.drawString(((d == ForgeDirection.UNKNOWN) ? "DEFAULT" : d.name()), -22, 18, 0);
        } else {
            Minecraft mc = FMLClientHandler.instance().getClient();
            GL11.glScalef(1.0F, 1.0F, -0.00001F);
            ItemStackRenderer.renderItemIdentifierStackListIntoGui(
                    ItemIdentifierStack.getListFromInventory(module.getFilterInventory()),
                    null,
                    0,
                    -25,
                    -32,
                    3,
                    9,
                    18,
                    18,
                    100.0F,
                    DisplayAmount.NEVER,
                    true,
                    false,
                    shifted);
            GL11.glScalef(1.0F, 1.0F, 1 / -0.00001F);
            if (module.areItemsIncluded()) {
                mc.fontRenderer.drawString("Included", -22, 25, 0);
            } else {
                mc.fontRenderer.drawString("Excluded", -22, 25, 0);
            }
        }
    }

    @Override
    public List<IHUDButton> getButtons() {
        return buttons;
    }

    private class TabButton extends BasicHUDButton {

        private final int mode;

        public TabButton(String name, int mode, int x, int y, int width, int heigth) {
            super(name, x, y, width, heigth);
            this.mode = mode;
        }

        @Override
        public void clicked() {
            selected = mode;
        }

        @Override
        public void renderButton(boolean hover, boolean clicked, boolean shifted) {
            GL11.glTranslatef(0.0F, 0.0F, -0.000005F);
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (hover) {
                GL11.glColor4b((byte) 127, (byte) 127, (byte) 127, (byte) 127);
                if (!clicked) {
                    GL11.glTranslatef(0.0F, 0.0F, -0.01F);
                }
            } else {
                GL11.glColor4b((byte) 127, (byte) 127, (byte) 127, (byte) 64);
            }
            GL11.glScaled(0.5D, 0.5D, 1.0D);
            GuiGraphics.drawGuiBackGround(
                    mc,
                    posX * 2,
                    posY * 2,
                    (posX + sizeX) * 2,
                    (posY + sizeY) * 2 + 15,
                    0,
                    false,
                    true,
                    true,
                    false,
                    true);
            GL11.glScaled(2.0D, 2.0D, 1.0D);

            if (clicked) {
                GL11.glTranslatef(0.0F, 0.0F, -0.01F);
            }

            GL11.glTranslatef(0.0F, 0.0F, -0.000005F);
            int color = 0;
            if (hover && !clicked) {
                color = Color.getValue(Color.LIGHT_YELLOW);
            } else if (!clicked) {
                color = Color.getValue(Color.BLACK);
            } else {
                color = Color.getValue(Color.DARK_GREY);
            }
            GL11.glScaled(0.8D, 0.8D, 1.0D);
            mc.fontRenderer.drawString(
                    label,
                    (int) ((-(mc.fontRenderer.getStringWidth(label) / (2 * (1 / 0.8D))) + posX + sizeX / 2)
                            * (1 / 0.8D)),
                    (int) ((posY + (sizeY - 8) / 2) * (1 / 0.8D)) + 2,
                    color);
            GL11.glScaled(1 / 0.8D, 1 / 0.8D, 1.0D);
            if (hover) {
                GL11.glTranslatef(0.0F, 0.0F, 0.01F);
            }
            GL11.glTranslatef(0.0F, 0.0F, 0.00001F);
        }

        @Override
        public boolean shouldRenderButton() {
            return true;
        }

        @Override
        public boolean buttonEnabled() {
            return mode != selected;
        }
    }
}
