package kroryi.yiiib.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplateCreator extends HttpServlet {
    private static JdbcTemplate jdbcTemplate;
    private Properties properties;


    @Override
    public void init() throws ServletException {
        loadProperties();
        jdbcTemplate = createJdbcTemplate();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 코드 추가
        }
    }

    private JdbcTemplate createJdbcTemplate() {
//        String url = "jdbc:mysql://localhost:3306/ynbank?useUnicode=true&characterEncoding=utf8";
//        String user = "root";
//        String password = "1234";
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    public static JdbcTemplate getInstance() {
        return jdbcTemplate;
    }

    public static Connection getConnect() throws SQLException {
        return new JdbcTemplateCreator().createJdbcTemplate().getDataSource().getConnection();
    }
}
