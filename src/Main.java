import customer.Buyer;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Buyer buyer = new Buyer();
        buyer.setBuyingType(Buyer.BuyingType.JUST_LOOKING);
        System.out.println(buyer.getBuyingType().getProbability());
    }
}
