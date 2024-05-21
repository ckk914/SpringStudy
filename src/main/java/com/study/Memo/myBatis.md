
많은 일반적으로 요즘 회사들이 
80%? 정도의 회사들이 사용하고 있다

# MyBatis
-  자바 언어를 위한 오픈 소스 ORM(Object Relational Mapping) 프레임워크
- ⭐️JDBC로 수행되는 데이터베이스 쿼리의 수고를 덜어주는 간편한 방법을 제공

- 기존에 쓰던 레퍼지토리 대신 마이바티스로 교체하기
- 라이브러리 필요
- 어플리케이션 프로퍼티에
```
- #mybatis setting   : path 는 자유롭게 하는데 저걸로 많이 씀
  mybatis.config-location=classpath:mybatis-config.xml
```
-resources 에 mybatis-config.xml을 만든다
```dtd
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

<!--    SQL을 적어놓은 ... Mapper.xml 파일들을 사용등록하는 영역-->
<!--다른 xml 생기면 하단에 계속 추가-->
<mappers>
    <mapper resource="mappers/PersonMapper.xml"/>
</mappers>
</configuration>

```

xml 만들기
resources -> 그냥 놔도 되는데 폴더로 깔끔하게 추가
적당히 해도됨
사용이름+mapper

resources-> mappers>personMapper.xml
```dtd
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- mapper파일은 실행할 sql을 적는 파일-->
<!--생성한 mapper 파일은 mybatis-config.xml에 사용 등록을 해야함. -->

<!--namespace속성에는 사용할 인터페이스의 풀네임(패키지+인터페이스명)을 적음-->
<!--각 인터페이스 가서 패키지 주소 복사하고 + 그 인터페이스명 기입~!⭐️-->
<mapper namespace="com.study.SpringStudy.database.chap02.PersonMapper">
<!--id 속성: 인터페이스의 추상 메서드명을 적는다.~! (함수명)-->
    <insert id="save">
        INSERT INTO tbl_person
        (id, person_name, person_age)
        VALUES
         (#{id}, #{personName}, #{personAge})
    </insert>
</mapper>
```

### 인서트문 상세
        INSERT INTO tbl_person
        (id, person_name, person_age)
        VALUES
         (#{id}, #{personName}, #{personAge})

? 였던걸 #{} 으로 하고, 안에 컬럼은 객체 생성할때 만든 컬럼명

