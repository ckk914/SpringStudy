
```dtd

<!--    동적 sql 분리 -->
    <sql id = "search">
        <if test="type == 'title'">
            WHERE title LIKE CONCAT('%', #{keyword}, '%')
        </if>
        <if test="type == 'content'">
            WHERE content LIKE CONCAT('%', #{keyword}, '%')
        </if>
        <if test="type == 'writer'">
            WHERE writer LIKE CONCAT('%', #{keyword}, '%')
        </if>
        <if test="type == 'tc'">
            WHERE title LIKE CONCAT('%', #{keyword}, '%')
            OR content LIKE CONCAT('%', #{keyword}, '%')
        </if>
    </sql>
    <select id="findAll" resultType="board">
        SELECT * FROM tbl_board
        <include refid="search"/>
        ORDER BY board_no DESC
        LIMIT #{pageStart}, #{amount}
    </select>
```

search로 호출해서 줄일 수 있다~!⭐️