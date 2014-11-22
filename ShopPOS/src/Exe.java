/**
 * Created by Administrator on 2014/11/18.
 */

public class Exe {

    public static void main(String[] args) throws Exception {
        Cart cart = new Cart();
        cart.getInfo("D:/cart.txt","D:/itemlist.txt");

        Discount dis = new Discount("D:/discount_promotion.txt");
        SecondHalf sh = new SecondHalf("D:/second_half_price_promotion.txt");

        cart.usePromotion(dis).usePromotion(sh);

        cart.giveMoney();
    }
}


