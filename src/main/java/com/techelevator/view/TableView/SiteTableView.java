package com.techelevator.view.TableView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import com.techelevator.model.Site;

public final class SiteTableView extends TableView {
	
	private List<Site> sites;
	private String header;
	private BigDecimal cost;
	
	/**
	 * Creates a tableview for sites.
	 * 
	 * @param sites the sites to display in the table
	 * @param header the header of the table
	 * @param cost the cost for the sites
	 */
	public SiteTableView(List<Site> sites, String header, BigDecimal cost) {
		this.sites = sites;
		this.header = header;
		this.cost = cost;
	}

	@Override
	public void displayTableView() {
		out.println();
		out.println(header);
		out.println();
		out.printf("%-10s %-10s %-15s %-20s %-10s %10s\n","Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "Cost");
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		for (int i = 1; i <= sites.size(); i++) {
			Site site = sites.get(i-1);
			out.printf("%-10s %-10s %-15s %-20s %-10s %10s\n",site.getSiteNumber(), site.getMaxOccupancy(),
					(site.isAccessible()) ? "Yes" : "No", site.getMaxRVLength(), (site.isUtilities()) ? "Yes" : "No", formatter.format(cost));
		}
		out.flush();
	}

}
