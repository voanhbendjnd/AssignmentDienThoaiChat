/**
 *
 * @author Vo Anh Ben - CE190709
 */
package utils.error;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.entity.User;
import handle.HandleUser;
import setupFile.AllFile;

// check xem người dùng nhập thông tin vào có đúng không, nếu bỏ trống hay không đúng định dạng thì ném ra lỗi
// đồng thời check luôn cái email có tồn tại trong data chưa
public class ValidationUser {
    public String validUser(String name, String email, String password) {
        List<String> sb = new ArrayList<>();
        if (name.trim().isEmpty()) {
            sb.add("Username not empty!");
        }
        if (email.trim().isEmpty() || !email.endsWith("@gmail.com") || email.equals("@gmail.com")) {
            sb.add("Email not empty or uncorrect format!");
        }
        if (password.length() < 8) {
            sb.add("Password must be more than 8 characters");
        }
        List<User> accList = new HandleUser().read(AllFile.fileAccountTxt);
        boolean existsAccount = accList.stream()
                .anyMatch(x -> x.getEmail().equals(email));

        if (existsAccount && sb.isEmpty()) {
            sb.add("Email already exists!");
            sb.add("Please select other email!");
        }
        String s = sb.stream()
                .collect(Collectors.joining("\n"));
        return sb.isEmpty() ? "validation" : s;

    }
}
