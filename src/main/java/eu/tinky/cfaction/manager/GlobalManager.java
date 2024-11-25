package eu.tinky.cfaction.manager;


public class GlobalManager {

    private final PlayerManager playerManager;

    public GlobalManager() {
        this.playerManager = new PlayerManager();
    }


    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
