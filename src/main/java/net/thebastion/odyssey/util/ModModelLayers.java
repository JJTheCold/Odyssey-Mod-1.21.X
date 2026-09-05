package net.thebastion.odyssey.util;

import com.google.common.collect.Sets;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers extends ModelLayers {
    private static final String DEFAULT_LAYER = "main";
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation BURIED_INNER_ARMOR = registerInnerArmor("bogged");
    public static final ModelLayerLocation BURIED_OUTER_ARMOR = registerOuterArmor("bogged");
    public static final ModelLayerLocation BURIED_OUTER_LAYER = register("bogged", "outer");
    public static final ModelLayerLocation BURIED = register("bogged");

    private static ModelLayerLocation register(String path) {
        return register(path, "main");
    }

    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = createLocation(path, model);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation registerInnerArmor(String path) {
        return register(path, "inner_armor");
    }

    private static ModelLayerLocation registerOuterArmor(String path) {
        return register(path, "outer_armor");
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(ResourceLocation.withDefaultNamespace(path), model);
    }
}
