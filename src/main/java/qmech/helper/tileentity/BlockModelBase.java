package qmech.helper.tileentity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import qmech.helper.LoggingHelper;

/**
 * Created by anshuman on 14-05-2014.
 */
public class BlockModelBase extends CustomModelBase {

    ModelRenderer Block;

    @Override
    public void renderShapes(Entity player, float f, float f1, float f2, float f3, float f4, float f5) {
        Block.render(f5);
    }

    @Override
    public void initTextureSize() {
        LoggingHelper.getInstance().info("trace: BlockModelBase");
        this.textureHeight = 32;
        this.textureWidth = 64;
    }

    @Override
    public void initShapes() {
        Block = new ModelRenderer(this, 0, 0);
        Block.addBox(0F, 0F, 0F, 16, 16, 16);
        Block.setRotationPoint(0F, 0F, 0F);
        Block.setTextureSize(64, 32);
        Block.mirror = true;
        setRotation(Block, 0F, 0F, 0F);
    }
}
