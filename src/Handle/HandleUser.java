/**
 *
 * @author Vo Anh Ben - CE190709
 */
package handle;

import domain.entity.User;
import setupFile.AllFile;
import utils.constant.RoleEnum;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class HandleUser implements Handle<User> {
    @Override
    public List<User> read(String fileProducts) {
        List<User> accountList = new ArrayList<>();
        try {
            // cú pháp đọc file
            File myFile = new File(fileProducts);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] accounts = data.split("\\?");
                Long id = Long.parseLong(accounts[0]);
                String username = accounts[1];
                String password = accounts[2];
                String email = accounts[3];
                RoleEnum role = RoleEnum.valueOf(accounts[4]);
                accountList.add(new User(id, username, password, email, role));

            }
            sc.close();

        } catch (Exception ex) {
            // lỗi trong quá trình đọc
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return accountList;
    }

    @Override
    // ghi file xuống hàng và lấy định dạng theo dấu ?
    public void writeFile(String fileName, List<User> account) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (User x : account) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    // thêm 1 đối tượng bằng cách đọc hết cái file xong thêm rồi ghi nó theo định
    // dạng
    public void addNew(String fileName, User account) {
        List<User> accountList = read(AllFile.fileAccountTxt);
        accountList.add(account);
        writeFile(fileName, accountList);
    }

    @Override
    // xóa sản phẩm bằng iterator
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        List<User> accountList = read(fileName);
        boolean accountFound = false;
        for (Iterator<User> iterator = accountList.iterator(); iterator.hasNext();) {
            User account = iterator.next();
            if (account.getId().equals(id)) {
                iterator.remove();
                accountFound = true;
                break;
            }
        }
        if (accountFound) {
            writeFile(fileName, accountList);
        }
    }

    @Override
    public void delete(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        HandleUser handleUser = new HandleUser();
        List<User> userList = handleUser.read(fileName);
        userList.removeIf(x -> x.getId().equals(id));
        handleUser.writeFile(fileName, userList);
    }
}