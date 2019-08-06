package com.bawei.gailei20190806.bean;

import java.util.List;

/**
 * author: 盖磊
 * data: 2019/8/6 10:10:02
 * function:
 */
public class ListBean {

    private List<DataInfo> data;

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }

    public class DataInfo {
        private String content;
        private String avatar;
        private List<ImagesInfo> images;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public List<ImagesInfo> getImages() {
            return images;
        }

        public void setImages(List<ImagesInfo> images) {
            this.images = images;
        }

        public class ImagesInfo {
            private String imageurl;

            public String getImageurl() {
                return imageurl;
            }

            public void setImageurl(String imageurl) {
                this.imageurl = imageurl;
            }
        }
    }
}
