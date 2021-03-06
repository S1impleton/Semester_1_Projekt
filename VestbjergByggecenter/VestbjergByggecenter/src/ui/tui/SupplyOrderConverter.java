package ui.tui;

import ui.libTUI.*;
import model.*;

public class SupplyOrderConverter implements ConverterIF<SupplyOrder> {

	@Override
	public String convertToString(SupplyOrder o) {
		if(! (o instanceof SupplyOrder)) {
			return "[Sorry, not a SupplyOrder object]";
		}
		SupplyOrder s = (SupplyOrder)o;
		return "Supplier: " + s.getSupplier();
	}
}
