package com.xht.cloud.framework.core.api.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Data
@Hidden
public abstract class CreateRequest extends Request implements Serializable {
}
