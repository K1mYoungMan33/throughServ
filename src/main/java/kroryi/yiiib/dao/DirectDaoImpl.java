package kroryi.yiiib.dao;

import kroryi.yiiib.jdbc.JdbcTemplateCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class DirectDaoImpl implements DirectDao {
    private static DirectDao instance = new DirectDaoImpl();

    public static DirectDao getInstance() {
        return instance;
    }

    public static class ObjectRowMapper implements RowMapper<MyData> {
        @Override
        public MyData mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Map<String, Map<String, Object>> nestedMap = new HashMap<>();
            MyData data = new MyData();

            // 칼럼 수 가져오기
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnType = metaData.getColumnTypeName(i);
                Object columnValue = rs.getObject(i);

                // 데이터 추가
                data.map.put( columnName, new HashMap<>());


                // 여기에서 columnValue를 적절한 타입으로 변환하여 사용
                if (columnValue instanceof String) {
                    String stringValue = (String) columnValue;

                    data.map.get( columnName ).put( columnType, stringValue );
                } else if (columnValue instanceof Integer) {
                    int intValue = (Integer) columnValue;

                    data.map.get( columnName ).put( columnType, intValue );

                } else if (columnValue instanceof Date) {
//                } else if ( "DATETIME".equals( columnType ) ) {
                    LocalDateTime  writeDate = (LocalDateTime) columnValue;

                    data.map.get( columnName ).put( columnType, writeDate );
                } else {
                    data.map.get( columnName ).put( columnType, "no match data type (" + columnType + ")" );
                }
            }

            return data;
        }
    }


    @Override
    public List<MyData> select(String tableName, String strWhere, String... args) {
        String sql = "select * from " + tableName;
        if ( null != strWhere && !strWhere.isEmpty() )
            sql += " where " + strWhere;

        System.out.println( "DirectDaoImpl select \n" + sql );

        JdbcTemplate jdbcTemplate = JdbcTemplateCreator.getInstance();
        return jdbcTemplate.query( sql, new ObjectRowMapper(), args );
    }

    @Override
    public int insert(String tableName, String columnDefine, String... args) {
        return 0;
    }

    @Override
    public int update(String tableName, String[] columns, String... args) {
        return 0;
    }

    @Override
    public int delete(String tableName, String keyColName, Object keyValue) {
        return 0;
    }
}
