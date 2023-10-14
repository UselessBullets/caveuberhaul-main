package diarr.caveuberhaul.mixin;

import diarr.caveuberhaul.blocks.EntityFallingStalagtite;
import diarr.caveuberhaul.blocks.RenderFallingStalagtite;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

@Mixin(value= EntityRenderDispatcher.class,remap = false)
public class RenderManagerMixin {
    @Final
    @Shadow
    private Map<Class<?>, EntityRenderer<?>> renderers;
    @Inject(method = "<init>", at = @At("TAIL"))
    private void RenderManager(CallbackInfo ci)
    {
        EntityRenderer<Entity> stalagtiteRenderer = new RenderFallingStalagtite();
        stalagtiteRenderer.setRenderDispatcher(EntityRenderDispatcher.instance);
        renderers.put(EntityFallingStalagtite.class, stalagtiteRenderer);
    }
}
