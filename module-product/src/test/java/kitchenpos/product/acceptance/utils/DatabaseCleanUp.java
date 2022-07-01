package kitchenpos.product.acceptance.utils;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

@Service
@ActiveProfiles("test")
public class DatabaseCleanUp {
    private static final String TRUNCATE_COMMAND = "truncate table ";
    private static final String FOREIGN_KEY_CHECKS_UNLOCK = "SET FOREIGN_KEY_CHECKS = 0";
    private static final String FOREIGN_KEY_CHECKS_LOCK = "SET FOREIGN_KEY_CHECKS = 1";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final List<String> tables;

    public DatabaseCleanUp(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        tables = Collections.singletonList("product");
    }

    @Transactional
    public void execute() {
        jdbcTemplate.execute(FOREIGN_KEY_CHECKS_UNLOCK, PreparedStatement::executeUpdate);
        for (String table : tables) {
            String sql = TRUNCATE_COMMAND + table;
            jdbcTemplate.execute(sql, PreparedStatement::executeUpdate);
        }

        jdbcTemplate.execute(FOREIGN_KEY_CHECKS_LOCK, PreparedStatement::executeUpdate);
    }
}