package kroryi.yiiib.dao;

import java.util.List;

public interface DirectDao {
    List<MyData> select( String tableName, String strWhere, String... args ) ;
    int insert( String tableName, String columnDefine, String... args ) ;
    int update( String tableName, String[] columns, String... args ) ;
    int delete( String tableName, String keyColName, Object keyValue ) ;
}
