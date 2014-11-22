/**
 * Created by Administrator on 2014/11/19.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {

    public Map<String,Double> itemsNumbers = new HashMap<String, Double>();
    public Map<String,Double> itemsPrice = new HashMap<String, Double>();

    public Map<String,Double> newItemsNumbers = new HashMap<String, Double>();
    public Map<String,Double> newItemsPrice = new HashMap<String, Double>();

    public Cart usePromotion(Promotion p){
        p.handle(this);
        return this;
    }
    public void getInfo(String cartFile, String itemlistFile){
        this.getItemsPrice(itemlistFile);
        this.getItemsNumbers(cartFile);
    }
    public void getItemsNumbers(String cartFile){
        try {
            FileReader fr = new FileReader(cartFile);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line=br.readLine())!=null) {
                if (line.split("-").length == 1) {
                    if (this.itemsNumbers.get(line) == null) {
                        this.itemsNumbers.put(line, 1d);
                    } else {
                        this.itemsNumbers.put(line, this.itemsNumbers.get(line) + 1);
                    }
                } else {
                    if (this.itemsNumbers.get(line.split("-")[0]) == null) {
                        this.itemsNumbers.put(line.split("-")[0], Double.parseDouble(line.split("-")[1]));
                    } else {
                        this.itemsNumbers.put(line.split("-")[0], this.itemsNumbers.get(line.split("-")[0]) + Double.parseDouble(line.split("-")[1]));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getItemsPrice(String itemlistFile){
        try {
            FileReader fr = new FileReader(itemlistFile);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line=br.readLine())!=null){
                this.itemsPrice.put(line.split(":")[0],Double.parseDouble(line.split(":")[1]));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void giveMoney() {
        //打印最后的购物清单的逻辑，即结账的逻辑

//        购物明细（数量 单价 小计）
//        item1            5   40    120
//        item3            2   50    75
//        item5            4   60    216
//
//        总计金额（优惠前  优惠后  优惠差价）
//        411         540    411     129

        System.out.println("购物明细   （数量  单价  小计）");
        Iterator<Map.Entry<String, Double>> it = itemsNumbers.entrySet().iterator();
        Double totalOld = 0d;
        Double totalNew = 0d;
        while (it.hasNext()){
            Map.Entry<String, Double> entry = it.next();
            System.out.println("");
            System.out.print(entry.getKey()+"   ");
            System.out.print(entry.getValue()+" ");
            System.out.print(itemsPrice.get(entry.getKey())+" ");
            totalNew+=newItemsNumbers.get(entry.getKey())*newItemsPrice.get(entry.getKey());
            System.out.print(newItemsNumbers.get(entry.getKey())*newItemsPrice.get(entry.getKey()));
            totalOld+=itemsNumbers.get(entry.getKey())*itemsPrice.get(entry.getKey());
        }
        System.out.println("");
        System.out.println("总计金额（优惠前  优惠后  优惠差价）");
        Double diff = totalOld-totalNew;
        System.out.println(totalNew+" "+totalOld+" "+totalNew+" "+diff);
        System.out.println("");
    }
    }
