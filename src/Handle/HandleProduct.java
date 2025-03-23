package handle;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import domain.entity.Product;
import utils.constant.TargetEnum;

public class HandleProduct implements Handle<Product> {
    @Override
    public List<Product> read(String fileProducts) {
        List<Product> productsList = new ArrayList<>();
        try {
            // cú pháp đọc file
            File myFile = new File(fileProducts);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] products = data.split("\\?");
                Long codePhone = Long.parseLong(products[0]);
                String name = products[1];
                String brand = products[2];
                TargetEnum target = TargetEnum.valueOf(products[3]);
                Long price = Long.parseLong(products[4]);
                String description = products[5];
                Long stock = Long.parseLong(products[6]);
                productsList.add(new Product(codePhone, name, brand, target, price, description, stock));

            }
            sc.close();

        } catch (Exception ex) {
            // lỗi trong quá trình đọc
//            System.out.println("Error reading file: " + ex.getMessage());
        }
        return productsList;
    }

    @Override
    // ghi file xuống hàng và lấy định dạng theo dấu ?
    public void writeFile(String fileName, List<Product> product) {
        product.sort(Comparator.comparingLong(Product::getCode));
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Product x : product) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    // thêm 1 đối tượng bằng cách đọc hết cái file xong thêm rồi ghi nó theo định
    // dạng
    public void addNew(String fileName, Product product) {
        List<Product> proList = read("products.txt");
        proList.add(product);
        writeFile(fileName, proList);
    }

    @Override
    // xóa sản phẩm bằng iterator
    public void deleteIt(String fileName, Optional<?> codeOptional) {
        Long code = Long.parseLong(codeOptional.get().toString());
        List<Product> proList = read(fileName);
        boolean productFound = false;
        // interator hasNext xét xem còn phân tử nào duyệt qua không
        // nếu còn thì tiếp tục
        for (Iterator<Product> iterator = proList.iterator(); iterator.hasNext();) {
            Product product = iterator.next(); // lấy phần tử gán vao product
            if (product.getCode().equals(code)) {
                iterator.remove();
                productFound = true;
                break;
            }
        }
        if (productFound) {
            writeFile(fileName, proList);
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void delete(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        HandleProduct handle = new HandleProduct();
        List<Product> proList = handle.read(fileName);
        proList.removeIf(x -> x.getCode().equals(id));
        writeFile(fileName, proList);
    }

}
