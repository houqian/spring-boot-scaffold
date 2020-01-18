package org.houqian.springbootdemo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论信息表
 * </p>
 *
 * @author houqian
 * @since 2020-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发表评论的用户账号的ID
     */
    private Long userAccountId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 订单信息ID
     */
    private Long orderInfoId;

    /**
     * 订单条目id
     */
    private Long orderItemId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品sku ID
     */
    private Long goodsSkuId;

    /**
     * 商品sku的销售属性
     */
    private String goodsSkuSaleProperties;

    /**
     * 总评分，1~5
     */
    private Integer totalScore;

    /**
     * 商品评分，1~5
     */
    private Integer goodsScore;

    /**
     * 客服评分，1~5
     */
    private Integer customerServiceScore;

    /**
     * 物流评分，1~5
     */
    private Integer logisticsScore;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 是否晒图，1：晒图，0：未晒图
     */
    private Integer isShowPictures;

    /**
     * 是否系统自动给的默认评论，1：是默认评论，0：不是默认评论
     */
    private Integer isDefaultComment;

    /**
     * 评论状态，1：待审核，2：已审核，3：审核不通过
     */
    private Integer commentStatus;

    /**
     * 评论类型，1：好评，总分是4分以上；2：中评，3分；3：差评，1~2分
     */
    private Integer commentType;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;


}
