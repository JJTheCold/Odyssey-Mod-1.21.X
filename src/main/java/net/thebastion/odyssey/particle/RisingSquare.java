package net.thebastion.odyssey.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class RisingSquare extends TextureSheetParticle {
    private final double centerX;
    private final double centerZ;
    private final double radius;
    private double angle;
    private final double rotationSpeed;

    @Override
    public int getLightColor(float partialTick) {
        int i = super.getLightColor(partialTick);
        int j = 240;
        int k = i >> 16 & 0xFF;
        return j | k << 16;
    }

    protected RisingSquare(ClientLevel level, double x, double y, double z, double radius, double rotationSpeed, SpriteSet sprites) {
        super(level, x, y, z);

        this.centerX = x;
        this.centerZ = z;
        this.radius = radius;
        this.rotationSpeed = rotationSpeed;
        this.angle = 0.0;

        this.lifetime = 60;
        this.gravity = 0.0F;
        this.alpha = 1f;

        this.setSprite(sprites.get(this.random));
    }

    public void tick() {
        super.tick();
        this.yd = 0.04D;
        this.move(this.xd, this.yd, this.zd);
        this.xd *= 0.98D;
        this.yd *= 0.98D;
        this.zd *= 0.98D;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {

            double radius = xSpeed != 0 ? xSpeed : 1.0;
            double rotationSpeed = zSpeed != 0 ? zSpeed : 0.1;

            return new RisingSquare(level, x, y, z, radius, rotationSpeed, this.sprites);
        }
    }
}