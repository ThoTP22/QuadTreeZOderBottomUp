public class Point implements Comparable<Point> {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Calculates the Z-order (Morton code) for the point.
     * @return Z-order code as a long.
     */
    public long calculateZOrder() {
        long xBits = interleaveBits((int)x);
        long yBits = interleaveBits((int)y);
        return xBits | (yBits << 1);
    }

    /**
     * Interleaves bits of an integer to calculate Morton code.
     * @param n Input integer.
     * @return Interleaved bits as a long.
     */
    private long interleaveBits(int n) {
        long x = n & 0x00000000FFFFFFFFL;
        x = (x | (x << 16)) & 0x0000FFFF0000FFFFL;
        x = (x | (x << 8)) & 0x00FF00FF00FF00FFL;
        x = (x | (x << 4)) & 0x0F0F0F0F0F0F0F0FL;
        x = (x | (x << 2)) & 0x3333333333333333L;
        x = (x | (x << 1)) & 0x5555555555555555L;
        return x;
    }

    // Compares points based on their Z-order code.
    @Override
    public int compareTo(Point other) {
        long thisZOrder = this.calculateZOrder();
        long otherZOrder = other.calculateZOrder();
        return Long.compare(thisZOrder, otherZOrder);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
