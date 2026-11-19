package net.thebastion.odyssey.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class SpinningSquare extends TextureSheetParticle {
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

    protected SpinningSquare(ClientLevel level, double x, double y, double z, double radius, double ySpeed, double rotationSpeed, SpriteSet sprites) {
        super(level, x, y, z);

        this.centerX = x;
        this.centerZ = z;
        this.radius = radius;
        this.rotationSpeed = rotationSpeed;
        this.angle = 0.0;

        this.lifetime = 60;
        this.gravity = -0.2F;
        this.alpha = 1f;

        this.setSprite(sprites.get(this.random));
        this.updatePosition();
        this.yd = ySpeed;
    }

    @Override
    public void tick() {
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.alpha = 1.0F - ((float)this.age / (float)this.lifetime);

        this.angle += this.rotationSpeed;
        this.updatePosition();
    }

    private void updatePosition() {
        double newX = this.centerX + (Math.cos(this.angle) * this.radius);
        double newZ = this.centerZ + (Math.sin(this.angle) * this.radius);
        this.setPos(newX, this.y, newZ);
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

            return new SpinningSquare(level, x, y, z, radius, ySpeed, rotationSpeed, this.sprites);
        }
    }
}