package ru.jug.jps.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jug.jps.dto.Participant;

import java.util.List;

@Repository
public class ParticipantRepository {
    private static final RowMapper<Participant> PARTICIPANT_ROW_MAPPER = (resultSet, i) -> {
        Participant participant = new Participant();
        participant.setFirstName(resultSet.getString("first_name"));
        participant.setLastName(resultSet.getString("last_name"));
        participant.setEmail(resultSet.getString("email"));
        participant.setTicketNumber(resultSet.getString("ticket_number"));
        participant.setCompany(resultSet.getString("company_name"));
        return participant;
    };

    private final JdbcTemplate jdbcTemplate;

    public ParticipantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Participant> findByEitherField(String value) {
        String regexValue = "%" + value + "%";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("regexValue", regexValue);

        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedJdbcTemplate.query(
                "SELECT first_name, last_name, email, ticket_number, company_name " +
                        "FROM participants " +
                        "WHERE " +
                        "first_name LIKE :regexValue OR " +
                        "last_name LIKE :regexValue OR " +
                        "email LIKE :regexValue OR " +
                        "ticket_number LIKE :regexValue OR " +
                        "company_name LIKE :regexValue",
                parameters,
                PARTICIPANT_ROW_MAPPER
        );
    }

    public List<Participant> findAll() {
        return jdbcTemplate.query(
                "SELECT first_name, last_name, email, ticket_number, company_name " +
                        "FROM participants ",
                PARTICIPANT_ROW_MAPPER
        );
    }
}
