package com.inet.code.entity.production.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.inet.code.entity.label.vo.LabelBaseView;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 展示项目的View实体类
 *
 * @author HCY
 * @since 2020/12/29 9:38 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ProductionView {

    /**
     * 作品uuid
     */
    private String productionUuid;

    /**
     * 作品名称
     */
    private String productionName;

    /**
     * 封面的URL地址
     */
    private String productionCover;
    /**
     * 项目的地址
     */
    private String productionUrl;

    /**
     * 作品的说明(备注)
     */
    private String productionRemark;

    /**
     * 作品操作说明
     */
    private String productionOi;

    /**
     * 作品的类型
     */
    private String productionTypeName;

    /**
     * 作品是否允许改编
     */
    private Boolean productionRecompose;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 标签的集合
     */
    private List<LabelBaseView> labelLists;

    /**
     * 点赞数
     */
    private Integer likeTotal;

    /**
     * 评论数
     */
    private Integer commentsTotal;

}
