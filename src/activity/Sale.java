package activity;

import customer.Buyer;
import staff.Staff;

import java.util.ArrayList;

public class Sale extends Activity {
    private Buyer buyer;

    public Sale(Staff provider) {
        super(provider);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void sellVehicleToBuyer(Buyer buyer) {

    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * Get next Buyer from the list.
     *
     * Assumption: first come first served
     *
     * @param buyers buyer list
     * @return first buyer from the list, null if empty list
     */
    private Buyer getNextBuyer(ArrayList<Buyer> buyers) {
        if (buyers.size() > 0) {
            return buyers.get(0);
        }
        return null;
    }
}
