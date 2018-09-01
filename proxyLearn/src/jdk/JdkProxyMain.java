package jdk;

public class JdkProxyMain {
    public static void main(String[] args) {
        DBQueryProxy proxy = new DBQueryProxy();
        IDBQuery query = (IDBQuery) proxy.bind(new DBQuery());
        System.out.println(query.request("1"));
        System.out.println("class:"+query.getClass());
   }
}
