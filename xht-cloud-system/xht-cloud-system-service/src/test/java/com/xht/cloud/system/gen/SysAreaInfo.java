package com.xht.cloud.system.gen;

import com.xht.cloud.code.generate.annotation.GenInfoAnnotation;
import com.xht.cloud.code.generate.annotation.Module;
import com.xht.cloud.code.generate.annotation.fields.FiledInfo;
import com.xht.cloud.code.generate.annotation.fields.Id;
import com.xht.cloud.code.generate.annotation.file.GenApi;
import com.xht.cloud.code.generate.annotation.file.GenApiImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileAddRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileController;
import com.xht.cloud.code.generate.annotation.file.GenFileConvert;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileDao;
import com.xht.cloud.code.generate.annotation.file.GenFileDaoImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileIService;
import com.xht.cloud.code.generate.annotation.file.GenFileMapper;
import com.xht.cloud.code.generate.annotation.file.GenFileMapperXml;
import com.xht.cloud.code.generate.annotation.file.GenFileRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileResponse;
import com.xht.cloud.code.generate.annotation.file.GenFileServiceImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileWrapper;
import com.xht.cloud.code.generate.annotation.file.GenPackageInfo;
import com.xht.cloud.code.generate.annotation.file.GenSql;
import com.xht.cloud.code.generate.annotation.file.GenVue3AddOrUpdate;
import com.xht.cloud.code.generate.annotation.file.GenVue3Api;
import com.xht.cloud.code.generate.annotation.file.GenVue3Index;
import com.xht.cloud.code.generate.annotation.file.GenVue3Types;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：`地区信息`代码生成器所需实体
 *
 * @author : 小糊涂-代码生成器
 **/
@Data
@Module(value = "area", desc = "地区信息")
@GenInfoAnnotation(tableName = "sys_area_info", url = "/sys/area/info", tableComment = "地区信息", tableType = "BASE TABLE", tableSchema = "xht-cloud", engine = "InnoDB", createTime = "2023-05-18 17:07:15", updateTime = "")
public class SysAreaInfo {

    /**
     *
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "id", describe = "主键", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String id;

    /**
     * 父级区划代码
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "parent_id", describe = "父级区划代码", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String parentId;

    /**
     * 名称
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "name", describe = "名称", columnDefault = "", length = 100, isNullable = true, dbType = "varchar")
    private String name;

    /**
     * 级别1-5,省市县镇村1级：省、直辖市、自治区2级：地级市3级：市辖区、县（旗）、县级市、自治县（自治旗）、特区、林区4级：镇、乡、民族乡、县辖区、街道5级：村、居委会
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "level", describe = "级别1-5,省市县镇村1级：省、直辖市、自治区2级：地级市3级：市辖区、县（旗）、县级市、自治县（自治旗）、特区、林区4级：镇、乡、民族乡、县辖区、街道5级：村、居委会", columnDefault = "", length = 1, isNullable = true, dbType = "char")
    private String level;

    /**
     * 区划代码
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "area_no", describe = "区划代码", columnDefault = "", length = 20, isNullable = true, dbType = "varchar")
    private String areaNo;

    /**
     * 城乡分类111表示主城区；112表示城乡接合区；121表示镇中心区；122表示镇乡接合区；123表示特殊区域；210表示乡中心区；220表示村庄
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "category", describe = "城乡分类111表示主城区；112表示城乡接合区；121表示镇中心区；122表示镇乡接合区；123表示特殊区域；210表示乡中心区；220表示村庄", columnDefault = "", length = 3, isNullable = false, dbType = "varchar")
    private String category;

    /**
     * 描述
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "msg", describe = "描述", columnDefault = "", length = 1000, isNullable = false, dbType = "varchar")
    private String msg;

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
    @GenFileWrapper
    @GenSql
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
    @GenFileWrapper
    @GenSql
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
    @GenFileWrapper
    @GenSql
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
    @GenFileWrapper
    @GenSql
    @FiledInfo(value = "update_time", describe = "更新时间", columnDefault = "", length = 0, isNullable = false, dbType = "datetime")
    private LocalDateTime updateTime;


}
