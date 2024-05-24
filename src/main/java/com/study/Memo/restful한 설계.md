
Restful 한 설계 방식
- **`GET /users`**: 모든 사용자를 조회합니다.
- **`GET /users/1`**: 식별자가 1인 사용자를 조회합니다.
- **`POST /users`**: 새로운 사용자를 생성합니다.
- **`PUT /users/1`**: 식별자가 1인 사용자의 정보를 수정합니다.
- **`PATCH /users/1`**: 식별자가 1인 사용자의 일부 정보를 수정합니다.
- **`DELETE /users/1`**: 식별자가 1인 사용자를 삭제합니다.
- **`GET /users/1/posts`**: 식별자가 1인 사용자의 모든 게시물을 조회합니다.
- **`GET /users/1/posts/2`**: 식별자가 1인 사용자가 작성한 식별자가 2인 게시물을 조회합니다.
- **`POST /users/1/posts`**: 식별자가 1인 사용자가 새로운 게시물을 생성합니다.

---

JSON (JavaScript Object Notation)은 가볍고, 텍스트 기반의 데이터 교환 형식

```dtd
{
  "name": "John Doe",
  "age": 30,
  "isStudent": false,
  "courses": ["math", "history", "chemistry"],
  "address": {
    "street": "123 Main St",
    "city": "New York",
    "zipcode": "10001"
  }
}
```
json에서 키에도 따옴표 무조건 넣어야함⭐️


@GetMapping("/person")
//  ll
//    @GetMapping(value = "/person", produces = "application/json")