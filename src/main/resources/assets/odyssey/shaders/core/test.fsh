#version 150

uniform sampler2D Sampler0; // Your texture image
uniform vec4 ColorModulator; // Base tint from Minecraft
uniform float GameTime;      // A timer that counts up automatically

in vec4 vertexColor;         // Color from the model
in vec2 texCoord0;           // X and Y spot on the texture (0.0 to 1.0)

out vec4 fragColor;          // The final pixel color drawn on screen

void main() {
    vec4 color = texture(Sampler0, textCoord0);

    float blink = sin(GameTime * 5000.0) * 0.5 + 0.5;
    fragColor = color * blink;
}