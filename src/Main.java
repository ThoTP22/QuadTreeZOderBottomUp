
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuadTree tree = new QuadTree(-1000000, -1000000, 1000000, 1000000);

        // Tạo danh sách các điểm
        List<Point> points = new ArrayList<>();
        try {
            List<String> data = Utils.readDataFromFile("C:\\Quadtree\\FinalQuadTree\\FinalQuadTree\\toa_do_100mb.csv");
            for (String line : data) {
                String[] coordinates = line.split(",");
                double x = Double.parseDouble(coordinates[0].trim());
                double y = Double.parseDouble(coordinates[1].trim());
                points.add(new Point(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sắp xếp các điểm theo mã Z-order
        Collections.sort(points);

        // Xây dựng cây quadtree từ dưới lên
        tree.buildBottomUp(points);

        // In ra cấu trúc của cây
        tree.traverseAndPrintByQuadrant(tree.getRootNode());
    }
}
