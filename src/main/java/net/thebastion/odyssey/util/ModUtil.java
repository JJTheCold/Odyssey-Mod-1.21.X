package net.thebastion.odyssey.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class ModUtil {

    public static void instaLook(Mob mob, LivingEntity vec3){
        mob.getLookControl().setLookAt(vec3.xRotO, vec3.yRotO, vec3.zOld, 200.0F, mob.getMaxHeadXRot());
        double d2 = vec3.xRotO - mob.getX();
        double d1 = vec3.zOld - mob.getZ();
        float rotate = -((float) Mth.atan2(d2, d1)) * (180F / (float) Math.PI);
        mob.setYRot(rotate);
        mob.yBodyRot = rotate;
        mob.yHeadRot = rotate;
    }
    public static void rotateTo(Mob looker, LivingEntity target){
        rotateTo(looker, target, 90.0F);
    }

    public static void rotateTo(Mob looker, LivingEntity target, float speed){
        double d2 = target.getX() - looker.getX();
        double d1 = target.getZ() - looker.getZ();
        float rotate = -((float) Mth.atan2(d2, d1)) * (180F / (float) Math.PI);
        looker.setYRot(rotLerp(looker.getYRot(), rotate, speed));
        looker.yBodyRot = looker.getYRot();
        looker.yHeadRotO = looker.getYRot();
        looker.yHeadRot = looker.getYRot();
    }

    public static float rotLerp(float start, float end, float speed) {
        float f = Mth.wrapDegrees(end - start);
        if (f > speed) {
            f = speed;
        }

        if (f < -speed) {
            f = -speed;
        }

        return start + f;
    }
}
