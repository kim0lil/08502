# 마이바티스

이번에 살펴 볼 것은 마이바티스의 기초 설정과 사용법 그리고 중급 사용법까지 설명 할 것입니다.

우리가 배울 마이바티스는 자바 퍼시스턴스 프레임워크의 하나로써 JDBC로 처리하는 상당부분의 코드와 파라미터 설정 및 결과 매핑을 대신해 줍니다.

아래와 같은 그림이 마이바티스의 전체적인 모양입니다.

<< 그림 1-1. mybatis architecture >>

![그림보기](./images/001/001.png)

먼저 전체적인 매퍼 프레임워크가 뭔지부터 살펴 보면서

차근 차근 마이바티스 구문을 살펴 보도록 하겠습니다.

## JDBC

JDBC(Java Database Connectivity)는 자바 데이터베이스를 연결하도록 지원하는 인터페이스로써 JDBC에서 전형적으로 사용되는(침투적인 코드) 코드가 많습니다.

아래 코드를 보면서 말해 보도록 하겠습니다.

[소스보기](./sources/001/step001.java)

이 코드는 데이터베이스와 접속 하기 위하는 jdbc의 고유 코드로써

이 행위를 통하여 데이터베이스에 접속하여 쿼리를 날려 처리 할 수 있는 것입니다.

따라서 이 모양은 쿼리를 보내는 순간 똑같이 유지 될 것입니다.

아래와 같이 말입니다.

[소스보기](./sources/001/step002.java)

select, update, insert, delete 등 모든 구문에서 데이터베이스에 접속 하는 구문이 있는 것을 확인 할 수 있습니다.

이 구문들은 공통화 되지 못한 관계로 작은 변경에도 큰 파동을 일으킬 수 있습니다.

(예로써 url의 host ip가 변경 될 경우 `DriverManager.getConnection`시 사용 되는 모든 구문이 변하여야 합니다.)

[소스보기](./sources/001/step003.java)

따라서 우리는 이 접속 정보를 추상화 할 필요가 생겼습니다.

### jdbc 접속 정보의 추상화

그러면 접속 정보를 추상화 해보도록 하겠습니다.

접속 정보는 아래와 같이 공통적으로 특정 한 행동을 하는 정보를 추출하여 분리 하는 것 부터 시작합니다.

우리는 클래스 드라이버를 로딩하고 Connection을 맷는 과정을 메소드 추출 방법을 통하여 `getConnection`으로 추상화 해 보도록 하겠습니다.

[소스보기](./sources/001/step004.java)

그 외에도 여러 방법을 사용하여 분리 할 수 있지만 이 장에서 설명할 범위에 벗어 나므로 넘어 가도록 하겠습니다.

### 설정 분리

이번에는 설정 정보를 사용하여 접속 시 처리 해 보도록 하겠습니다.

아래와 같이 설정 파일을 하나 만들어 봅니다.

[소스보기](./sources/001/step005.properties)

다음으로 실행 시 설정 파일을 불러 오도록 하겠습니다.

[소스보기](./sources/001/step006.java)

### SQL 분리

이번에는 SQL문을 분리 해 보도록 하겠습니다.

조회 하는 소스를 다시 한번 살펴 보겠습니다.

[소스보기](./sources/001/step007.java)

`prepareStatement`를 불러 오고 `SQL`에 파라미터를 매핑하여 실행 하는 것을 볼 수 있습니다.

먼저 SQL문을 외부로 분리 하여 보도록 하겠습니다.

파일을 하나 생성한 다음 SQL문을 등록 합니다.

[소스보기](./sources/001/step008.properties)

그런 다음 등록 한 SQL문을 로드 시 불러 오도록 합니다.

[소스보기](./sources/001/step009.java)

### Mapper 등장

이제 `executeQuery`를 실행하여 실행 결과 값을 반환 받을 수 있습니다.

이 `executeQuery` 문은 `ResultSet`을 반환하도록 작성 되어 있습니다.

따라서 쿼리문을 담아 보도록 하겠습니다.

그 전에 먼저 값을 담을 도메인을 하나 생성하여 보도록 하겠습니다.

[소스보기](./sources/001/step010.java)

이제 `ResultSet`을 실행 하는 코드를 추가해 보도록 하겠습니다.

[소스보기](./sources/001/step011.java)

매퍼 클래스를 만들어 보도록 하겠습니다.

[소스보기](./sources/001/step012.java)

(매퍼 클래스는 데이터베이스와 연결을 담당하고 `prepareStatement`에서 `SQL`을 불러 오고 `ResultSet`과 `shop`을 매핑해 주는 역활을 합니다.)

