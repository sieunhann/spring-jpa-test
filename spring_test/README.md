1. Thuộc tính name trong annotation **@Entity** khác với thuộc tính name trong **@Table** như thế nào? Hãy giải thích rõ cần thì minh hoạ

 Answer:
+ "name" trong Entity dùng để chỉ tên của entity được Hibernate quản lý trong khi "name" trong Table chỉ đích danh tên của table dưới database

2. Để debug câu lệnh SQL mà **Hibernate** sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh nào vào file **application.properties**?

Answer:
+ Cần bổ sung lệnh: spring.jpa.show-sql=true
3. Annotation **@Column** dùng để bổ sung tính chất cho cột ứng với một thuộc tính. Tham số nào trong **@Column** sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính, tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu, tham số nào buộc trường không được null?

Answer:
+ Tham số sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính: name
+ Tham số chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu: unique = true
+ Tham số buộc trường không được null: nullable = false

4. Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung

    - Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)
    - Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
      Vậy 2 annotation này là gì
   
Answer:
- Trước khi đối tượng Entity lưu xuống CSDL: @PrePersit
- Trước khi đối tượng Entity cập nhật xuống CSDL: @PreUpdate

5. JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể JpaRepository kế thừa từ interface nào?

Answer:
- JpaRepository kế thừa từ PagingAndSortingRepository và QueryByExampleExecutor

7. Hãy viết khai báo một interface repository thao tác với một Entity tên là **Post**, kiểu dữ liệu trường Identity là long, tuân thủ interface JpaRepository.

Answer:

```java
@Repository 

public interface PostRepository extends JpaRepository<Post, Long>{}
```

8. Khi đã chọn một cột là Identity dùng **@Id** để đánh dấu, thì có cần phải dùng xác định unique dùng annotation @Column(unique=true) không?

Answer:
- Không cần vì @Id đã đánh dấu là duy nhất

9. Giả sử có 1 class **Employee** với các fields sau **{id, emailAddress, firstName, lastName}**. Hãy viết các method trong interface EmployeeRespository để :

-   Tìm tất cả các Employee theo emailAddress và lastName
-   Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
-   Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
-   Tìm tất cả các Employee theo fistName không phân biệt hoa thường

Answer:
>- List`<Employee>` findByEmailAddressContainingAndLastName(String email, String lastName);
>- List`<Employee>` findDistinctByFirstNameOrLastName(String firstName, String lastName);
>- List`<Employee>` findByLastNameOrderByFirstName(String lastName);
>- List`<Employee>` findByFirstNameIgnoreCase(String firstName);
9. Hãy nêu cách sử dụng của **@NamedQuery** và **@Query**. Cho ví dụ

Answer:
>**@NamedQuery** sử dụng cho câu truy vấn HQL trên class Entity
>>@NamedQuery(<br>
>>name = "getUserInfo",<br>
>>query = "SELECT u FROM User u WHERE id = ?1",<br>
>>resultClass = User.class)<br>
>>@Getter<br>
>>@Setter<br>
>>@AllArgsConstructor<br>
>>@NoArgsConstructor<br>
>>@Entity<br>
>>@Table(name="user")<br>
>>public class User {<br>
>>...<br>
>>}
>- Thuộc tính name của annotation định nghĩa tên của câu truy vấn.
>- Viết nội dung câu query vào thuộc tính query.
>- Có thể định nghĩa lớp Entity hoặc @SqlResultSetMapping sẽ được sử dụng để ánh xạ kết quả truy vấn
>> @Repository <br>
public interface UserRepository extends JpaRepository<User, Integer> { <br>
@Query(name = "getUserInfo") <br>
User getUserInfo(int id); <br>
}
>- Định nghĩa một method trong repository. Method này có kiểu trả về trùng với kiểu dữ liệu của kết quả truy vấn đã được định nghĩa (truy vấn có resultClass là User → return type phải là User hoặc List). Tham số truyền vào tương ứng với tham số sử dụng trong truy vấn.  Sử dụng annotation @Query bên trên method có thuộc tính name chỉ định tên của truy vấn muốn tham chiếu đến.

>**@Query** có thể tự định nghĩa các method sử dụng câu truy vấn JPQL (Hibernate) hoặc raw SQL.
>>@Repository <br>
>>public interface UserRepository extends JpaRepository<User, Integer> { <br>
>>// Khi được gắn @Query, thì tên của method không còn tác dụng nữa <br>
>>// Đây là JPQL <br>
>>@Query("select u from User u where u.email = ?1") <br>
>>User myCustomQuery(String email); <br>
>>// Đây là Native SQL <br>
>>    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true) <br>
>>    User myCustomQuery2(String email); <br>
>>}
> 
> - Đối với các câu query động (UPDATE, DELETE, INSERT) thì cần thêm annotation @Modifying và @Transactional
>>@Repository <br>
>>public interface ImageRepository extends JpaRepository<Image, Integer> { <br>
>>@Modifying <br>
>>@Transactional <br>
>>@Query(value = "UPDATE user SET name = ?1, avatar = ?2, phone = ?3, birthday = ?4 WHERE id = ?5", nativeQuery = true) <br>
>>public void updateProfile(String name, String avatar, String phone, Date birthday, int id); <br>
>>}
10. Làm thế nào để có thể viết custom method implemetations cho Jpa Repository. Cho ví dụ

