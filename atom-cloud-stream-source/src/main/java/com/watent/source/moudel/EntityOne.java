package com.watent.source.moudel;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Atom on 2017/6/20.
 */
@Data
public class EntityOne implements Serializable{

    private Long id;

    private String name;

    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "EntityOne{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
