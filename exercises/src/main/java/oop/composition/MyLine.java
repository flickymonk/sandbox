package oop.composition;

public class MyLine {

    private MyPoint being;
    private MyPoint end;

    public MyLine(int x1, int y1, int x2, int y2) {

    }

    public MyLine(MyPoint being, MyPoint end) {
        this.being = being;
        this.end = end;
    }

    public MyPoint getBeing() {
        return being;
    }

    public void setBeing(MyPoint being) {
        this.being = being;
    }

    public MyPoint getEnd() {
        return end;
    }

    public void setEnd(MyPoint end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "MyLine{" +
                "being=" + being +
                ", end=" + end +
                '}';
    }

    public int getBeingX() {
        return being.getX();
    }

    public void setBeingX(int x) {
        being.setX(x);
    }

    public int getBeingY() {
        return being.getY();
    }

    public void setBeingY(int y) {
        being.setY(y);
    }

    public int getEndX() {
        return end.getX();
    }

    public void setEndX(int x) {
        end.setX(x);
    }

    public int getEndY() {
        return end.getY();
    }

    public void setEndY(int y) {
        end.setY(y);
    }

    public int[] getBeingXY() {
        return new int[]{being.getX(), being.getY()};
    }

    public void setBeingXY(int x, int y) {
        being.setX(x);
        being.setY(y);
    }

    public int[] getEndXY() {
        return new int[]{end.getX(), end.getY()};
    }

    public void setEndXY(int x, int y) {
        end.setX(x);
        end.setY(y);
    }

    public double getLength() {
        return being.distance(end);
    }

    public double getGradient() {
        int xDiff = being.getX() - end.getX();
        int yDiff = being.getY() - end.getY();
        return Math.atan2(xDiff, yDiff);
    }


}
