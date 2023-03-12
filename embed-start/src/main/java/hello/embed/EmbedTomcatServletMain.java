package hello.embed;

import hello.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class EmbedTomcatServletMain {

    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatServletMain.main");

        // Tomcat 생성 및 설정
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // Servlet 등록
        Context servletContext = tomcat.addContext("", "/");

        String servletName = "helloServlet";
        tomcat.addServlet("", servletName, new HelloServlet());
        servletContext.addServletMappingDecoded("/hello-servlet", servletName);
        tomcat.start();
    }
}
