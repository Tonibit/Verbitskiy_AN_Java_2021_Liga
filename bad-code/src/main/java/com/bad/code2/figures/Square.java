package com.bad.code2.figures;

import com.bad.code2.interfaces.Shape2D;

public class Square implements Shape2D {
    private Double x;
    private Double y;
    private Double edgeSize;

    public Square(Double x, Double y, Double edgeSize) {
        this.x = x;
        this.y = y;
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getPerimeter() {
        return 4 * edgeSize;
    }

    @Override
    public Double getArea() {
        return edgeSize * edgeSize;
    }
}
