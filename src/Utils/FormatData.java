package utils;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class FormatData {

    // định dạng về dạng chuỗi 000.000.000 vnd
    public String formatPrice(Long price) {
        String s = price.toString();
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                sb.append(".");
                count = 0;
            }
        }

        return sb.reverse().toString() + " vnd";
    }
}