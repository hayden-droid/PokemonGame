package com.pokemon.game;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.entities.Trigger;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;

import javax.swing.*;

public class GameLogic {
    public static void init() {
        // we'll use a camera in our game that is locked to the location of the player
        Camera camera = new PositionLockCamera(Player.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
        Game.screens().setChangeCooldown(0);
        Game.graphics().setBaseRenderScale(4.001f);
        ((JFrame) Game.window().getHostControl()).setResizable(false);

        // Game.world().setGravity(120);

        // add default game logic for when a level was loaded
        Game.world().onLoaded(e -> {
            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = e.getSpawnpoint("enter");
            if (enter != null) {
                enter.spawn(Player.instance());
            }
        });


        Game.world().loadEnvironment("map1");
        Trigger trigger1 = Game.world().environment().getTrigger("TRIGGER_BOX_1");
        trigger1.addTriggerListener(new PokemonTriggerListener(TriggerBoxEnum.TRIGGER_BOX_1));
        /* Trigger trigger2 = Game.world().environment().getTrigger("TRIGGER_BOX_2");
        trigger2.addTriggerListener(new PokemonTriggerListener(TriggerBoxEnum.TRIGGER_BOX_2));
        Trigger trigger3 = Game.world().environment().getTrigger("TRIGGER_BOX_3");
        trigger3.addTriggerListener(new PokemonTriggerListener(TriggerBoxEnum.TRIGGER_BOX_3));*/
    }
}
