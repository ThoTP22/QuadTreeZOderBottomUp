
import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private Node root;
    private int count = 0;

    public QuadTree(double minX, double minY, double maxX, double maxY) {
        this.root = new Node(minX, minY, maxX - minX, maxY - minY, null);
    }

    // Method to build the quadtree using bottom-up approach
    public void buildBottomUp(List<Point> points) {
        List<Node> leaves = new ArrayList<>();

        // Create all leaf nodes and add points to the respective leaf nodes
        for (Point point : points) {
            Node leafNode = new Node(point.getX(), point.getY(), 1, 1, null);
            leafNode.setNodeType(NodeType.LEAF);
            leafNode.setPoint(point);
            leaves.add(leafNode);
        }

        // Merge leaf nodes into parent nodes until there's only one node left
        while (leaves.size() > 1) {
            List<Node> newLeaves = new ArrayList<>();

            // Merge every 4 leaves into a new parent node
            for (int i = 0; i < leaves.size(); i += 4) {
                Node parent = new Node(0, 0, 0, 0, null);
                parent.setNodeType(NodeType.POINTER);

                // Set child nodes for the parent
                for (int j = 0; j < 4; j++) {
                    if (i + j < leaves.size()) {
                        Node child = leaves.get(i + j);
                        setChild(parent, child, j);
                    }
                }
                newLeaves.add(parent);
            }

            leaves = newLeaves;  // Update the list of leaves
        }

        this.root = leaves.get(0);  // Set the root to the last remaining node
    }

    // Set the child node for a parent based on the quadrant index
    private void setChild(Node parent, Node child, int index) {
        double halfWidth = parent.getW() / 2;
        double halfHeight = parent.getH() / 2;
        child.setParent(parent);

        switch (index) {
            case 0:
                parent.setNw(child);
                break;
            case 1:
                parent.setNe(child);
                break;
            case 2:
                parent.setSw(child);
                break;
            case 3:
                parent.setSe(child);
                break;
        }
    }

    public Node getRootNode() {
        return this.root;
    }

    public void traverseAndPrintByQuadrant(Node node) {
        if (node == null) {
            return;
        }

        System.out.println("Node Type: " + (node.getNodeType() == NodeType.LEAF ? "LEAF" : "POINTER"));
        System.out.println("Point: " + (node.getPoint() != null ? "(" + node.getPoint().getX() + ", " + node.getPoint().getY() + ")" : "null"));
        System.out.println("Bounds: (" + node.getX() + ", " + node.getY() + ", " + node.getW() + ", " + node.getH() + ")");

        if (node.getNodeType() == NodeType.POINTER) {
            System.out.println("  -- Northeast Quadrant --");
            traverseAndPrintByQuadrant(node.getNe());

            System.out.println("  -- Southeast Quadrant --");
            traverseAndPrintByQuadrant(node.getSe());

            System.out.println("  -- Southwest Quadrant --");
            traverseAndPrintByQuadrant(node.getSw());

            System.out.println("  -- Northwest Quadrant --");
            traverseAndPrintByQuadrant(node.getNw());
        }
    }
    // Hàm tìm kiếm các điểm nằm trong vùng hình chữ nhật (range)
    public List<Point> search(double rangeXMin, double rangeYMin, double rangeXMax, double rangeYMax) {
        List<Point> foundPoints = new ArrayList<>();
        search(this.root, rangeXMin, rangeYMin, rangeXMax, rangeYMax, foundPoints);
        return foundPoints;
    }

    // Hàm đệ quy tìm kiếm các điểm trong vùng hình chữ nhật
    private void search(Node node, double rangeXMin, double rangeYMin, double rangeXMax, double rangeYMax, List<Point> foundPoints) {
        if (node == null) {
            return;
        }

        // Kiểm tra nếu node nằm ngoài vùng tìm kiếm
        if (!intersects(rangeXMin, rangeYMin, rangeXMax, rangeYMax, node)) {
            return;
        }

        // Kiểm tra nếu node là LEAF và điểm của node nằm trong vùng tìm kiếm
        if (node.getNodeType() == NodeType.LEAF) {
            Point point = node.getPoint();
            if (point != null && point.getX() >= rangeXMin && point.getX() <= rangeXMax &&
                    point.getY() >= rangeYMin && point.getY() <= rangeYMax) {
                foundPoints.add(point);
            }
        } else if (node.getNodeType() == NodeType.POINTER) {
            // Nếu là node POINTER, tìm kiếm tiếp tục trong các nút con
            search(node.getNw(), rangeXMin, rangeYMin, rangeXMax, rangeYMax, foundPoints);
            search(node.getNe(), rangeXMin, rangeYMin, rangeXMax, rangeYMax, foundPoints);
            search(node.getSw(), rangeXMin, rangeYMin, rangeXMax, rangeYMax, foundPoints);
            search(node.getSe(), rangeXMin, rangeYMin, rangeXMax, rangeYMax, foundPoints);
        }
    }

    // Kiểm tra xem vùng tìm kiếm có giao nhau với nút hiện tại không
    private boolean intersects(double rangeXMin, double rangeYMin, double rangeXMax, double rangeYMax, Node node) {
        return !(rangeXMax < node.getX() || rangeXMin > node.getX() + node.getW() ||
                rangeYMax < node.getY() || rangeYMin > node.getY() + node.getH());
    }
}
