package myproject_springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getProductByName(String name) {
        // Чтение скрипта из resources
        String sql = read("getProductByName.sql");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name.toLowerCase());  // Используем строку в нижнем регистре для поиска

        // Выполнение запроса
        return jdbcTemplate.queryForObject(sql, params, String.class);
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
