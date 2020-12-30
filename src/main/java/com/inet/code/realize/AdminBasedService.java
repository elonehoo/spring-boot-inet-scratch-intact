package com.inet.code.realize;

import com.inet.code.entity.label.dto.LabelAmendDoMain;
import com.inet.code.entity.label.dto.LabelAppendDoMain;
import com.inet.code.entity.portrait.dto.PortraitIncreaseDomain;
import com.inet.code.entity.slideshow.dto.SlideshowAmendDomain;
import com.inet.code.entity.slideshow.dto.SlideshowIncreaseDomain;
import com.inet.code.entity.type.dto.TypeAmendDoMain;
import com.inet.code.entity.type.dto.TypeAppendDoMain;
import com.inet.code.utils.Result;

/**
 * 管理员的业务操作
 *
 * @author HCY
 * @since 2020/12/11 下午 06:23
*/
public interface AdminBasedService {
    /**
     * 标签的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:10
     * @param labelAppendDoMain: 标签的基础属性 内涵 标签名字
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAppendLabel(LabelAppendDoMain labelAppendDoMain, String path);

    /**
     * 类别的添加
     *
     * @author HCY
     * @since 2020/12/11 下午 09:32
     * @param typeAppendDoMain: 类别的基础属性, 内涵 类被名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAppendType(TypeAppendDoMain typeAppendDoMain, String path);

    /**
     * 标签的修改
     *
     * @author HCY
     * @since 2020/12/12 上午 09:48
     * @param labelAmendDoMain: 修改标签的实体类
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendLabel(LabelAmendDoMain labelAmendDoMain, String path);

    /**
     * 通过标签的uuid查询到有多少个项目文件属于这个标签
     *
     * @author HCY
     * @since 2020/12/12 上午 10:31
     * @param labelUuid: 需要删除的标签的名称
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getRemoveInquireLabel(String labelUuid, String path);

    /**
     * 删除操作,会删除属于该类别的所有项目的该标签
     *
     * @author HCY
     * @since 2020/12/12 下午 01:45
     * @param labelUuid: 删除标签的序号
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getRemoveLabel(String labelUuid, String path);

    /**
     * 类别的修改操作
     *
     * @author HCY
     * @since 2020/12/12 下午 02:55
     * @param typeAmendDoMain: 修改类别的实体类 含有 类别的uuid 和 类别的名字
     * @param path: URL 路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendType(TypeAmendDoMain typeAmendDoMain, String path);

    /**
     * 分页，查看轮播图(可以通过状态)
     *
     * @author HCY
     * @since 2020/12/15 10:44 上午
     * @param current: 页数
     * @param total: 条目数
     * @param isShow: 图片的状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
     */
    Result getSlideshowPagination(Integer current, Integer total, Boolean isShow, String path);

    /**
     * 新增轮播图
     *
     * @author HCY
     * @since 2020/12/16 9:52 上午
     * @param slideshowIncreaseDomain: 轮播图新增的实体类 含有 轮播图的URL 和 轮播图的展示状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getIncreaseSlideshow(SlideshowIncreaseDomain slideshowIncreaseDomain, String path);

    /**
     * 修改轮播图
     *
     * @author HCY
     * @since 2020/12/16 下午 03:40
     * @param slideshowAmendDomain: 修改轮播图的状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getAmendSlideshow(SlideshowAmendDomain slideshowAmendDomain, String path);

    /**
     * 删除轮播图的序号
     *
     * @author HCY
     * @since 2020/12/17 下午 06:53
     * @param slideshowUuid: 删除轮播图的序号
     * @param path:URL路径
     * @return com.inet.code.utils.Result
    */
    Result getRemoveSlideshow(String slideshowUuid, String path);

    /**
     * 分页查看默认头像
     *
     * @author HCY
     * @since 2020/12/21 1:27 下午
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getShowPortrait(Integer current, Integer size, String path);

    /**
     * 新增默认头像
     *
     * @author HCY
     * @since 2020/12/21 2:07 下午
     * @param portraitIncreaseDomain: 新增默认头像
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getIncreasePortrait(PortraitIncreaseDomain portraitIncreaseDomain, String path);

    /**
     * 查看从今日开始前七天的新增人数量
     *
     * @author HCY
     * @since 2020/12/24 9:50 下午
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getListNewUser(String path);

    /**
     * 查看从今日起七天的新增项目数量
     *
     * @author HCY
     * @since 2020/12/25 6:09 下午
     * @param issue: 发布状态
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getListNewProduction(Boolean issue, String path);

    /**
     * 查看点赞数最高的五个项目
     *
     * @author HCY
     * @since 2020/12/30 上午8:59
     * @param path: URL路径
     * @return com.inet.code.utils.Result
    */
    Result getListFiveProduction(String path);
}