다음으로 `selectOne` 메서드를 생성해 보겠습니다.

기존 jdbc 소스를 사용하면 이렇게 사용 될 것입니다.

[소스보기](./sources/001/step013.java)

이번에는 좀 더 추상화 시켜 보도록 하겠습니다.

추상화 대상은 `Connection`에서 `PrepareStatement`를 추출하고 setObject로 값을 넣는 행동입니다.

[소스보기](./sources/001/step014.java)

다음 추상화 대상은 ResultSet 과 shop 객체를 묶는 행동을 추상화 해 보도록 하겠습니다.

그러기 위해서 먼저 인터페이스를 하나 생성하여 Mapping할 수 있도록 느슨하게 만들어 보겠습니다.

[소스보기](./sources/001/step015.java)

느슨하게 만들어진 인터페이스를 기반으로 추상화 대상을 처리 하는 함수를 하나 만들어 보겠습니다.

[소스보기](./sources/001/step016.java)

이제 가변 인자가 되는 항목들을 외부로 맞겨 모듈화를 해 보겠습니다.

[소스보기](./sources/001/step017.java)

실행 아래와 같이 실행 할 수 있습니다

[소스보기](./sources/001/step018.java)

고생하셨습니다.

이렇게 우리가 만든 클래스를 퍼시스턴스 계층을 담당하는 매퍼 프레임워크 라고 합니다.

마이바티스에서는 좀 더 활용적이고 범용 적으로 사용 할 수 있는 프레임워크를 제공하니 다음 장 부터는 마이바티스의 설정방법 부터

사용법까지 설명해 보도록 하겠습니다.

## 마이바티스 설정 파일

먼저 실행 할 정보를 확인 하기 위하여 데이터 베이스를 확인해 보도록 하겠습니다.

데이터베이스의 정보는 아래와 같습니다.

```s

> database information

driver   : oracle
url      : 13.209.118.27:1521/xe
username : ADMIN
password : ADMIN

```

다음으로 테이블을 보도록 하겠습니다.

```s

CREATE TABLE TB_USER (
    USER_ID NUMBER(5)     PRIMARY KEY,
    USER_NAME VARCHAR(50) NOT NULL,
    USER_ROLE CHAR(1)     NOT NULL,
    USER_GRADE VARCHAR(5) NOT NULL,
    USER_LOCATION VARCHAR(100)
)

```

이렇게 작성한 다음 데이터베이스에 접속 하여 테이블을 생성합니다.

<< 그림 1-2. oracle table create >>

![그림보기](./images/001/002.png)

테이블이 생성 된 것을 확인 한 다음 이제 자바로 돌아 가서 먼저 해당 테이블과 

그전에 먼저 프로젝트 구조를 정리 한 다음

매핑 되는 도메인을 생성해 보도록 하겠습니다.

프로젝트의 구조는 아래와 같습니다.

<< 그림 1-3. base structure >>

![그림보기](./images/001/003.png)

다음으로 maven을 추가 하여 프로젝트 구조를 등록해 보도록 하겠습니다.

[소스보기](./sources/002/002/pom.xml)

메이븐의 더 자세한 사항은 아래 링크를 참조 하세요.

[메이븐 빌드툴 정보](https://github.com/kim0lil/08501/blob/master/MAVEN.md)

다음으로 마이바티스 라이브러리와 jdbc라이브러리를 등록 합니다.  
(jdbc의 경우는 nexus를 이용할 것이며 오라클의 jdbc 라이브러리를 등록 하시면 됩니다.)

[소스보기](./sources/002/003/pom.xml)

이제 준비는 끝났습니다.

마이바티스의 설정 정보 부터 작성해 보도록 하겠습니다.

마이바티스의 설정을 등록 할 때에는 설정 파일을 생성합니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step001.xml)

다음으로는 먼저 데이터베이스와 연동할 데이터 소스를 등록 합니다.

데이터 소스는 `dataSource` 태그로 등록 합니다.  
(`dataSource`는 camelCase로 작성 되는 것을 잊지 마세요)

[소스보기](./sources/002/004/src/resources/mybatis/config/step002.xml)

`dataSource`에 연동 정보(driver, url, username, password)를 등록 합니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step003.xml)

다음으로 dataSource 를 커넥션을 관리할 `type`를 등록 합니다.

type은 아래와 같이 3종류의 타입으로 분리 합니다.

- - -

