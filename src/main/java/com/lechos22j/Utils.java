package com.lechos22j;

import com.lechos22j.entity.Entity;

import java.awt.*;

public class Utils {
    public static int clamp(int min, int value, int max) {
        return Math.max(min, Math.min(value, max));
    }
    public static int align(int value, int align) {
        return value - value % align;
    }
    public static Image loadResourceImage(String name) {
        return Toolkit.getDefaultToolkit().getImage(Utils.class.getResource(name));
    }

    public static double distance(int x, int y, int x1, int y1) {
        return Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
    }
    public static double squareDistance(int x, int y, int x1, int y1) {
        return Math.pow(x - x1, 2) + Math.pow(y - y1, 2);
    }
    public static class Vec2{
        public double x;
        public double y;
        public Object ref;
        public Vec2 setRef(Object ref){
            this.ref = ref;
            return this;
        }
        public Vec2(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public double length() {
            return Math.sqrt(x * x + y * y);
        }
        public double lengthSquared() {
            return x * x + y * y;
        }
        public Vec2 normalize() {
            double length = length();
            return new Vec2(x / length, y / length);
        }
        public Vec2 add(Vec2 other) {
            return new Vec2(x + other.x, y + other.y);
        }
        public Vec2 sub(Vec2 other) {
            return new Vec2(x - other.x, y - other.y);
        }
        public Vec2 mul(double scalar) {
            return new Vec2(x * scalar, y * scalar);
        }
        public Vec2 div(double scalar) {
            return new Vec2(x / scalar, y / scalar);
        }
        public Vec2 rotate(double angle) {
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            return new Vec2(x * cos - y * sin, x * sin + y * cos);
        }

        public Object getRef() {
            return ref;
        }
    }
}
