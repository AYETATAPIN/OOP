package ru.nsu.demidov.zmei.zmeyuka;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private Direction direction;

    public Snake(int startX, int startY) {
        body = new LinkedList<>();
        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        body.add(new Point(startX - 2, startY));
        direction = Direction.RIGHT;
    }

    public void move() {
        Point head = getHead();
        Point newHead = switch (direction) {
            case UP -> new Point((int) head.getX(), (int) (head.getY() - 1));
            case DOWN -> new Point((int) head.getX(), (int) (head.getY() + 1));
            case LEFT -> new Point((int) (head.getX() - 1), (int) head.getY());
            case RIGHT -> new Point((int) (head.getX() + 1), (int) head.getY());
        };

        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        Point tail = body.getLast();
        body.addLast(new Point((int) tail.getX(), (int) tail.getY()));
    }

    public Point getHead() {
        return body.getFirst();
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        if (newDirection.isOpposite(this.direction) == false) {
            this.direction = newDirection;
        }
    }
}