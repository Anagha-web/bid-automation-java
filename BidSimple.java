
public class BidSimple {

    public static void main(String[] args) {
        double taxRate = 8.0 / 100;
        double marginRate = 10.0 / 100;
        double overhead = 2000;

        String[][] takeoff = {
            {"T-10", "Tile", "Daltile PF05", "SF", "914", "10"},
            {"CP-10", "Carpet", "Mohawk HIK04", "SY", "596", "5"}
        };

        String[][] reference = {
            {"Tile", "Daltile PF05", "SF", "2.5", "1.2"},
            {"Carpet", "Mohawk HIK04", "SY", "3.2", "1.5"}
        };

        System.out.println("Code | Category | Qty(waste) | MatCost | LabCost | Tax | Overhead | Margin | Total");

        for (int i = 0; i < takeoff.length; i++) {
            String code = takeoff[i][0];
            String category = takeoff[i][1];
            String name = takeoff[i][2];
            String unit = takeoff[i][3];
            double qty = Double.parseDouble(takeoff[i][4]);
            double waste = Double.parseDouble(takeoff[i][5]);

            double qtyWithWaste = qty + (qty * waste / 100);

            double materialRate = 0;
            double laborRate = 0;

            for (int j = 0; j < reference.length; j++) {
                if (reference[j][0].equals(category) && name.contains(reference[j][1])) {
                    materialRate = Double.parseDouble(reference[j][3]);
                    laborRate = Double.parseDouble(reference[j][4]);
                    break;
                }
            }

            double materialCost = qtyWithWaste * materialRate;
            double laborCost = qtyWithWaste * laborRate;
            double tax = (materialCost + laborCost) * taxRate;
            double margin = (materialCost + laborCost) * marginRate;
            double total = materialCost + laborCost + tax + overhead + margin;

            System.out.printf("%s | %s | %.2f | ₹%.2f | ₹%.2f | ₹%.2f | ₹%.2f | ₹%.2f | ₹%.2f\n",
                    code, category, qtyWithWaste, materialCost, laborCost, tax, overhead, margin, total);
        }
    }
}
