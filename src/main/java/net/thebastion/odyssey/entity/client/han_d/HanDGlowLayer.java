package net.thebastion.odyssey.entity.client.han_d;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.HanD;

@OnlyIn(Dist.CLIENT)
public class HanDGlowLayer<T extends HanD, M extends HanDModel<T>> extends EyesLayer<T, M> {
    private static final RenderType HAN_D_GLOW = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/han-d/han-d_glow.png"));
    public HanDGlowLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }


    @Override
    public RenderType renderType() {
        return HAN_D_GLOW;
    }
}
