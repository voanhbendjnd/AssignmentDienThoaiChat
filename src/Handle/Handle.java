/**
 *
 * @author Vo Anh Ben - CE190709
 */
package handle;

import java.util.List;
import java.util.Optional;

// các class handle sẽ kế thừa từ class này
public interface Handle<T> {
    // đọc file
    public List<T> read(String file);

    // ghi file
    public void writeFile(String fileName, List<T> list);

    // thêm mới vào file
    // trên thực tế cơ bản tính năng này không cần
    // do trong quá trình làm quá nhiều class nếu sửa lại thì sẽ tạo ra bug không
    // mong muốn
    // nên nhóm chon cách giữa hàm này lại
    // hàm này sẽ lưu 1 đối tượng rồi gọi writeFile ở trên
    // cách nhanh và tiết kiệm code hơn là dùng chỉ cần dùng writeFile để lưu
    public void addNew(String fileName, T t);

    // xóa 1 với file
    public void deleteIt(String fileName, Optional<?> kei);

    public void delete(String fileName, Optional<?> kei);
}
