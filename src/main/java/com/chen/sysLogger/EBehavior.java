package com.chen.sysLogger;


/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-07-10
 */
public enum EBehavior {

    /**
     * 用户行为
     */
    PRAISE("点赞", "praise"),
    FRIENDSHIP_LINK("点击友情链接", "friendship_link"),
    SEARCH("点击搜索", "search"),
    VISIT_PAGE("访问页面", "visit_page");

    private String content;
    private String behavior;

    EBehavior(String content, String behavior) {
        this.content = content;
        this.behavior = behavior;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
