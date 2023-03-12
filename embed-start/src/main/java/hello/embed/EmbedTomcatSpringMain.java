package hello.embed;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbedTomcatSpringMain {

    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatSpringMain.main");

        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // Spring Container 생성
        AnnotationConfigWebApplicationContext appContext
            = new AnnotationConfigWebApplicationContext();

        appContext.register(HelloConfig.class);

        // DispatcherServlet 생성, Spring Container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // DispatcherServlet 등록
        Context servletContext = tomcat.addContext("", "/");
        tomcat.addServlet("", "dispatcher", dispatcherServlet);
        servletContext.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
    }
}
