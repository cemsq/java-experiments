package com.cg.jgen.utils;

import com.cg.jgen.core.exception.KeyWordException;
import com.cgm.storm.utils.common.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Checks if a database keyword is used
 */
public class KeyWordChecker {

    private static List<String> sqlServerKeywordList = Arrays.asList("ADD", "ALL", "ALTER", "AND", "ANY", "AS", "ASC",
            "AUTHORIZATION", "BACKUP", "BEGIN", "BETWEEN", "BREAK", "BROWSE", "BULK", "BY", "CASCADE", "CASE",
            "CHECK", "CHECKPOINT", "CLOSE", "CLUSTERED", "COALESCE", "COLLATE", "COLUMN", "COMMIT", "COMPUTE",
            "CONSTRAINT", "CONTAINS", "CONTAINSTABLE", "CONTINUE", "CONVERT", "CREATE", "CROSS", "CURRENT",
            "CURRENT_DATE", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_USER", "CURSOR", "DATABASE", "DBCC",
            "DEALLOCATE", "DECLARE", "DEFAULT", "DELETE", "DENY", "DESC", "DISK", "DISTINCT", "DISTRIBUTED",
            "DOUBLE", "DROP", "DUMMY", "DUMP", "ELSE", "END", "ERRLVL", "ESCAPE", "EXCEPT", "EXEC", "EXECUTE",
            "EXISTS", "EXIT", "FETCH", "FILE", "FILLFACTOR", "FOR", "FOREIGN", "FREETEXT", "FREETEXTTABLE", "FROM",
            "FULL", "FUNCTION", "GOTO", "GRANT", "GROUP", "HAVING", "HOLDLOCK", "IDENTITY", "IDENTITY_INSERT",
            "IDENTITYCOL", "IF", "IN", "INDEX", "INNER", "INSERT", "INTERSECT", "INTO", "IS", "JOIN", "KEY", "KILL",
            "LEFT", "LIKE", "LINENO", "LOAD", "NATIONAL", "NOCHECK", "NONCLUSTERED", "NOT", "NULL", "NULLIF", "OF",
            "OFF", "OFFSETS", "ON", "OPEN", "OPENDATASOURCE", "OPENQUERY", "OPENROWSET", "OPENXML", "OPTION", "OR",
            "ORDER", "OUTER", "OVER", "PERCENT", "PLAN", "PRECISION", "PRIMARY", "PRINT", "PROC", "PROCEDURE",
            "PUBLIC", "RAISERROR", "READ", "READTEXT", "RECONFIGURE", "REFERENCES", "REPLICATION", "RESTORE",
            "RESTRICT", "RETURN", "REVOKE", "RIGHT", "ROLLBACK", "ROWCOUNT", "ROWGUIDCOL", "RULE", "SAVE", "SCHEMA",
            "SELECT", "SESSION_USER", "SET", "SETUSER", "SHUTDOWN", "SOME", "STATISTICS", "SYSTEM_USER", "TABLE",
            "TEXTSIZE", "THEN", "TO", "TOP", "TRAN", "TRANSACTION", "TRIGGER", "TRUNCATE", "TSEQUAL", "UNION",
            "UNIQUE", "UPDATE", "UPDATETEXT", "USE", "USER", "VALUES", "VARYING", "VIEW", "WAITFOR", "WHEN", "WHERE",
            "WHILE", "WITH", "WRITETEXT");
    private static List<String> oracleReservedWordList = Arrays.asList("ACCESS", "ADD", "ALL", "ALTER", "AND", "ANY",
            "ARRAYLEN", "AS", "ASC", "AUDIT", "BETWEEN", "BY", "CHAR", "CHECK", "CLUSTER", "COLUMN", "COMMENT",
            "COMPRESS", "CONNECT", "CREATE", "CURRENT", "DATE", "DECIMAL", "DEFAULT", "DELETE", "DESC", "DISTINCT",
            "DROP", "ELSE", "EXCLUSIVE", "EXISTS", "FILE", "FLOAT", "FOR", "FROM", "GRANT", "GROUP", "HAVING",
            "IDENTIFIED", "IMMEDIATE", "IN", "INCREMENT", "INDEX", "INITIAL", "INSERT", "INTEGER", "INTERSECT",
            "INTO", "IS", "LEVEL", "LIKE", "LOCK", "LONG", "MAXEXTENTS", "MINUS", "MODE", "MODIFY", "NOAUDIT",
            "NOCOMPRESS", "NOT", "NOTFOUND", "NOWAIT", "NULL", "NUMBER", "OF", "OFFLINE", "ON", "ONLINE", "OPTION",
            "OR", "ORDER", "PCTFREE", "PRIOR", "PRIVILEGES", "PUBLIC", "RAW", "RENAME", "RESOURCE", "REVOKE", "ROW",
            "ROWID", "ROWLABEL", "ROWNUM", "ROWS", "START", "SELECT", "SESSION", "SET", "SHARE", "SIZE", "SMALLINT",
            "SQLBUF", "SUCCESSFUL", "SYNONYM", "SYSDATE", "TABLE", "THEN", "TO", "TRIGGER", "UID", "UNION", "UNIQUE",
            "UPDATE", "USER", "VALIDATE", "VALUES", "VARCHAR", "VARCHAR2", "VIEW", "WHENEVER", "WHERE", "WITH");
    private static List<String> oracleKeywordList = Arrays.asList("ADMIN", "AFTER", "ALLOCATE", "ANALYZE", "ARCHIVE",
            "ARCHIVELOG", "AUTHORIZATION", "AVG", "BACKUP", "BEGIN", "BECOME", "BEFORE", "BLOCK", "BODY", "CACHE",
            "CANCEL", "CASCADE", "CHANGE", "CHARACTER", "CHECKPOINT", "CLOSE", "COBOL", "COMMIT", "COMPILE",
            "CONSTRAINT", "CONSTRAINTS", "CONTENTS", "CONTINUE", "CONTROLFILE", "COUNT", "CURSOR", "CYCLE",
            "DATABASE", "DATAFILE", "DBA", "DEC", "DECLARE", "DISABLE", "DISMOUNT", "DOUBLE", "DUMP", "EACH",
            "ENABLE", "END", "ESCAPE", "EVENTS", "EXCEPT", "EXCEPTIONS", "EXEC", "EXPLAIN", "EXECUTE", "EXTENT",
            "EXTERNALLY", "FETCH", "FLUSH", "FREELIST", "FREELISTS", "FORCE", "FOREIGN", "FORTRAN", "FOUND",
            "FUNCTION", "GO", "GOTO", "GROUPS", "INCLUDING", "INDICATOR", "INITRANS", "INSTANCE", "INT", "KEY",
            "LANGUAGE", "LAYER", "LINK", "LISTS", "LOGFILE", "MANAGE", "MANUAL", "MAX", "MAXDATAFILES", "MAXINSTANCES",
            "MAXLOGFILES", "MAXLOGHISTORY", "MAXLOGMEMBERS", "MAXTRANS", "MAXVALUE", "MIN", "MINEXTENTS", "MINVALUE",
            "MODULE", "MOUNT", "NEXT", "NEW", "NOARCHIVELOG", "NOCACHE", "NOCYCLE", "NOMAXVALUE", "NOMINVALUE",
            "NONE", "NOORDER", "NORESETLOGS", "NORMAL", "NOSORT", "NUMERIC", "OFF", "OLD", "ONLY", "OPEN", "OPTIMAL",
            "OWN", "PACKAGE", "PARALLEL", "PCTINCREASE", "PCTUSED", "PLAN", "PLI", "PRECISION", "PRIMARY", "PRIVATE",
            "PROCEDURE", "PROFILE", "QUOTA", "READ", "REAL", "RECOVER", "REFERENCES", "REFERENCING", "RESETLOGS",
            "RESTRICTED", "REUSE", "ROLE", "ROLES", "ROLLBACK", "SAVEPOINT", "SCHEMA", "SCN", "SECTION", "SEGMENT",
            "SEQUENCE", "SHARED", "SNAPSHOT", "SOME", "SORT", "SQL", "SQLCODE", "SQLERROR", "SQLSTATE", "STATEMENT_ID",
            "STATISTICS", "STOP", "STORAGE", "SUM", "SWITCH", "SYSTEM", "TABLES", "TABLESPACE", "TEMPORARY", "THREAD",
            "TIME", "TRACING", "TRANSACTION", "TRIGGERS", "TRUNCATE", "UNDER", "UNLIMITED", "UNTIL", "USE", "USING",
            "WHEN", "WRITE", "WORK");
    private static List<String> oraclePlSqlReservedWordList = Arrays.asList("ABORT", "ACCEPT", "ACCESS", "ADD", "ALL",
            "ALTER", "AND", "ANY", "ARRAY", "ARRAYLEN", "AS", "ASC", "ASSERT", "ASSIGN", "AT", "AUTHORIZATION", "AVG",
            "BASE_TABLE", "BEGIN", "BETWEEN", "BINARY_INTEGER", "BODY", "BOOLEAN", "BY", "CASE", "CHAR", "CHAR_BASE",
            "CHECK", "CLOSE", "CLUSTER", "CLUSTERS", "COLAUTH", "COLUMNS", "COMMIT", "COMPRESS", "CONNECT", "CONSTANT",
            "COUNT", "CRASH", "CREATE", "CURRENT", "CURRVAL", "CURSOR", "DATABASE", "DATA_BASE", "DATE", "DBA",
            "DEBUGOFF", "DEBUGON", "DECLARE", "DECIMAL", "DEFAULT", "DEFINITION", "DELAY", "DELETE", "DELTA", "DESC",
            "DIGITS", "DISPOSE", "DISTINCT", "DO", "DROP", "ELSE", "ELSIF", "END", "ENTRY", "EXCEPTION",
            "EXCEPTION_INIT", "EXISTS", "EXIT", "FALSE", "FETCH", "FLOAT", "FOR", "FORM", "FROM", "FUNCTION", "GENERIC",
            "GOTO", "GRANT", "GROUP", "HAVING", "IDENTIFIED", "IF", "IN", "INDEX", "INDEXES", "INDICATOR", "INSERT",
            "INTEGER", "INTERSECT", "INTO", "IS", "LEVEL", "LIKE", "LIMITED", "LOOP", "MAX", "MIN", "MINUS", "MLSLABEL",
            "MOD", "MODE", "NATURAL", "NEW", "NEXTVAL", "NOCOMPRESS", "NOT", "NULL", "NUMBER", "NUMBER_BASE", "OF",
            "ON", "OPEN", "OPTION", "OR", "ORDER", "OTHERS", "OUT", "PACKAGE", "PARTITION", "PCTFREE", "POSITIVE",
            "PRAGMA", "PRIOR", "PRIVATE", "PROCEDURE", "PUBLIC", "RAISE", "RANGE", "REAL", "RECORD", "RELEASE", "REMR",
            "RENAME", "RESOURCE", "RETURN", "REVERSE", "REVOKE", "ROLLBACK", "ROWID", "ROWLABEL", "ROWNUM", "ROWTYPE",
            "RUN", "SAVEPOINT", "SCHEMA", "SELECT", "SEPARATE", "SET", "SIZE", "SMALLINT", "SPACE", "SQL", "SQLCODE",
            "SQLERRM", "START", "STATEMENT", "STDDEV", "SUBTYPE", "SUM", "TABAUTH", "TABLE", "TABLES", "TASK",
            "TERMINATE", "THEN", "TO", "TRUE", "TYPE", "UNION", "UNIQUE", "UPDATE", "USE", "VALUES", "VARCHAR",
            "VARCHAR2", "VARIANCE", "VIEW", "VIEWS", "WHEN", "WHERE", "WHILE", "WITH", "WORK", "XOR");
    private static List<String> h2keywordList = Arrays.asList("CROSS", "CURRENT_DATE", "CURRENT_TIME",
            "CURRENT_TIMESTAMP", "DISTINCT", "EXCEPT", "EXISTS", "FALSE", "FETCH", "FOR", "FROM", "FULL", "GROUP",
            "HAVING", "INNER", "INTERSECT", "IS", "JOIN", "LIKE", "LIMIT", "MINUS", "NATURAL", "NOT", "NULL", "OFFSET",
            "ON", "ORDER", "PRIMARY", "ROWNUM", "SELECT", "SYSDATE", "SYSTIME", "SYSTIMESTAMP", "TODAY", "TRUE",
            "UNION", "UNIQUE", "WHERE");


//    KeyWordChecker.checkKeyWord(name, "Index");

    public static void checkKeyWord(String field, String typeOfObject, String table) {

        String msg;
        field = field.toUpperCase();

        if (StringUtils.isValid(table)) {
            msg = typeOfObject + " name '" + field + "' (table " + table + ")";
        } else {
            msg = typeOfObject + " name '" + field + "'";
        }

        if (sqlServerKeywordList.contains(field)) {
            throw new KeyWordException(msg + " is a key word in SQL Server ");
        } else if (oracleReservedWordList.contains(field)) {
            throw new KeyWordException(msg + " is a reserved word in Oracle");
        } else if (oracleKeywordList.contains(field)) {
            throw new KeyWordException(msg + " is a key word in Oracle");
        } else if (oraclePlSqlReservedWordList.contains(field)) {
            throw new KeyWordException(msg + " is a reserved word in PL/SQL ");
        } else if (h2keywordList.contains(field)) {
            throw new KeyWordException(msg + " is a key word in H2");
        }
    }

}
