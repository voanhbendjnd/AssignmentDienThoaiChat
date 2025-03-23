package handle;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Cart;
import setupFile.AllFile;

public class HandleCart implements Handle<Cart> {
    @Override
    public List<Cart> read(String fileOrder) {
        List<Cart> cartList = new ArrayList<>();
        try {
            // cú pháp đọc file
            File myFile = new File(fileOrder);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] orders = data.split("\\?");
                Long id = Long.parseLong(orders[0]);
                Long userId = Long.parseLong(orders[1]);
                String name = orders[2];
                Long price = Long.parseLong(orders[3]);
                Long qty = Long.parseLong(orders[4]);

                Long total = Long.parseLong(orders[5]);
                Long user = Long.parseLong(orders[6]);

                cartList.add(new Cart(id, userId, name, price, qty, total, user));

            }
            sc.close();

        } catch (Exception ex) {

        }
        return cartList;
    }

    @Override
    // ghi file xuống hàng và lấy định dạng theo dấu ?
    public void writeFile(String fileName, List<Cart> cartList) {
        if (cartList.isEmpty()) {
            new File(fileName).delete();
            return;
        }
        cartList.sort(Comparator.comparingLong(Cart::getId));
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Cart x : cartList) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    // thêm 1 đối tượng bằng cách đọc hết cái file xong thêm rồi ghi nó theo định
    // dạng
    public void addNew(String fileName, Cart cart) {
        List<Cart> cartList = read(AllFile.fileCartTxt);
        cartList.add(cart);
        writeFile(fileName, cartList);
    }

    @Override
    // xóa sản phẩm bằng iterator
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        List<Cart> cartList = read(fileName);
        boolean productFound = false;
        for (Iterator<Cart> iterator = cartList.iterator(); iterator.hasNext();) {
            Cart cart = iterator.next();
            if (cart.getId().equals(id)) {
                iterator.remove();
                productFound = true;
                break;
            }
        }
        if (productFound) {
            writeFile(fileName, cartList);
        }
    }

    @Override
    public void delete(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        HandleCart handleCart = new HandleCart();
        List<Cart> cartList = handleCart.read(fileName);
        cartList.removeIf(x -> x.getId().equals(id));
        handleCart.writeFile(fileName, cartList);
    }

}
