package online.zust.qcqcqc.utils.cglib;

public class UserService {
    public String getUserName(Long userId) {
        System.out.println("获取用户名..");
        return "user" + userId;
    }

    public void test() {
        System.out.println("test");
    }
}
