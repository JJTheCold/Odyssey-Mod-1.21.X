package net.thebastion.odyssey;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModAttributes {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    //public static final ModConfigSpec.ConfigValue<Double> RedstoneMonstrosityHealth;
    //public static final ModConfigSpec.ConfigValue<Double> RedstoneMonstrosityArmor;
    //public static final ModConfigSpec.ConfigValue<Double> RedstoneMonstrosityDamage;
    public static final ModConfigSpec.ConfigValue<Double> HanDPrecentDamage;
    //public static final ModConfigSpec.ConfigValue<Double> RedstoneMonstrosityHurtRange;

    static {
        BUILDER.push("Attributes");
        SPEC = BUILDER.build();
        HanDPrecentDamage = BUILDER.comment("Han-D's attack HP percent damage, Default: 0.08")
                .defineInRange("HanDPrecentDamage", 0.08, 0.0, 1.0);
    }
}
