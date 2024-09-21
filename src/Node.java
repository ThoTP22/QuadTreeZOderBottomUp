public class Node {
    private double x;
    private double y;
    private double w;
    private double h;
    private Node parent;
    private Point point;
    private NodeType nodeType = NodeType.EMPTY;
    private Node nw;
    private Node ne;
    private Node sw;
    private Node se;

    public Node(double x, double y, double w, double h, Node parent) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.parent = parent;
    }

    public double getX() {
        return x; }
    public void setX(double x) {
        this.x = x; }

    public double getY() {
        return y; }
    public void setY(double y) {
        this.y = y; }

    public double getW() {
        return w; }
    public void setW(double w) {
        this.w = w; }

    public double getH() {
        return h; }
    public void setH(double h) {
        this.h = h; }

    public Node getParent() {
        return parent; }
    public void setParent(Node parent) {
        this.parent = parent; }

    public Point getPoint() {
        return point; }
    public void setPoint(Point point) {
        this.point = point; }

    public NodeType getNodeType() {
        return nodeType; }
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType; }

    public Node getNw() { return nw; }
    public void setNw(Node nw) { this.nw = nw; }

    public Node getNe() { return ne; }
    public void setNe(Node ne) { this.ne = ne; }

    public Node getSw() { return sw; }
    public void setSw(Node sw) { this.sw = sw; }

    public Node getSe() { return se; }
    public void setSe(Node se) { this.se = se; }
}
