package utils;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.List;

import domain.entity.User;
import handle.HandleUser;
import setupFile.AllFile;
import utils.constant.RoleEnum;

// class nay mục đích là để tạo ra super admin, vì không thể tạo người dùng với vai trò admin được
public class InitializeSuperAdmin {
    public void init() {
        Long code = 0L;
        List<User> accList = new HandleUser().read(AllFile.fileAccountTxt);
        boolean existsByNameAndEmail = accList.stream()
                .anyMatch(x -> x.getEmail().equals("admin@gmail.com"));
        if (!existsByNameAndEmail) {

            if (accList.isEmpty()) {
                code = 0L;
            } else {
                for (User x : accList) {
                    if (x.getId() >= code) {
                        code = x.getId();
                    }
                }
            }
            new HandleUser().addNew(AllFile.fileAccountTxt,
                    new User(code + 1, "admin", "123456", "admin@gmail.com", RoleEnum.ADMIN));
        }
    }
}
