package skypro.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Конфигурационный класс, отвечающий за настройку деспетчер сервлет, благодаря которому все будет работать
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    // Определяет работу с рут конфиг классами, такого класса нет, поэтому возвращаем null
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Указываем конфигурационный класс
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{Config.class};
    }

    // Указываем запросы, которые необходимо орабатывать / - означает что обрабатываться будут все запросы
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
