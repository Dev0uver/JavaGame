package main;

import java.awt.*;

public class GameSettings {

    private int playerLives = 3;
    private int hp = 3;
    private float playerSpeed = 3f;
    private float enemySpeed = 1f;

    public static Font GUIFont = new Font("Courier", Font.BOLD, 30);


    public void ResetVariables() {

        enemySpeed = 1f;
        playerSpeed = 3f;
        hp = 3;
    }

    public void SpeedUp() {

        enemySpeed += enemySpeed * 0.02f;
        playerSpeed += playerSpeed * 0.01f;
    }

    public int GetPlayerLives() {
        return playerLives;
    }
    public void SetPlayerLives(int playerLives) {
        this.playerLives = playerLives;
    }

    public int GetHP() {
        return hp;
    }
    public void SetHP(int hp) {
        this.hp = hp;
    }

    public float GetPlayerSpeed() {
        return playerSpeed;
    }
    public float GetEnemySpeed() {
        return enemySpeed;
    }
}
