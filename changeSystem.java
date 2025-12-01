import java.text.DecimalFormat;

public class changeSystem {
		public static String getBreakdown(float change) {
			double[] denominations = {1000, 500, 100, 50, 20, 10, 5, 1, 0.25, 0.10, 0.05};
			DecimalFormat pesoFormat = new DecimalFormat("₱#,##0.00");
			StringBuilder breakdown = new StringBuilder("Change Breakdown: \n");
			double remaining = (double) change;
			
			breakdown.append(String.format("%-10s | %-5s\n", "Denomination", "Count"));
	        breakdown.append("---------------------------\n");
			
			for (double denom : denominations) {
				int count = (int) (remaining / denom);
				if (count > 0) {
					breakdown.append(
							String.format("%-10s | %-5d\n", pesoFormat.format(denom), count)
					);
					remaining = Math.round((remaining % denom) * 100.0) / 100.0;
				}
			}
			
			return breakdown.toString();
			
		}
}

