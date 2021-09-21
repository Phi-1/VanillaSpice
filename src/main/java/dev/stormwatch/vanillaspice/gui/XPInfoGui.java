package dev.stormwatch.vanillaspice.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class XPInfoGui extends Screen {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("vanillaspice", "textures/gui/xpgui.png");
    private static final int xSize = 160;
    private static final int ySize = 160;

    public XPInfoGui() {
        super(new TranslationTextComponent("vanillaspice.xpinfogui.title"));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width/2, 8, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}