UNPOOLED : 디비 접속 요청이 있을 경우 실시간으로 커넥션 객체를 OPEN하고 CLOSE 합니다.
POOLED : 디비 접속 요청 이전 이미 커넥션을 만들어 두고 풀에 넣어 둔 다음 요청이 있을 경우 풀의 커넥션을 반환합니다.
JNDI : application에서 직접 데이터베이스 커넥션을 관리 할 떄 사용하며 설정 정보를 JNDI로 정의 한다는 뜻입니다.

- - -

우리는 `UNPOOLED` 타입으로 등록해 보도록 하겠습니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step004.xml)

이번에는 트랜젝션 관리를 위하여 `transactionManager`를 등록 하겠습니다.

(태그의 순서는 지켜야 오류가 나지 않습니다))

[소스보기](./sources/002/004/src/resources/mybatis/config/step005.xml)


`transactionManager`의 타입을 등록해 보도록 하겠습니다.

타입으로는 아래 jdbc에서 자동적으로 커밋을 관리하는 `JDBC`와 커밋과 롤백을 메뉴얼하게 하는 `MANAGED`가 있습니다.

우리는 맨 처음 커밋과 롤백을 메뉴얼 하게 작업할 예정이므로 `MANAGED`로 등록 합니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step006.xml)

우리가 작성한 dataSource와 transactionManager의 경우는 데이터베이스 접속을 위한 환경입니다.

애플리케이션 서버에서는 하나의 서버에 여러 환경에 존재 할 수 있으며

이 환경을 등록 하기 위하여 `environment`태그와 `environments`태그로 등록 합니다.

(각 `environment`는 고유한 `id`로 구분하기 떄문에 `environment`에는 `id`태그를 등록  하며 `environments`에서는 기본 환경을 `default` 속성을 통하여 등록 합니다.)

[소스보기](./sources/002/004/src/resources/mybatis/config/step007.xml)

이번에는 mapper를 등록해 보도록 하겠습니다.

mapper는 environment아래에 등록 합니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step008.xml)

다음으로 mapper.xml 파일을 생성하여 등록 합니다.

(mapper는 resources/mybatis/mapper 폴더에 생성 합니다.)

<< 그림 1-4. mapper folder >>

![그림보기](./images/001/004.png)

마이바티스 설정 파일에 매퍼를 등록합니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step009.xml)

매퍼는 하나 이상 등록 할 수 있으므로 mapper를 mappers로 감싸 줍니다.

[소스보기](./sources/002/004/src/resources/mybatis/config/step010.xml)

environment와 mapper를 묶어 하나의 설정(configuration을 만들어 줍니다.)

[소스보기](./sources/002/004/src/resources/mybatis/config/step011.xml)

xml 버전 정보와 mybatis dtd 를 등록합니다.

xml 버전은 경우 아래와 같이 등록합니다

```s
<?xml version="1.0" encoding="UTF-8"?>
```

dtd의 경우 mybatis 공식 사이트에서 배포 하고 있는 정보를 등록합니다.

```s
<!DOCTYPE configuration PUBLIC "-//mybatis.org/DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
```

[소스보기](./sources/002/004/src/resources/mybatis/config/step012.xml)

이번에는 mapper를 등록 해 보도록 하겠습니다.  

(* mapper를 변경할 예정이므로 추후 실행 시 마지막 ampper를 mapper의 resource로 등록 하여야 합니다.)

매퍼는 insert, update, delete, select 태그를 사용하여 등록 할 수 있습니다.

우리는 TB_USER 테이블을 조회 할 것이기 때문에 select태그를 사용해 등록 할 수 있습니다.

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step002.xml)

각 태그(select, update, delete, insert)는 하나 이상 등록 할 수 있으므로 고유한 id를 지니고 있습니다.

이제 id 속성을 등록해 보겠습니다.

(id는 `select` + `Table명`으로 등록 합니다.)

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step003.xml)

다음으로는 쿼리문을 등록합니다.

먼저 sqlplus에 접속 하여 쿼리문을 날려 보겠습니다.

<< 그림 1-5. select query execution >>

![그림보기](./images/001/005.png)

위와 같이 쿼리 결과가 나왔다면 쿼리를 select문 안에 등록 합니다.

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step004.xml)

이제 도메인을 등록 해 보도록 하겠습니다.

현제 우리는 데이터베이스의 테이블과 자바의 클래스가 1:1 mapping 될 것이기 때문에

도메인은 java에서 등록 합니다.

<< 그림 1-6. tb_user domain class >>

![그림보기](./images/001/006.png)

