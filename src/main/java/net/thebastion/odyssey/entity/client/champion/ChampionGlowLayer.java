package net.thebastion.odyssey.entity.client.champion;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.Champion;

@OnlyIn(Dist.CLIENT)
public class ChampionGlowLayer<T extends Champion, M extends ChampionModel<T>> extends EyesLayer<T, M> {
    private static final RenderType CHAMPION_GLOW = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, "textures/entity/champion/champion-texture-glow.png"));
    public ChampionGlowLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }


    @Override
    public RenderType renderType() {
        return CHAMPION_GLOW;
    }
}
