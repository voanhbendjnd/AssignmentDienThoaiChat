package handle;

import java.io.File;
/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import domain.entity.OrderUserImpl;
import setupFile.AllFile;

public class HandleOrderUser implements Handle<OrderUserImpl> {
    @Override
    public List<OrderUserImpl> read(String fileOrder) {
        List<OrderUserImpl> orderList = new ArrayList<>();
        try {
            // cú pháp đọc file
            File myFile = new File(fileOrder);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] orders = data.split("\\?");
                Long id = Long.parseLong(orders[0]);
                String nameP = orders[1];
                String name = orders[2];
                String address = orders[3];
                String phone = orders[4];
                Long userId = Long.parseLong(orders[5]);
                Long price = Long.parseLong(orders[6]);
                int status = Integer.parseInt(orders[7]);
                orderList.add(new OrderUserImpl(id, nameP, name, address, phone, userId, price, status));

            }
            sc.close();

        } catch (Exception ex) {

        }
        return orderList;
    }

    @Override
    // ghi file xuống hàng và lấy định dạng theo dấu ?
    public void writeFile(String fileName, List<OrderUserImpl> orderList) {
        orderList.sort(Comparator.comparingLong(OrderUserImpl::getId));
        try (FileWriter fw = new FileWriter(fileName)) {
            for (OrderUserImpl x : orderList) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    // thêm 1 đối tượng bằng cách đọc hết cái file xong thêm rồi ghi nó theo định
    // dạng
    public void addNew(String fileName, OrderUserImpl order) {
        List<OrderUserImpl> orderList = read(AllFile.fileOrderUserTxt);
        orderList.add(order);
        writeFile(fileName, orderList);
    }

    @Override
    // xóa sản phẩm bằng iterator
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        List<OrderUserImpl> orderList = read(fileName);
        boolean productFound = false;
        for (Iterator<OrderUserImpl> iterator = orderList.iterator(); iterator.hasNext();) {
            OrderUserImpl order = iterator.next();
            if (order.getId().equals(id)) {
                iterator.remove();
                productFound = true;
                break;
            }
        }
        if (productFound) {
            writeFile(fileName, orderList);
            // System.out.println("Product deleted successfully.");
        } else {
            // System.out.println("Product not found.");
        }
    }

    @Override
    public void delete(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        HandleOrderUser handle = new HandleOrderUser();
        List<OrderUserImpl> orderUserList = handle.read(fileName);
        orderUserList.removeIf(x -> x.getId().equals(id));
        handle.writeFile(fileName, orderUserList);
    }
}
