package io.ebean.platform.db2;

import io.ebean.BackgroundExecutor;
import io.ebean.annotation.Platform;
import io.ebean.config.dbplatform.*;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * DB2 specific platform.
 */
public abstract class BaseDB2Platform extends DatabasePlatform {

  public BaseDB2Platform() {
    super();
    this.platform = Platform.DB2;
    this.supportsNativeJavaTime = false;
    this.truncateTable = "truncate table %s reuse storage ignore delete triggers immediate";
    this.likeClauseRaw = "like ?";
    this.sqlLimiter = new AnsiSqlRowsLimiter();

    this.dbIdentity.setSupportsGetGeneratedKeys(true);
    this.dbIdentity.setSupportsSequence(true);
    this.idInExpandedForm = true; // Db2 does not support (a,b) in ((?,?),(?,?))
    this.exceptionTranslator =
      new SqlErrorCodes()
        .addAcquireLock("40001","57033") // key -911/-913
        .addDuplicateKey("23505") // -803
        // .addDataIntegrity("-407","-530","-531","-532","-543","-544","-545","-603","-667")
        // we need SQLState, not code: https://www.ibm.com/support/knowledgecenter/en/SSEPEK_10.0.0/codes/src/tpc/db2z_n.html
        .addDataIntegrity("23502","23503","23504","23511","23512","23511","42917","23515")
        .build();

    historySupport = new DB2HistorySupport();
    booleanDbType = Types.BOOLEAN;
    dbTypeMap.put(DbType.VARCHAR, new DbPlatformType("varchar", 255, 4000, dbTypeMap.get(DbType.CLOB)));
    dbTypeMap.put(DbType.VARBINARY, new DbPlatformType("varbinary", 255, 8000, dbTypeMap.get(DbType.BLOB)));
    dbTypeMap.put(DbType.TINYINT, new DbPlatformType("smallint", false));
    dbTypeMap.put(DbType.INTEGER, new DbPlatformType("integer", false));
    dbTypeMap.put(DbType.BIGINT, new DbPlatformType("bigint", false));
    dbTypeMap.put(DbType.REAL, new DbPlatformType("real"));
    dbTypeMap.put(DbType.DECIMAL, new DbPlatformType("decimal", 16, 3));
  }

  /**
   * Return a DB2 specific sequence IdGenerator that supports batch fetching
   * sequence values.
   */
  @Override
  public PlatformIdGenerator createSequenceIdGenerator(BackgroundExecutor be, DataSource ds, int stepSize, String seqName) {

    return new DB2SequenceIdGenerator(be, ds, seqName, sequenceBatchSize);
  }

}
