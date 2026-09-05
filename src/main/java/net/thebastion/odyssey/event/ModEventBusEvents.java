package net.thebastion.odyssey.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.entity.client.buried.BuriedModel;
import net.thebastion.odyssey.entity.client.champion.ChampionModel;
import net.thebastion.odyssey.entity.client.han_d.HanDModel;
import net.thebastion.odyssey.entity.client.RelicBoltModel;
import net.thebastion.odyssey.entity.custom.Buried;
import net.thebastion.odyssey.entity.custom.BuriedLesser;
import net.thebastion.odyssey.entity.custom.Champion;
import net.thebastion.odyssey.entity.custom.HanD;

@EventBusSubscriber(modid = Odyssey.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(HanDModel.LAYER_LOCATION, HanDModel::createBodyLayer);
            event.registerLayerDefinition(BuriedModel.LAYER_LOCATION, BuriedModel::createBodyLayer);
            event.registerLayerDefinition(ChampionModel.LAYER_LOCATION, ChampionModel::createBodyLayer);
            event.registerLayerDefinition(RelicBoltModel.LAYER_LOCATION, RelicBoltModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntities.HAN_D.get(), HanD.createAttributes().build());
            event.put(ModEntities.BURIED.get(), Buried.createAttributes().build());
            event.put(ModEntities.CHAMPION.get(), Champion.createAttributes().build());
            event.put(ModEntities.BURIED_LESSER.get(), BuriedLesser.createAttributes().build());
        }
}
