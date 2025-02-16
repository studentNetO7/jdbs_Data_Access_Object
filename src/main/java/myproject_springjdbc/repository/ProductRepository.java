package myproject_springjdbc.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.List;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String sql;

    // Читаем SQL-скрипт в конструкторе
    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.sql = read("getProductByName.sql");  // Считываем SQL-скрипт один раз при создании объекта
    }

    // Метод может возвращать список строк (возможно, несколько продуктов)
    public List<String> getProductByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name.toLowerCase());  // Используем строку в нижнем регистре для поиска

        // Используем query для возвращения списка строк
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getString("product_name"));
    }

    private String read(String scriptFileName) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(scriptFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL script", e);
        }
    }
}
