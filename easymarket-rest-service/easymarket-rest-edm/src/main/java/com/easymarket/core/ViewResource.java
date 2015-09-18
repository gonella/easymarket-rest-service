package com.easymarket.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Useful for entities which need to be displayed in easymarket panel.
 *
 */
@MappedSuperclass
public class ViewResource extends BaseResource {

    public ViewResource(){};

    @Column
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
