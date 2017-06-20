package com.watent.source.moudel;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Atom on 2017/6/20.
 */
@Data
public class EntityTwo {

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "EntityTwo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
