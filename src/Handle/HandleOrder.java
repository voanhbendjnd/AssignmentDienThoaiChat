/**
 *
 * @author Vo Anh Ben - CE190709
 */
package handle;

import domain.entity.OrderImpl;
import setupFile.AllFile;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class HandleOrder implements Handle<OrderImpl> {
    @Override
    public List<OrderImpl> read(String fileOrder) {
        List<OrderImpl> orderList = new ArrayList<>();
        try {
            // cú pháp đọc file
            File myFile = new File(fileOrder);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] orders = data.split("\\?");
                Long id = Long.parseLong(orders[0]);
                Long id_user = Long.parseLong(orders[1]);
                Long product_id = Long.parseLong(orders[2]);
                String name = orders[3];
                String address = orders[4];
                String phone = orders[5];
                Long price = Long.parseLong(orders[6]);
                Long orderId = Long.parseLong(orders[7]);
                orderList.add(new OrderImpl(id, id_user, product_id, name, address, phone, price, orderId));

            }
            sc.close();

        } catch (Exception ex) {

        }
        return orderList;
    }

    @Override
    // ghi file xuống hàng và lấy định dạng theo dấu ?
    public void writeFile(String fileName, List<OrderImpl> orderList) {
        orderList.sort(Comparator.comparingLong(OrderImpl::getId));
        try (FileWriter fw = new FileWriter(fileName)) {
            for (OrderImpl x : orderList) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    // thêm 1 đối tượng bằng cách đọc hết cái file xong thêm rồi ghi nó theo định
    // dạng
    public void addNew(String fileName, OrderImpl order) {
        List<OrderImpl> orderList = read(AllFile.fileOrderTxt);
        orderList.add(order);
        writeFile(fileName, orderList);
    }

    @Override
    // xóa sản phẩm bằng iterator
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        List<OrderImpl> orderList = read(fileName);
        boolean orderFound = false;
        for (Iterator<OrderImpl> iterator = orderList.iterator(); iterator.hasNext();) {
            OrderImpl order = iterator.next();
            if (order.getId().equals(id)) {
                iterator.remove();
                orderFound = true;
                break;
            }
        }
        if (orderFound) {
            writeFile(fileName, orderList);
        }
    }

    @Override
    public void delete(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        HandleOrder handleOrder = new HandleOrder();
        List<OrderImpl> orderList = handleOrder.read(fileName);
        orderList.removeIf(x -> x.getId().equals(id));
        handleOrder.writeFile(fileName, orderList);
    }

}
