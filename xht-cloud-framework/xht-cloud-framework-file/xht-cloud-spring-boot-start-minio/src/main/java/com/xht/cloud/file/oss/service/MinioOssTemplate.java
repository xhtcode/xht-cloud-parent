package com.xht.cloud.file.oss.service;

import com.xht.cloud.file.oss.convert.MinioBucketConvertImpl;
import com.xht.cloud.file.oss.convert.MinioCmdToDownloadObjectArgsConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToGetObjectArgsConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToGetPreSignedObjectUrlConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToListObjectsArgsConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToMakeBucketArgsConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToPutObjectArgsConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToRemoveBucketArgsConvert;
import com.xht.cloud.file.oss.convert.MinioCmdToRemoveObjectArgsToConvert;
import com.xht.cloud.file.oss.convert.MinioCmdToRemoveObjectsArgsConvert;
import com.xht.cloud.file.oss.convert.MinioCmdToStatObjectArgsConverter;
import com.xht.cloud.file.oss.convert.MinioCmdToUploadObjectArgsConverter;
import com.xht.cloud.file.oss.convert.MinioDeleteResultItemToDTOConvert;
import com.xht.cloud.file.oss.convert.MinioGetObjectResponseToDTOConverter;
import com.xht.cloud.file.oss.convert.MinioListResultItemToDTOConvert;
import com.xht.cloud.file.oss.convert.MinioObjectWriteResponseToDTOConverter;
import com.xht.cloud.file.oss.convert.MinioObjectWriteResponseToUploadDTOConverter;
import com.xht.cloud.file.oss.convert.MinioStatObjectResponseToDTOConverter;
import com.xht.cloud.file.oss.repository.MinioFileRepository;
import com.xht.cloud.framework.file.oss.convert.BucketConvert;
import com.xht.cloud.framework.file.oss.core.OssBucketOperations;
import com.xht.cloud.framework.file.oss.core.OssObjectOperations;
import com.xht.cloud.framework.file.oss.dto.BucketDTO;
import com.xht.cloud.framework.file.oss.dto.DeleteObjectsDTO;
import com.xht.cloud.framework.file.oss.dto.GetObjectDTO;
import com.xht.cloud.framework.file.oss.dto.ListObjectsDTO;
import com.xht.cloud.framework.file.oss.dto.PutObjectDTO;
import com.xht.cloud.framework.file.oss.dto.StatObjectDTO;
import com.xht.cloud.framework.file.oss.dto.UploadObjectDTO;
import com.xht.cloud.framework.file.oss.dto.cmd.CreateBucketCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteBucketCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteObjectsCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DownloadObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.GetObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.GetPreSignedObjectUrlCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.GetStatObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.ListObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.PutObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.UploadObjectCmd;
import com.xht.cloud.framework.file.oss.exception.OssException;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.UploadObjectArgs;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@RequiredArgsConstructor
public class MinioOssTemplate implements OssBucketOperations, OssObjectOperations {

    private final MinioFileRepository minioOssRepository;

    /**
     * 查询所有存储桶
     *
     * @return Bucket 列表
     */
    @Override
    public List<BucketDTO> listBuckets() {
        BucketConvert<Bucket> convert = new MinioBucketConvertImpl();
        List<Bucket> buckets = minioOssRepository.listBuckets();
        return convert.convert(buckets);
    }