도메인으로 TB_USER를 등록합니다.

[소스보기](./sources/002/004/src/java/org/mybatis/domain/TB_USER.java)

다시 mapper로 돌아 가서 타입을 매핑해 보도록 하겠습니다.

타입은 `resultType`과 `resultMap`이 있습니다.

- - -

resultType : 프로퍼티와 매핑할 구문이 일치할 경우 사용합니다.

resultMap : 프로퍼티와 매핑 할 구문이 일치하지 않을 경우 사용합니다.

두 타입의 자세한 차이는 뒤편에 나옵니다.

- - -

우선 resultType 으로 매핑해 보도록 하겠습니다.

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step005.xml)

sql(select) 매핑 구문이 완성 되었습니다.

하나의 매퍼에는 여러 sql 매핑구문이 등록 될 수 있습니다.

따라서 mapper 태그를 사용하여 하나의 그룹으로 묶어 준 다음

매퍼 역시 여러 매퍼가 존재 할 수 있으므로 고유한 아이디(namespace)를 등록 합니다.

(namespace의 설정 기준이 없을 경우 `package명`+`파일 명`으로 등록 합니다. )

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step006.xml)

이제 `xml`정보와 `mybatis`에서 제공하는 `mapper`구문이 정의 된 dtd를 등록해 보도록 하겠습니다.

```xml
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```

( dtd는 마이바티스 사이트에 방문하여 확인 할 수 있습니다. [사이트](https://mybatis.org/) )

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step007.xml)

이제 실행 구문을 만들어 보도록 하겠습니다.

서비스 패키지에 새로운 클래스를 생성한 다음 아래와 같이 실행 구문을 등록 합니다.

실행 순서는 아래와 같습니다.

1. Resources와 config.xml 경로를 통하여 파일을 읽어 들이는 Reader를 반환 받습니다.
2. Reader 객체를 사용하여 SqlSessionFactory를 만듭니다.
3. 트랜젝션 발생 시에 SqlSessionFactory를 통하여 SqlSession을 생성합니다.
4. 쿼리를 실행하고 SqlSession의 commit rollback 구문을 통하여 쿼리를 처리 합니다.

SqlSessionFactory는 하나만 존재 하면 되므로 설정 클래스를 하나 만들어 두도록 하겠습니다.

[SqlManager 소스보기](./sources/002/004/src/java/org/mybatis/config/SqlManager.java)

이제 select 서비스를 하나 등록 하겠습니다.

[소스보기](./sources/002/004/src/java/org/mybatis/service/step001.java)

이제 이 서비스를 테스트할 test 프레임워크를 등록해 보도록 하겠습니다.

먼저 pom.xml에 junit의존성을 등록 합니다.

[소스보기](./sources/002/004/src/pom.xml)

다음으로 test를 실행할 테스트 리소스를 등록 합니다.

<< 그림 1-7. test resource >>

![그림보기](./images/001/007.png)

이제 pom.xml에 testSourceDirectory를 등록 합니다.

[소스보기](./sources/002/004/src/pom.xml)

테스트에 필요한 클래스를 하나 생성하여 테스트를 실행 합니다.

[소스보기](./sources/002/004/src/test/org/mybatis/MybatisTest.java)

메이븐 테스트를 실행하여 테스트 해 보겠습니다.

<< 그림 1-8. mvn test success >>

![그림보기](./images/001/008.png)

만일 same의 값을 바꾸면 아래와 같이 테스트가 실패 할 것입니다.

<< 그림 1-9. mvn test failure >>

![그림보기](./images/001/009.png)

이제 서비스를 추가해 보곘습니다.

추가할 서비스는 아래와 같습니다.

1. 단건 조회 - Mapper = selectOne_TB_USER, method = selectOne
2. 입력      - Mapper = insert_TB_USER, method = insert
3. 수정      - Mapper = update_TB_USER, method = update
4. 단건 삭제 - Mapper = deleteOne_TB_USER, method = deleteOne
5. 전체 삭제 - Mapper = deleteALL_TB_USER, method = deleteAll

각 항목별 이름은 오른쪽에 있습니다.

이제 매퍼 부터 추가해 보도록 하겠습니다.

(파라미터도 parameterType와 parameterMap으로 등록 할 수 있습니다)

[소스보기](./sources/002/004/src/resources/mybatis/mapper/step008.xml)

다음으로 서비스에 추가해 보도록 하겠습니다.

[소스보기](./sources/002/004/src/java/org/mybatis/service/step002.java)

이제 테스트를 추가해 보도록 하겠습니다.

