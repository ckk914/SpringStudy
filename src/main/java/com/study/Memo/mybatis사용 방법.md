
### 1/ 인터페이스 생성

@Mapper 붙이고, 각각의 사용할 함수들 생성. 추상형
```dtd
@Mapper
public interface BoardMapper {
    //게시물 목록 조회
    List<Board> findAll();
    //게시물 상세 조회
    Board findOne(int boardNo);
    //게시물 등록
    boolean save(Board board);
    //게시글 삭제
    boolean delete(int boardNo);
    //조회 수 상승
    void upViewCount(int boardNo);
}
```
쿼리는 resources 에 작성
폴더 깔끔히 mappers -> BoardMapper.xml로 만듬
```dtd
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.study.SpringStudy.springmvc.chap04.mapper.BoardMapper">
```
- namespace는 아까 만든 mapper의 만든 위치를 긁어온다. 패키지 보면 써있고, 끝에 이름을 적어준다
```dtd
<select id="findAll" resultType="board">
```
id는 함수명 , resultType은 mybatis-config.xml에서 약어로 작성
```dtd
    <typeAliases>
        <typeAlias type="com.study.SpringStudy.database.chap01.Person" alias="person"/>
```
xml에서 만들어주면 alias값을 통해 불러올 수 있음

```dtd
<mapper>
    쿼리 위치
<select></select>
    <insert></insert>
    <update></update>
    <delete></delete>
</mapper>
```
  - 각각 select 문 제외하고는 id만 적는다

```dtd
<select id="findOne" resultType="board">
```
- 셀렉트 문은 return 타입 처럼 적는게 있다.~!

- 그리고 service 클래스에서 mapper를 접근하여 사용
```dtd
public void delete(int boardNum) {
        mapper.delete(boardNum);
    }
```
- service를 사용하여 호출하는 곳은 controller

