package net.thebastion.odyssey.entity.client.buried.lesser;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thebastion.odyssey.entity.custom.BuriedLesser;

@OnlyIn(Dist.CLIENT)
public class BuriedLesserModel extends SkeletonModel<BuriedLesser> {

    public BuriedLesserModel(ModelPart p_331727_) {
        super(p_331727_);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        SkeletonModel.createDefaultSkeletonMesh(partdefinition);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void prepareMobModel(BuriedLesser p_331102_, float p_330960_, float p_331747_, float p_330221_) {
        super.prepareMobModel(p_331102_, p_330960_, p_331747_, p_330221_);
    }
}
