package com.watent.task.moudel;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Atom on 2017/6/20.
 */
@Data
public class EntityOne {

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
