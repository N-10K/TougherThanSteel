package tougherthansteel;

import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.events.worldGeneration.GeneratedCaveOresEvent;
import necesse.level.gameObject.RockObject;
import necesse.level.gameObject.RockOreObject;
import necesse.level.maps.biomes.forest.ForestBiome;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.level.maps.biomes.plains.PlainsBiome;
import necesse.level.maps.biomes.swamp.SwampBiome;
import tougherthansteel.items.*;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;

import java.awt.*;

@ModEntry
public class TougherThanSteel {

    public void init() {
        System.out.println("Initializing Tougher Than Steel");

        //items
        System.out.println("Adding items...");
        ItemRegistry.registerItem("coal", new coal(), 8, true);
        ItemRegistry.registerItem("sulphur", new sulphur(), 12, true);


        //ores
        System.out.println("Adding ores...");
        int coalOre=ObjectRegistry.registerObject("tttcoalore" , new RockOreObject((RockObject)ObjectRegistry.getObject("rock"), "oremask", "coalore", new Color(10, 10, 10), "coal"), 0.0F, true);
        int sulphurOre=ObjectRegistry.registerObject("tttsulphurore" , new RockOreObject((RockObject)ObjectRegistry.getObject("rock"), "oremask", "sulphurore", new Color(10, 10, 10), "sulphur"), 0.0F, true);
        //ore generation
        GameEvents.addListener(GeneratedCaveOresEvent.class, new GameEventListener<GeneratedCaveOresEvent>() {
            @Override
            public void onEvent(GeneratedCaveOresEvent event) {
                if (event.level.biome instanceof ForestBiome) {
                    event.caveGeneration.generateOreVeins(1, 2,8, coalOre);
                    event.caveGeneration.generateOreVeins(1, 2,6, sulphurOre);
                }
                if (event.level.biome instanceof PlainsBiome) {
                    event.caveGeneration.generateOreVeins(1, 2,6, coalOre);
                    event.caveGeneration.generateOreVeins(1, 2,8, sulphurOre);
                }
                if (event.level.biome instanceof SwampBiome) {
                    event.caveGeneration.generateOreVeins(1, 4,12, coalOre);
                    event.caveGeneration.generateOreVeins(1, 2,6, sulphurOre);
                }
            }
        });

    }

    public void initResources() {

    }

    public void postInit() {
        System.out.println("Tougher Than Steel initialized");

        //Recipes
        System.out.println("Adding recipes...");
        Recipes.registerModRecipe(new Recipe(
                "torch",
                6,
                RecipeTechRegistry.NONE,
                new Ingredient[]{
                        new Ingredient("anylog",4),
                        new Ingredient("coal",1)
                }
                )
        );
    }

}
