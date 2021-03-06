package lab.dao;

import lab.domain.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class HsqlCountryDao extends JdbcDaoSupport implements CountryDao {
    private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ";

    private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
    private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
    private static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = '";
    private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = '";

    private static final String UPDATE_COUNTRY_NAME_SQL_1 = "update country SET name='";
    private static final String UPDATE_COUNTRY_NAME_SQL_2 = " where code_name='";

    private static final RowMapper<Country> COUNTRY_ROW_MAPPER =
            (rs, rowNum) -> Country.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .codeName(rs.getString("code_name"))
                    .build();

    @Override
    public List<Country> getCountryList() {
        return getJdbcTemplate().query(
                GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
    }

    @Override
    public List<Country> getCountryListStartWith(String name) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getDataSource());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
                "name", name + "%");
        return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL,
                sqlParameterSource, COUNTRY_ROW_MAPPER);
    }

    @Override
    public void updateCountryName(String codeName, String newCountryName) {
        getJdbcTemplate().execute(
                UPDATE_COUNTRY_NAME_SQL_1 + newCountryName + "'"
                        + UPDATE_COUNTRY_NAME_SQL_2 + codeName + "'");
    }

    @Override
    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA) {
            String sql = LOAD_COUNTRIES_SQL + "('" + countryData[0] + "', '"
                    + countryData[1] + "');";
            getJdbcTemplate().execute(sql);
        }
    }

    @Override
    public Country getCountryByCodeName(String codeName) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();

        String sql = GET_COUNTRY_BY_CODE_NAME_SQL + codeName + "'";
//		System.out.println(sql);

        return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER).get(0);
    }

    @Override
    public Country getCountryByName(String name)
            throws CountryNotFoundException {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<Country> countryList = jdbcTemplate.query(GET_COUNTRY_BY_NAME_SQL
                + name + "'", COUNTRY_ROW_MAPPER);
        if (countryList.isEmpty()) {
            throw new CountryNotFoundException();
        }
        return countryList.get(0);
    }
}
