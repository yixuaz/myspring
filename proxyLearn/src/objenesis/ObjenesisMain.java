package objenesis;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

public class ObjenesisMain {
    public static void main(String[] args) {
        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator<User> thingyInstantiator = objenesis.getInstantiatorOf(User.class);
        User user = thingyInstantiator.newInstance();
        user.setName("hello");
        System.out.println(user.getName());
    }
}
