package com.xht.cloud.service.generate.gen;

import com.xht.cloud.code.generate.annotation.GenInfoAnnotation;
import com.xht.cloud.code.generate.annotation.Module;
import com.xht.cloud.code.generate.annotation.fields.FiledInfo;
import com.xht.cloud.code.generate.annotation.fields.Id;
import com.xht.cloud.code.generate.annotation.file.GenApi;
import com.xht.cloud.code.generate.annotation.file.GenApiImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileConvert;
import com.xht.cloud.code.generate.annotation.file.GenFileAddRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileController;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileDao;
import com.xht.cloud.code.generate.annotation.file.GenFileDaoImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileResponse;
import com.xht.cloud.code.generate.annotation.file.GenFileIService;
import com.xht.cloud.code.generate.annotation.file.GenFileMapper;
import com.xht.cloud.code.generate.annotation.file.GenFileMapperXml;
import com.xht.cloud.code.generate.annotation.file.GenFileRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileServiceImpl;
import com.xht.cloud.code.generate.annotation.file.GenPackageInfo;
import com.xht.cloud.code.generate.annotation.file.GenFileWrapper;
import com.xht.cloud.code.generate.annotation.file.GenSql;
import com.xht.cloud.code.generate.annotation.file.GenVue3AddOrUpdate;
import com.xht.cloud.code.generate.annotation.file.GenVue3Api;
import com.xht.cloud.code.generate.annotation.file.GenVue3Index;
import com.xht.cloud.code.generate.annotation.file.GenVue3Types;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：`代码生成业务字段`代码生成器所需实体
 *
 * @author : 小糊涂-代码生成器
 **/
@Data
@Module(value = "column", desc = "")
@GenInfoAnnotation(tableName = "gen_table_column", url = "/gen/table/column", tableComment = "代码生成业务字段", tableType = "BASE TABLE", tableSchema = "xht-cloud-generate", engine = "InnoDB", createTime = "2023-12-07 15:02:21", updateTime = "")
public class GenTableColumn {

    /**
     * 字段名字
     */
    @Id
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "id", describe = "字段名字", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String id;

    /**
     * 表id
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "table_id", describe = "表id", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String tableId;

    /**
     * 表所在的数据库名称
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "table_schema", describe = "表所在的数据库名称", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String tableSchema;

    /**
     * 表名称
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "table_name", describe = "表名称", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String tableName;

    /**
     * 字段名字
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_name", describe = "字段名字", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String columnName;

    /**
     * 字段长度
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_length", describe = "字段长度", columnDefault = "", length = 0, isNullable = true, dbType = "int")
    private Integer columnLength;

    /**
     * 字段名字
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_code_name", describe = "字段名字", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String columnCodeName;

    /**
     * 字段描述
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_comment", describe = "字段描述", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String columnComment;

    /**
     * 数据库字段类型
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_db_type", describe = "数据库字段类型", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String columnDbType;

    /**
     * Java字段类型
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_java_type", describe = "Java字段类型", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String columnJavaType;

    /**
     * Ts字段名称
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_ts_type", describe = "Ts字段名称", columnDefault = "", length = 255, isNullable = true, dbType = "varchar")
    private String columnTsType;

    /**
     * 字段值示例
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_example", describe = "字段值示例", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnExample;

    /**
     * 是否主键（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_pk", describe = "是否主键（1是）", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnPk;

    /**
     * 是否列表（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_list", describe = "是否列表（1是）", columnDefault = "", length = 1, isNullable = false, dbType = "char")
    private String columnList;

    /**
     * 是否增加（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_save", describe = "是否增加（1是）", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnSave;

    /**
     * 是否修改（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_update", describe = "是否修改（1是）", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnUpdate;

    /**
     * 是否查询（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_query", describe = "是否查询（1是）", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnQuery;

    /**
     * 是否必填（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_nullable", describe = "是否必填（1是）", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnNullable;

    /**
     * 是否字典（1是）
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_dict", describe = "是否字典（1是）", columnDefault = "0", length = 1, isNullable = false, dbType = "char")
    private String columnDict;

    /**
     * 字段显示
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_html", describe = "字段显示", columnDefault = "0", length = 100, isNullable = false, dbType = "varchar")
    private String columnHtml;

    /**
     * 字段排序
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "column_sort", describe = "字段排序", columnDefault = "", length = 0, isNullable = false, dbType = "int")
    private Integer columnSort;

    /**
     * 创建者
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "create_by", describe = "创建者", columnDefault = "", length = 64, isNullable = false, dbType = "varchar")
    private String createBy;

    /**
     * 更新者
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "update_by", describe = "更新者", columnDefault = "", length = 64, isNullable = false, dbType = "varchar")
    private String updateBy;

    /**
     * 创建时间
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "create_time", describe = "创建时间", columnDefault = "", length = 0, isNullable = false, dbType = "datetime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "update_time", describe = "更新时间", columnDefault = "", length = 0, isNullable = false, dbType = "datetime")
    private LocalDateTime updateTime;


}
