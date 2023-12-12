package com.xht.cloud.framework.file.oss.core;

import com.xht.cloud.framework.file.oss.dto.DeleteObjectsDTO;
import com.xht.cloud.framework.file.oss.dto.GetObjectDTO;
import com.xht.cloud.framework.file.oss.dto.ListObjectsDTO;
import com.xht.cloud.framework.file.oss.dto.PutObjectDTO;
import com.xht.cloud.framework.file.oss.dto.StatObjectDTO;
import com.xht.cloud.framework.file.oss.dto.UploadObjectDTO;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DeleteObjectsCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.DownloadObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.GetObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.GetPreSignedObjectUrlCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.GetStatObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.ListObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.PutObjectCmd;
import com.xht.cloud.framework.file.oss.dto.cmd.UploadObjectCmd;

import java.util.List;

/**
 * 描述 ：对象存储操作
 *
 * @author : 小糊涂
 **/
public interface OssObjectOperations {
    /**
     * 将文件中的内容作为存储桶中的对象上传
     *
     * @param objectCmd {@link UploadObjectCmd}
     * @return {@link UploadObjectDTO}
     */
    UploadObjectDTO uploadObject(UploadObjectCmd objectCmd);

    /**
     * 上传文件
     * <p>
     * · 默认情况下，如果已存在同名Object且对该Object有访问权限，则新添加的Object将覆盖原有的Object，并返回200 OK。
     * · OSS没有文件夹的概念，所有资源都是以文件来存储，但您可以通过创建一个以正斜线（/）结尾，大小为0的Object来创建模拟文件夹。
     *
     * @param objectCmd {@link PutObjectCmd}
     * @return {@link PutObjectDTO}
     */
    PutObjectDTO putObject(PutObjectCmd objectCmd);

    /**
     * listObjects列出桶里面的对象信息
     *
     * @param objectCmd {@link ListObjectCmd}
     * @return ListObjectsDTO
     */
    ListObjectsDTO listObjects(ListObjectCmd objectCmd);

    /**
     * 删除一个对象
     *
     * @param objectCmd {@link DeleteObjectCmd}
     */
    void removeObject(DeleteObjectCmd objectCmd);

    /**
     * 删除多个对象。它需要迭代返回的 Iterable 以执行删除
     *
     * @param objectsCmd {@link DeleteObjectsCmd}
     * @return List<DeleteObjectsDTO> 自定义删除错误列表。列表 Size 为 0，表明全部正常删除；不为 0，则返回具体错误对象以及相关信息
     */
    List<DeleteObjectsDTO> removeObjects(DeleteObjectsCmd objectsCmd);

    /**
     * 获取对象的对象信息和元数据
     *
     * @param objectCmd {@link GetStatObjectCmd}
     * @return {@link StatObjectDTO}
     */
    StatObjectDTO getStatObject(GetStatObjectCmd objectCmd);

    /**
     * GetObject接口用于获取某个文件（Object）。此操作需要对此Object具有读权限。
     * <p>
     * 获取对象的数据。InputStream使用后返回必须关闭以释放网络资源。
     *
     * @param objectCmd {@link GetObjectCmd}
     * @return {@link GetObjectDTO}
     */
    GetObjectDTO getObject(GetObjectCmd objectCmd);

    /**
     * 获取一个指定了 HTTP 方法、到期时间和自定义请求参数的对象URL地址，也就是返回带签名的URL，
     * <p>
     * 这个地址可以提供给没有登录的第三方共享访问或者上传对象。
     *
     * @param objectUrlCmd {@link GetPreSignedObjectUrlCmd}
     * @return url string
     */
    String getPreSignedObjectUrl(GetPreSignedObjectUrlCmd objectUrlCmd);



    /**
     * 将对象的数据下载到文件。主要用于在服务端下载
     *
     * @param objectCmd {@link DownloadObjectCmd}
     */
    void downloadObject(DownloadObjectCmd objectCmd);



}
