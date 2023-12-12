# web 扩展包

## 文档注释注解

springdoc-openapi 与 springfox-swagger2 提供的注解有很大差别：

| swagger2           | OpenAPI 3                                                         | 注解位置                          |
|--------------------|-------------------------------------------------------------------|-------------------------------|
| @Api               | @Tag(name  = “接口类描述”)                                             | Controller  类上                |
| @ApiOperation      | @Operation(summary  =“接口方法描述”)                                    | Controller  方法上               |
| @ApiImplicitParams | @Parameters                                                       | Controller  方法上               |
| @ApiImplicitParam  | @Parameter(description=“参数描述”)                                    | Controller  方法上 @Parameters 里 |
| @ApiParam          | @Parameter(description=“参数描述”)                                    | Controller  方法的参数上            |
| @ApiIgnore         | @Parameter(hidden  = true) 或 @Operation(hidden  = true) 或 @Hidden | -                             |
| @ApiModel          | @Schema                                                           | DTO类上                         |
| @ApiModelProperty  | @Schema                                                           | DTO属性上                        |

## jsr303校验

> JSR303是一项标准,只提供规范不提供实现，规定一些校验规范即校验注解，如@Null，@NotNull，@Pattern，位于javax.validation.constraints包下

```js
@AssertFalse            //被注释的元素只能为false
@AssertTrue             //被注释的元素只能为true
@DecimalMax             //被注释的元素必须小于或等于{value}
@DecimalMin             //被注释的元素必须大于或等于{value}
@Digits                 //被注释的元素数字的值超出了允许范围(只允许在{integer}位整数和{fraction}位小数范围内)
@Email                  //被注释的元素不是一个合法的电子邮件地址
@Future                 //被注释的元素需要是一个将来的时间
@FutureOrPresent        //被注释的元素需要是一个将来或现在的时间
@Max                    //被注释的元素最大不能超过{value}
@Min                    //被注释的元素最小不能小于{value}
@Negative               //被注释的元素必须是负数
@NegativeOrZero         //被注释的元素必须是负数或零
@NotBlank               //被注释的元素不能为空
@NotEmpty               //被注释的元素不能为空
@NotNull                //被注释的元素不能为null
@Null                   //被注释的元素必须为null
@Past                   //被注释的元素需要是一个过去的时间
@PastOrPresent          //被注释的元素需要是一个过去或现在的时间
@Pattern                //被注释的元素需要匹配正则表达式"{regexp}"
@Positive               //被注释的元素必须是正数
@PositiveOrZero         //被注释的元素必须是正数或零
@Size                  //被注释的元素个数必须在{min}和{max}之间
```

## hibernate validation

hibernate validation是对这个规范的实现，并增加了一些其他校验注解，如@Email，@Length，@Range等等

```js
@CreditCardNumber       //被注释的元素不合法的信用卡号码
@Currency               //被注释的元素不合法的货币 (必须是{value}其中之一)
@EAN                    //被注释的元素不合法的{type}条形码
@Email                  //被注释的元素不是一个合法的电子邮件地址  (已过期)
@Length                 //被注释的元素长度需要在{min}和{max}之间
@CodePointLength        //被注释的元素长度需要在{min}和{max}之间
@LuhnCheck              //被注释的元素${validatedValue}的校验码不合法, Luhn模10校验和不匹配
@Mod10Check             //被注释的元素${validatedValue}的校验码不合法, 模10校验和不匹配
@Mod11Check             //被注释的元素${validatedValue}的校验码不合法, 模11校验和不匹配
@ModCheck               //被注释的元素${validatedValue}的校验码不合法, ${modType}校验和不匹配  (已过期)
@NotBlank               //被注释的元素不能为空  (已过期)
@NotEmpty               //被注释的元素不能为空  (已过期)
@ParametersScriptAssert //被注释的元素执行脚本表达式"{script}"没有返回期望结果
@Range                  //被注释的元素需要在{min}和{max}之间
@SafeHtml               //被注释的元素可能有不安全的HTML内容
@ScriptAssert           //被注释的元素执行脚本表达式"{script}"没有返回期望结果
@URL                    //被注释的元素需要是一个合法的URL
@DurationMax            //被注释的元素必须小于${inclusive == true ? '或等于' : ''}${days == 0 ? '' : days += '天'}${hours == 0 ? '' : hours += '小时'}${minutes == 0 ? '' : minutes += '分钟'}${seconds == 0 ? '' : seconds += '秒'}${millis == 0 ? '' : millis += '毫秒'}${nanos == 0 ? '' : nanos += '纳秒'}
@DurationMin            //被注释的元素必须大于${inclusive == true ? '或等于' : ''}${days == 0 ? '' : days += '天'}${hours == 0 ? '' : hours += '小时'}${minutes == 0 ? '' : minutes += '分钟'}${seconds == 0 ? '' : seconds += '秒'}${millis == 0 ? '' : millis += '毫秒'}${nanos == 0 ? '' : nanos += '纳秒'}
```

## @Validate和@Valid什么区别？

> 细心的你会发现，上个例子中用的是@Validate, 而不是@Valid，那它们之间的区别是什么呢？

在检验Controller的入参是否符合规范时，使用@Validated或者@Valid在基本验证功能上没有太多区别。但是在分组、注解地方、嵌套验证等功能上两个有所不同：

- **分组**

  @Validated：提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制，这个网上也有资料，不详述。

  @Valid：作为标准JSR-303规范，还没有吸收分组的功能。

- **注解地方**

@Validated：可以用在类型、方法和方法参数上。但是不能用在成员属性（字段）上

@Valid：可以用在方法、构造函数、方法参数和成员属性（字段）上

- **嵌套类型**

比如本文例子中的address是user的一个嵌套属性, 只能用@Valid

```java

@Data
@Builder
@ApiModel(value = "User", subTypes = {AddressParam.class})
public class UserParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Valid // 这里只能用@Valid
    private AddressParam address;

}
```

