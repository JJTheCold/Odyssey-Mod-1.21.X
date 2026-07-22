package net.thebastion.odyssey.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.entity.client.HanDModel;
import net.thebastion.odyssey.entity.custom.HanD;

@EventBusSubscriber(modid = Odyssey.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(HanDModel.LAYER_LOCATION, HanDModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntities.HAN_D.get(), HanD.createAttributes().build());
        }
}
