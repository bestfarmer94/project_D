package com.sparta.project_d.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class a{
    public enum Grade{
        일반,
        고급,
        희귀,
        영웅
    }

    public static void main(String[] args) {
        List<Monster> list = new ArrayList<>();
        list.add(new Monster(1, Grade.일반));
        list.add(new Monster(2, Grade.영웅));
        list.add(new Monster(3, Grade.고급));
        list.add(new Monster(4, Grade.희귀));
        Collections.sort(list);
        for (Monster monster : list) {
            System.out.println(monster.getId());
        }
    }
}

@AllArgsConstructor
@Getter
class Monster implements Comparable<Monster>{
    private long id;
    private a.Grade grade;

    @Override
    public int compareTo(Monster o) {
        return this.grade.compareTo(o.grade);
    }
}

