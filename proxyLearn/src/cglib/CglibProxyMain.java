package cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class CglibProxyMain {
    public static void main(String[] args) {
        DBQuery query = createProxy(new DBQuery());
        System.out.println(query.request("1"));
        System.out.println(query.request2("2"));
        System.out.println(query.getClass());
    }

    private static <T> T createProxy(Object obj) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallbacks(new Callback[]{
                new DBQueryInterceptor(obj), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("second");
                return methodProxy.invokeSuper(o,args);
            }
        }
        });
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if(method.getName().equals("request2"))
                    return 1;
                return 0;
            }
        });

        return (T) enhancer.create();
    }
}
