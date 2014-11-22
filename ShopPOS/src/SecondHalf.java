import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2014/11/21.
 */
public class SecondHalf extends Promotion {

    Set<String> halfInfo=new HashSet<String>();
    public SecondHalf(String filePath) throws Exception {
        //将filePath的文件读取到SecondHalf中记录
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                this.halfInfo.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Double halfNumber(Double d) {
        //参数为实际买的个数，通过计算第二个半价的关系推出新的需要计算的个数
        //  int i = (new Double(d).intValue());
        //  return d-0.5*i/2;

        Double rs = 0d;
        while (d>=2){
            rs+=1.5d;
            d-=2;
        }
        rs+=d;
        return rs;
    }
    public void handle(Cart cart) {
        //重写逻辑，什么情况才能半价
        Iterator<Map.Entry<String, Double>> it = cart.itemsNumbers.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, Double> entry = it.next();
            if(halfInfo.contains(entry.getKey())){
                //不为空，第二价半价。注意要大于2个
                Double oldNumber = cart.itemsNumbers.get(entry.getKey());
                if(oldNumber>=2d) {
                    Double newNumber = this.halfNumber(oldNumber);//老的个数变成新的个数
                    cart.newItemsNumbers.put(entry.getKey(), newNumber);
                }else {
                    cart.newItemsNumbers.put(entry.getKey(), oldNumber);//小于2个，买几个算几个
                }
            }else{
                //为空，数量不变
                cart.newItemsNumbers.put(entry.getKey(),entry.getValue());
            }
        }
    }
}






