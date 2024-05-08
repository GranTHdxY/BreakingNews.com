package org.Big_Event.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.Big_Event.anno.State;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题

    @NotEmpty
    private String content;//文章内容

    @NotEmpty
    @URL
    private String coverImg;//封面图像

    //这里State是自定义校验
    //其实有更好的解决方法1.正则表达式 2.在数据库中用01 02 表示即可
    @Pattern(regexp = "已发布|草稿",message = "发布状态必须为'已发布'或者'草稿'")
    //@State
    private String state;//发布状态 已发布|草稿

    @NotNull
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
