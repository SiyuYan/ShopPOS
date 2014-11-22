import java.io.*;
import java.util.HashMap;
import java.util.Map;

import java.util.Iterator;
/**
 * Created by Administrator on 2014/11/21.
 */
public class Discount extends Promotion{
    Map<String,Double> discountInfo=new HashMap<String, Double>();

    public Discount(String filePath) throws Exception {
        //将filePath的文件读取到discountInfo中记录
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                this.discountInfo.put(line.split(":")[0],Double.parseDouble(line.split(":")[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle(Cart cart)
    {
        //重写逻辑，如何打折
        Iterator<Map.Entry<String, Double>> it = cart.itemsPrice.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, Double> entry = it.next();
            if(discountInfo.get(entry.getKey())!=null){
                //不为空，将打折后的新价钱放入newItemsPrice
                cart.newItemsPrice.put(entry.getKey(),discountInfo.get(entry.getKey())/100*entry.getValue());
            }else{
                //为空，将原价钱价钱放入newItemsPrice
                cart.newItemsPrice.put(entry.getKey(),entry.getValue());
            }
        }
    }
    }

