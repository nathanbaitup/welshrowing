package nsa.group7.welshrowing.security;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
//public class TLSConfig {
//
//    public static void main (String[] args) {
//        SpringApplication.run(TLSConfig.class, args);
//    }
//
//    @Value(value = "${http.port}")
//    private int httpPort;
//
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
//        return tomcat;
//    }
//
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(httpPort);
//        return connector;
//    }
//
//}
//
////    public TomcatServletWebServerFactory servletContainer() {
////        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
////            @Override
////            protected void postProcessContext (Context context){
////            SecurityConstraint securityConstraint = new SecurityConstraint();
////            securityConstraint.setUserConstraint("CONFIDENTIAL");
////            SecurityCollection collection = new SecurityCollection();
////            collection.addPattern("/*");
////            securityConstraint.addCollection(collection);
////            context.addConstraint(securityConstraint);
////        }
////        };
////
////        tomcat.addAdditionalTomcatConnectors(initateHttpConnector());
////        return tomcat;
////        }
//
////    private Connector redirectConnector() {
////        return new Connector(Http11NioProtocol.class.getName()) {{
////            setScheme("http");
////            setPort(8080);
////            setSecure(false);
////            setRedirectPort(8443);
////        }};
////    }
//// }
