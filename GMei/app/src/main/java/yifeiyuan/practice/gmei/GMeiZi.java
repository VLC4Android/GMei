package yifeiyuan.practice.gmei;

/**
 * Created by alanchen on 15/8/26.
 */
public class GMeiZi {

    /**
     * who : daimajia
     * publishedAt : 2015-08-25T04:08:30.735Z
     * desc : 8/25
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034gw1eveq3prvojj20k00qetbj.jpg
     * used : true
     * objectId : 55dbe85760b27e6cd4d8f016
     * createdAt : 2015-08-25T04:00:23.169Z
     * updatedAt : 2015-08-25T04:08:31.070Z
     */

    private String who;
    private String publishedAt;
    private String desc;
    private String type;
    private String url;
    private boolean used;
    private String objectId;
    private String createdAt;
    private String updatedAt;

    public void setWho(String who) {
        this.who = who;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getWho() {
        return who;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean getUsed() {
        return used;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
