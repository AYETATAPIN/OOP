package ru.nsu.demidov.zmei.task_2_3_1;

import javafx.beans.property.IntegerProperty;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameModel {
    private boolean isItOver = false;
    private int width;
    private int height;
    private Snake snake;
    private List<Food> foods;
    private final int INITIAL_FOOD_COUNT = 5;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(width / 2, height / 2);
        this.foods = new ArrayList<>();
        for (int i = 0; i < INITIAL_FOOD_COUNT; i++) {
            generateFood();
        }
    }

    public IntegerProperty snakeLengthProperty() {
        return snake.lengthProperty().snakeLengthProperty();
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
        boolean overlapped = true;
        LinkedList<Point> currentBody = snake.getBody();
        while (overlapped == true) {
            overlapped = false;
            try {
                for (Point joint : currentBody) {
                    if (Objects.equals(joint, snake.getHead())) {
                        continue;
                    }
                    if (x == joint.x && y == joint.y) {
                        overlapped = true;
                        x = rand.nextInt(width);
                        y = rand.nextInt(height);
                        break;
                    }
                }
            } catch (Exception ignored) {

            }
        }
        int value = rand.nextInt(1, 50);
        foods.add(new Food(x, y, value));
    }

    private void checkFood() {
        Point head = snake.getHead();
        Point neck = snake.getBody().get(1);
        for (Food food : foods) {
            if ((food.getX() == head.x || food.getX() == neck.x) && (food.getY() == head.y || food.getY() == neck.getY())) {
                for (int i = 0; i < food.getValue(); ++i) {
                    snake.grow();
                }
                foods.remove(food);
                generateFood();
                break;
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFoods() {
        return foods;
    }

    void setFoods(LinkedList<Food> foods) {
        this.foods = foods;
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
            System.out.println("It's actually over");
        }
    }

    public boolean itsActuallyOverForYou() {
        return isItOver;
    }
}