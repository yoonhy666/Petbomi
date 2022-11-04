package com.example.petbomi;

public class GetBounds {
    public double minX = 0;
    public double minY = 0;
    public double maxX = 0;
    public double maxY = 0;

    public GetBounds(double mylon, double mylat) {
//          mylon = 126.9864655;
//          mylat = 37.56096649;

        double radius = 0.007;
        double ptX = mylon + (radius * Math.cos(0));    // 3시 좌표값
        double ptY = mylat + (radius * Math.sin(90));   // 12시 좌표값
        double tempX = mylon - (ptX - mylon);           // 3시 좌표값으로 9시 좌표값 얻기
        double tempY = mylat - (ptY - mylat);           // 12시 좌표값으로 6시 좌표값 얻기

        minX = tempX;
        minY = tempY;
        maxX = ptX;
        maxY = ptY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }
}
