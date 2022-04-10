package com.lechos22j;

import com.lechos22j.entity.Entity;
import com.lechos22j.entity.MageEntity;

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
}