    /**
     * 存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return 是否存在，true 存在，false 不存在
     */
    @Override
    public boolean doesBucketExist(String bucketName) {
        return minioOssRepository.bucketExists(bucketName);
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    @Override
    public boolean createBucket(String bucketName) {
        if (doesBucketExist(bucketName)) {
            throw new OssException(String.format("`%s`存储桶已存在", bucketName));
        }
        minioOssRepository.makeBucket(bucketName);
        return Boolean.TRUE;
    }

    /**
     * 创建存储桶
     *
     * @param bucketCmd {@link CreateBucketCmd}
     */
    @Override
    public boolean createBucket(CreateBucketCmd bucketCmd) {
        Converter<CreateBucketCmd, MakeBucketArgs> converter = new MinioCmdToMakeBucketArgsConverter();
        if (doesBucketExist(bucketCmd.getBucketName())) {
            throw new OssException(String.format("`%s`存储桶已存在", bucketCmd.getBucketName()));
        }
        minioOssRepository.makeBucket(converter.convert(bucketCmd));
        return Boolean.TRUE;
    }

    /**
     * 删除一个空的存储桶 如果存储桶存在对象不为空时，删除会报错。
     *
     * @param bucketName 存储桶名称
     */
    @Override
    public void deleteBucket(String bucketName) {
        if (!doesBucketExist(bucketName)) {
            throw new OssException(String.format("`%s`存储桶不存在，删除失败!", bucketName));
        }
        minioOssRepository.removeBucket(bucketName);
    }

    /**
     * 删除一个空的存储桶 如果存储桶存在对象不为空时，删除会报错。
     *
     * @param bucketCmd {@link DeleteBucketCmd}
     */
    @Override
    public void deleteBucket(DeleteBucketCmd bucketCmd) {
        Converter<DeleteBucketCmd, RemoveBucketArgs> convert = new MinioCmdToRemoveBucketArgsConvert();
        RemoveBucketArgs removeBucketArgs = convert.convert(bucketCmd);
        if (!doesBucketExist(bucketCmd.getBucketName())) {
            throw new OssException(String.format("`%s`存储桶不存在，删除失败!", bucketCmd.getBucketName()));
        }
        minioOssRepository.removeBucket(removeBucketArgs);
    }

    /**
     * listObjects列出桶里面的对象信息
     *
     * @param objectCmd {@link ListObjectCmd}
     * @return List<ListObjectsDTO>
     */
    @Override
    public ListObjectsDTO listObjects(ListObjectCmd objectCmd) {
        Converter<ListObjectCmd, ListObjectsArgs> converter = new MinioCmdToListObjectsArgsConverter();
        Converter<Iterable<Result<Item>>, ListObjectsDTO> resultListObjectsDTOConverter = new MinioListResultItemToDTOConvert(objectCmd);
        Iterable<Result<Item>> resultItems = minioOssRepository.listObjects(converter.convert(objectCmd));
        return  resultListObjectsDTOConverter.convert(resultItems);
    }

    /**
     * 删除一个对象
     *
     * @param objectCmd {@link DeleteObjectCmd}
     */
    @Override
    public void removeObject(DeleteObjectCmd objectCmd) {
        Converter<DeleteObjectCmd, RemoveObjectArgs> convert = new MinioCmdToRemoveObjectArgsToConvert();
        minioOssRepository.removeObject(convert.convert(objectCmd));
    }

    /**
     * 删除多个对象。它需要迭代返回的 Iterable 以执行删除
     *
     * @param objectsCmd {@link DeleteObjectsCmd}
     * @return List<DeleteObjectsDTO> 自定义删除错误列表。列表 Size 为 0，表明全部正常删除；不为 0，则返回具体错误对象以及相关信息
     */
    @Override
    public List<DeleteObjectsDTO> removeObjects(DeleteObjectsCmd objectsCmd) {
        List<DeleteObjectsDTO> result = new ArrayList<>();
        Converter<DeleteObjectsCmd, RemoveObjectsArgs> convert = new MinioCmdToRemoveObjectsArgsConvert();
        Converter<Result<DeleteError>, DeleteObjectsDTO> deleteObjectsDTOConvert = new MinioDeleteResultItemToDTOConvert();
        Iterable<Result<DeleteError>> resultItems = minioOssRepository.removeObjects(convert.convert(objectsCmd));
        for (Result<DeleteError> item : resultItems) {
            DeleteObjectsDTO deleteObjectsDTO = deleteObjectsDTOConvert.convert(item);
            result.add(deleteObjectsDTO);
        }
        return result;
    }

    /**
     * 获取对象的对象信息和元数据
     *
     * @param objectCmd {@link GetStatObjectCmd}
     * @return {@link StatObjectDTO}
     */
    @Override
    public StatObjectDTO getStatObject(GetStatObjectCmd objectCmd) {
        Converter<GetStatObjectCmd, StatObjectArgs> converter = new MinioCmdToStatObjectArgsConverter();
        Converter<StatObjectResponse, StatObjectDTO> dtoConverter = new MinioStatObjectResponseToDTOConverter();
        StatObjectResponse response = minioOssRepository.statObject(converter.convert(objectCmd));
        return dtoConverter.convert(response);
    }

    /**
     * GetObject接口用于获取某个文件（Object）。此操作需要对此Object具有读权限。
     * <p>
     * 获取对象的数据。InputStream使用后返回必须关闭以释放网络资源。
     *
     * @param objectCmd {@link GetObjectCmd}
     * @return {@link GetObjectDTO}
     */
    @Override
    public GetObjectDTO getObject(GetObjectCmd objectCmd) {
        Converter<GetObjectCmd, GetObjectArgs> converter = new MinioCmdToGetObjectArgsConverter();
        Converter<GetObjectResponse, GetObjectDTO> dtoConverter = new MinioGetObjectResponseToDTOConverter();
        GetObjectResponse response = minioOssRepository.getObject(converter.convert(objectCmd));
        return dtoConverter.convert(response);
    }

    /**
     * 获取一个指定了 HTTP 方法、到期时间和自定义请求参数的对象URL地址，也就是返回带签名的URL，
     * <p>
     * 这个地址可以提供给没有登录的第三方共享访问或者上传对象。
     *
     * @param objectUrlCmd {@link GetPreSignedObjectUrlCmd}
     * @return url string
     */
    @Override
    public String getPreSignedObjectUrl(GetPreSignedObjectUrlCmd objectUrlCmd) {
        Converter<GetPreSignedObjectUrlCmd, GetPresignedObjectUrlArgs> converter = new MinioCmdToGetPreSignedObjectUrlConverter();
        return minioOssRepository.getPreSignedObjectUrl(converter.convert(objectUrlCmd));
    }

    /**
     * 上传文件
     * <p>
     * · 默认情况下，如果已存在同名Object且对该Object有访问权限，则新添加的Object将覆盖原有的Object，并返回200 OK。
     * · OSS没有文件夹的概念，所有资源都是以文件来存储，但您可以通过创建一个以正斜线（/）结尾，大小为0的Object来创建模拟文件夹。
     *
     * @param objectCmd {@link PutObjectCmd}
     * @return {@link PutObjectDTO}
     */
    @Override
    public PutObjectDTO putObject(PutObjectCmd objectCmd) {
        Converter<PutObjectCmd, PutObjectArgs> converter = new MinioCmdToPutObjectArgsConverter();
        Converter<ObjectWriteResponse, PutObjectDTO> dtoConverter = new MinioObjectWriteResponseToDTOConverter();
        ObjectWriteResponse response = minioOssRepository.putObject(converter.convert(objectCmd));
        return dtoConverter.convert(response);
    }

    /**
     * 将对象的数据下载到文件。主要用于在服务端下载
     *
     * @param objectCmd {@link DownloadObjectCmd}
     */
    @Override
    public void downloadObject(DownloadObjectCmd objectCmd) {
        Converter<DownloadObjectCmd, DownloadObjectArgs> converter = new MinioCmdToDownloadObjectArgsConverter();
        minioOssRepository.downloadObject(converter.convert(objectCmd));
    }

    /**
     * 将文件中的内容作为存储桶中的对象上传
     *
     * @param objectCmd {@link UploadObjectCmd}
     * @return {@link UploadObjectDTO}
     */
    @Override
    public UploadObjectDTO uploadObject(UploadObjectCmd objectCmd) {
        Converter<UploadObjectCmd, UploadObjectArgs> converter = new MinioCmdToUploadObjectArgsConverter();
        Converter<ObjectWriteResponse, UploadObjectDTO> dtoConverter = new MinioObjectWriteResponseToUploadDTOConverter();
        ObjectWriteResponse response = minioOssRepository.uploadObject(converter.convert(objectCmd));
        return dtoConverter.convert(response);
    }
}
