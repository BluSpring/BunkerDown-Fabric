package xyz.bluspring.bunkerdown.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.mcreator.bunkerdown.block.SteelVentBlock;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.world.level.block.LeavesBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(LiquidBlockRenderer.class)
public class LiquidBlockRendererMixin {
    @SuppressWarnings("MixinAnnotationTarget")
    @WrapOperation(method = "tesselate", constant = @Constant(classValue = LeavesBlock.class))
    public boolean sc$displayFluidOverlayForReinforcedGlass(Object obj, Operation<Boolean> original) {
        return original.call(obj) || obj instanceof SteelVentBlock;
    }
}
