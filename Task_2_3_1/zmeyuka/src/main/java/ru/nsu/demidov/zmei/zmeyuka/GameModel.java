package ru.nsu.demidov.zmei.zmeyuka;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private boolean isItOver = false;
    private int width;
    private int height;
    private Snake snake;
    private List<Point> foods;
    private GameView view;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(width / 2, height / 2);
        this.foods = new ArrayList<>();
        generateFood();
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void update() {
        snake.move();
        checkCollisions();
        checkFood();
    }

    private void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        foods.add(new Point(x, y));
    }

    private void checkFood() {
        Point head = snake.getHead();
        for (Point food : foods) {
            if (food.equals(head) == true) {
                snake.grow();
                foods.remove(food);
                generateFood();
                break;
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Point> getFoods() {
        return foods;
    }

    private void checkCollisions() {
        Point head = snake.getHead();
        if (head.getX() < 0 || head.getX() >= width || head.getY() < 0 || head.getY() >= height) {
            isItOver = true;
        }
        LinkedList<Point> body = snake.getBody();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i)) == true) {
                isItOver = true;
                break;
            }
        }
        if (isItOver == true) {
            itsActuallyOverForYou();
        }
    }

    private void itsActuallyOverForYou() {
        System.out.println("It's officially over");
    }

    public boolean isOver() {
        return isItOver;
    }
}