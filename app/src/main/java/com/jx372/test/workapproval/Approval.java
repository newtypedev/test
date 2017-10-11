package com.jx372.test.workapproval;

import java.util.UUID;

/**
 * Created by pys on 2017. 10. 3..
 */

public class Approval {

    private UUID id;
private String title;

    public Approval(){
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
