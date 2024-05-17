package com.study.SpringStudy.database.chap01;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private long id;
    private String personName;
    private int personAge;

    public Person(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.personName = rs.getString("person_name");
        this.personAge = rs.getInt("person_age");
    }
}