Answer:

- Bước 1: Khai báo Generic Interface Repository nhận tham số kiểu T tổng quát
>public interface CustomRepo<T>{  
>  void refresh(T entity);  
>}

- Bước 2: Tạo một class có tên là  CustomRepoImpl. Chú ý tên của class buộc phải tuân theo quy ước InterfaceName + "Impl" . Nếu không tuân thủ quy tắc này, khi biên dịch sẽ gặp lỗi
>public class CustomRepoImpl<T> implements CustomRepo<T>{ <br>
>  @PersistenceContext <br>
>  private EntityManager em; <br>
> <br>
>  @Override <br>
>  @Transactional <br>
>  public void refresh(T entity) { <br>
>  em.refresh(entity);    <br>
>  }  <br>
>} <br>

- Bước 4: Tạo ra interface  repository ứng với từng Entity cụ thể
>@Repository <br>
>public interface PersonRepository extends JpaRepository<Person, Long>,
>CustomRepo<Person>{ <br>
>Optional<Person> findByName(String name); <br>
>}

11. Hãy nêu 1 ví dụ sử dụng **sorting** và **paging** khi query đối tượng Employee ở trên
> - List<Employee> findAllByFirstName(String firstName, Pageable pageable); <br>
> - List<Employee> findAllByFirstName(String firstName, Sort sort);

12. Có 3 Entity **Product.java** và **Category.java** và **Tag.java**

-   Hãy bổ sung định nghĩa quan hệ **One to Many** giữa bảng**Category (One) -- Product
    (Many)**. Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null.
-   Hãy bổ sung định nghĩa quan hệ **Many to Many** giữa bảng **Tag(Many) -- Product(Many)**.

```java:Product.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
}
```

```java:Category.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
}
```

```java:Tag.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tag")
public class Tag {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
```

13. Có 2 Entity **Student.java** và **Course.java**

```java:Student.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
}
```

```java:Course.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="course")
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
```

Hãy mô tả quan hệ **Many to Many**:

-   Một Student có thể học nhiều Course.
-   Một Course có nhiều Student tham gia.
-   Bảng trung gian giữa Student và Course cần có một trường là điểm score kiểu int giá trị gán vào từ 0 đến 10. Cần validate ở phương thức setter

Dữ liệu mẫu để kiểm thử:

student.sql

```
INSERT INTO student (id, name) VALUES (1, 'bob');
INSERT INTO student (id, name) VALUES (2, 'alice');
INSERT INTO student (id, name) VALUES (3, 'tom');
INSERT INTO student (id, name) VALUES (4, 'jane');
INSERT INTO student (id, name) VALUES (5, 'van');
INSERT INTO student (id, name) VALUES (6, 'long');
```

course.sql

```
INSERT INTO course (id, name) VALUES (1, 'math');
INSERT INTO course (id, name) VALUES (2, 'music');
INSERT INTO course (id, name) VALUES (3, 'history');
```

Nội dung môn học và điểm (Bảng trung gian)

```
bob học {math: 7, music: 5, history: 8}
alice học {math: 8, music: 2, history: 9}
tom học {math: 4, history: 10}
jane học {music: 9, history: 8}
van học {math: 9, music: 7, history: 6}
long học {math: 10, music: 3}
```

14. Với kết quả của câu 13: Hãy lập trình JPARepository và viết Unit test để tính

-   Trả về liệt kê sinh viên tham gia từng môn học `Map<String, List<Student>>`: key là
    tên môn học, value là danh sách sinh viên đăng ký
-   Viết Native Query để tính điểm trung bình một môn bất kỳ
-   Liệt kê danh sách sinh viên học math nhưng không học music

15. Cho class `User.java` như sau

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
   private String email;
   private String password;
}
```

Viết câu lệnh query để tìm kiếm **UserDto** bao gồm các thuộc tính **(id, name, email)** theo cách sau

-   Method query + Convert to Dto
-   JPQL Query
-   Native Query
-   Projection

16. Cho class `Post.java` như sau

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {
   @Id
   private String id;
   private String title;
}
```

Viết custom generate id để tạo id ngẫu nhiên cho đối tượng post theo 2 cách

-   **Cách 1** : Random ra 1 chuỗi ngẫu nhiên (UUID)
-   **Cách 2** : Id được random theo cấu trúc sau `post-1`, `post-2`, ...
