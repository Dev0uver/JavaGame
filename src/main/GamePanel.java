package main;

import audio.Audio;
import entities.Bullet;
import entities.Enemy;
import entities.Player;
import inputs.KeyboardInputs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {

    public Player player = new Player();

    // Для движения врагов
    private boolean direction = true;

    // Объекты
    private final List<Bullet> bulletList = new ArrayList<>();
    private final List<Enemy> enemyList = new ArrayList<>();

    // Создание меню
    private Menu menu = new Menu();
    //
    private Audio audio = new Audio();



    public void pause(){
        menu.mainMenu(this);


        }

    public void CreateBullet() {
        pause();

        Bullet bullet = new Bullet(player.rectX, player.rectY, player.playerWidth);
        bulletList.add(bullet);
        audio.shot();
    }

    public void AddEnemy (int count, int row) {

        int sector = 120;

        for (int number = 0; number < count; number++) {

            Enemy enemy = new Enemy(sector, number, row);
            enemyList.add(enemy);
        }
    }



    // Конструктор класса
    public GamePanel() {
        pause(); // кнопка

        addKeyListener(new KeyboardInputs(this));
    }


    // Объект и метод для рисования (!Заменить на спрайты)
    public void paintComponent(Graphics graphics) {

        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);

        if (enemyList.size() == 0) {
            // Количество врагов в 1 строке
            int row = 10;
            // Количество врагов на поле
            int count = 30;
            AddEnemy(count, row);
        }
        else {
            for (Enemy enemy : enemyList) {
                enemy.PaintEnemy(graphics);
            }
            MoveEnemy();
        }

        if (bulletList != null) {

            synchronized (bulletList) {

                for (Bullet bullet : bulletList) {
                    // Отрисовка
                    bullet.PaintBullet(graphics);
                    bullet.MoveBullet();
                }
                CheckTopReach();
                CheckEnemyCollision();
            }
        }

        // Отрисовка и движение игрока
        player.PaintPlayer(graphics);
        player.MovePlayer();
    }


    private void CheckTopReach() {

        synchronized (bulletList) {

            for (int i = 0; i < bulletList.size(); i++) {

                if (bulletList.get(i).y <= -bulletList.get(i).height) {

                    bulletList.remove(i);
                }
            }
        }
    }


    // Коллизия
    private void CheckEnemyCollision() {
        if (bulletList != null && enemyList != null) {
            for (int i = 0; i < bulletList.size(); i++) {

                // позиция и размеры снаряда
                float bulletX = bulletList.get(i).x;
                float bulletY = bulletList.get(i).y;
                int bulletWidth = bulletList.get(i).width;
                int bulletHeight = bulletList.get(i).height;

                for (int j = 0; j < enemyList.size(); j ++) {
                    // позиция и размеры пришельца
                    float enemyX = enemyList.get(j).xPosition;
                    float enemyY = enemyList.get(j).yPosition;
                    int enemyWidth = enemyList.get(j).width;
                    int enemyHeight = enemyList.get(j).height;

                    // проверка столкновения снаряда и пришельца и их удаление в случае подтверждения
                    if ( bulletX + bulletWidth >= enemyX
                            && bulletX <= enemyX + enemyWidth
                            && bulletY >= enemyY
                            && bulletY - bulletHeight <= enemyY + enemyHeight ) {
                        enemyList.remove(j);
                        bulletList.remove(i);
                        audio.kill();
                    }
                }
            }
        }
    }

    private void MoveEnemy() {
        // !! Управление врагами происходит из переменных описанных выше !!
        boolean down = false;
        for (int i = 0; i < enemyList.size(); i++){
            // Проверка достижения правого края
            if (enemyList.get(i).xPosition + enemyList.get(i).width >= GameWindow.width) {
                // Означает что надо двигать впараво
                direction = false;
                // Означает что надо двигать вниз
                down = true;
            }
            // Проверка достижения левого края
            if (enemyList.get(i).xPosition <= 0) {
                direction = true;
                down = true;
            }
            // Проверка не вышли ли враги за нижнюю границу
            if (enemyList.get(i).yPosition >= GameWindow.height - player.playerHeight - 120) {
                enemyList.remove(enemyList.get(i));
            }
        }
        // Перемещение вниз при достижении края
        if (down) {
            for (Enemy enemy : enemyList) {
                enemy.yPosition += enemy.jumpDown;
            }
        }
        // Движение вправо или влево
        for (Enemy enemy : enemyList) {
            if (direction) {
                enemy.xPosition += enemy.speed;
            }
            else {
                enemy.xPosition -= enemy.speed;
            }
        }
    }
}
