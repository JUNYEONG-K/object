# 객체지향 프로그래밍

## 영화 예메 시스템
영화 예매 시스템이다. 영화별로 할인 정책이 있다. 비율 할인 정책과 금액 할인 정책. 영화는 두 개의 정책 중 하나의 정책만 적용할 수 있다.
또한 할인 조건이 있는데, 이는 여러 개의 조건을 붙일 수 있다. 조건 중 하나라도 만족한다면 할인 정책이 적용된다.

## 객체지향 프로그래밍을 향해

### 협력, 객체, 클래스
객체지향은 객체를 지향하는 것이다. 하지만 대부분의 사람들은 어떤 `클래스(Class)`가 필요한지를 먼저 고민한다.
이것은 객체지향의 본질과는 거리가 멀다. 우리는 객체에 초점을 맞추어야 한다.

1. 어떤 클래스가 필요한지를 고민하기 전에 어떤 객체들이 필요한지 고민하라.
2. 객체를 독립적인 존재가 아니라 기능을 위해 협력하는 공동체의 일원으로 봐야 한다.

클래스는 공통적인 상태와 행동을 공유하는 객체들을 추상화한 것이다. 따라서 클래스의 윤곽을 잡기 위해서는 어떤 객체들이 어떤 상태와 행동을 가지는지를 먼저 결정해야 한다.

또한 객체는 홀로 존재하는 것이 아니다. 의존하며 살아가는 협력적인 존재이다.
객체를 협력하는 공동체의 일원으로 바라보는 것은 설계를 유연하고 확장 가능하게 만든다.

훌륭한 협력이 훌륭한 객체를 낳고 훌륭한 객체가 훌륭한 클래스를 낳는다.

### 도메인의 구조를 따르는 프로그램 구조
문제를 해결하기 위해 사용자가 프로그램을 사용하는 분야를 `도메인(domain)`이라고 부른다.
객체지향 패러다임이 강력한 이유는 요구사항을 분석하는 초기 단계부터 프로그램을 구현하는 마지막 단계까지 객체라는 동일한 추상화 기법을 사용할 수 있기 떄문이다.
요구사항과 프로그램을 객체라는 동일한 관점에서 바라볼 수 있기 때문에 도메인을 구성하는 개념들이 프로그램의 객체와 클래스로 매끄럽게 연결될 수 있다.

클래스 기반의 객체지향 언어에서는 도메인 개념들을 구현하기 위해 클래스를 사용한다.
도메인의 네이밍이나 관계가 클래스의 네이밍과 의존성에 거의 유사하게 가져가면 된다.
클래스로 한 번 만들어보자.

* Screening(상영 정보)
  * 상영할 영화(movie), 순번(sequence), 상영 시작 시간(whenScreened) 를 인스턴스 변수로 가진다.
  * 상영 시작 시간을 반환하는 getStartTime 메서드, 순번의 일치 여부를 검사하는 isSequence 메서드, 기본 요금을 반환하는 getMovieFee 메서드

해당 클래스를 살펴보면, 변수의 가시성은 private 이고 메서드의 가시성은 public 이다.
내부와 외부를 구분한 것인데, 내부와 외부를 구분해야 하는 이유는 경계의 명확성이 객체의 자율성을 보장하기 때문이다.
객체가 객체의 메서드를 통해 데이터를 변환해야지, 모든 데이터를 public 으로 열어두면 다른 객체가 객체의 데이터를 변환할 수 있다.

#### 자율적인 객체
객체는 상태(state)와 행동(behavior)을 함께 가지는 복합적인 존재이다. 그리고 객체는 스스로 판단하고 행동하는 자율적인 존재이다.
이 두 가지 사실은 서로 깊이 연관되어 있다.

객체지향 이전의 패러다임에서는 데이터와 기능이라는 독립적인 존재를 서로 엮어 프로그램을 구성했다. 
이와 달리 객체지향은 객체라는 단위 안에 데이터와 기능을 한 덩어리로 묶음으로써 문제 영역의 아이디어를 적절하게 표현할 수 있게 했다.
이처럼 데이터와 기능을 객체 내부로 함께 묶는 것을 `캡슐화`라고 부른다.

또한 대부분의 객체지향 프로그래밍 언어들은 `접근 수정자(access modifier)`를 제공함으로써
외부에서의 접근을 통제할 수 있는 `접근 제어(access control)` 메커니즘도 함께 제공한다.

이렇게 객체 내부의 접근을 통제하는 이유는 객체를 자율적인 존재로 만들기 위해서다.
객체지향의 핵심은 스스로 상태를 관리하고 판단하고 행동하는 자율적인 객체들의 공동체를 구성하는 것이다.
객체가 자율적인 존재로 우뚝 서기 위해서는 외부의 간섭을 최소화해야 한다.

캡슐화와 접근제어는 객체를 두 부분으로 나눈다.
외부에서 접근 가능한 `퍼블릭 인터페이스(public interface)`와 오직 내부에서만 접근 가능한 `구현(implement)`로 나뉜다.
`인터페이스와 구현의 분리(separation of interface and implementation)` 원칙은 훌륭한 객체지향 프로그램을 만들기 위해 따라야 하는 핵심 원칙이다.

일반적으로 객체의 상태는 숨기고 행동만 외부에 공개해야 한다.

#### 프로그래머의 자유
프로그래머의 역할을 `클래스 작성자(class creator)와 클라이언트 프로그래머(client programmer)`로 나누자. 많은 책에서는 서버와 클라이언트라고 나눈다.
어쨌든, 클래스 작성자는 새로운 데이터 타입을 프로그램에 추가하고, 클라이언트 프로그래머는 작성자가 추가한 데이터 타입을 사용한다.

클래스 작성자는 클라이언트 프로그래머에게 필요한 부분만 공개하고 나머지는 꽁꽁 숨겨야 한다. 클라이언트 프로그래머는 필요한 것들만 가져와 자신의 일을 하면 된다.
이를 통해 클래스 작성자는 클라이언트 프로그래머에 대한 영향을 걱정하지 않고 내부 구현을 마음대로 변경할 수 있다. 이를 `구현 은닉(implement hiding)`이라고 부른다.

구현 은닉은 둘 모두에게 유용한 개념이다. 클라이언트 프로그래머는 내부의 구현은 무시한 채 인터페이스만 알고 있어도 클래스를 사용할 수 있기 때문에 머릿속에 담아둬야하는 지식의 양을 줄일 수 있다.
클래스 작성자는 인터페이스를 바꾸지 않는 한 외부에 미치는 영향을 걱정하지 않고도 내부 구현을 마음대로 변경할 수 있다.
클라이언트가 알아야 할 지식의 양은 줄이고, 작성자는 자유로운 구현의 변경이 가능하다.

따라서 클래스를 개발할 때마다 인터페이스와 구현을 깔끔하게 분리하기 위해 노력해야 한다.

### 협력하는 객체들의 공동체
영화 예매 시스템을 마저 살펴보자.

Money 라는 금액과 관련된 다양한 계산을 구현하는 클래스를 만들었다. 앞선 1장 예시에서는 금액을 구현하기 위해 Long 타입을 사용했다. 왜 굳이 Money 라는 클래스를 만들었을까?
Long 타입은 변수의 크기나 연산자의 종류와 관련된 구현 관점의 제약은 표현할 수 있지만, Money 타입처럼 저장하는 값이 금액과 관련되어 있다는 의미를 전달할 수는 없다.
또한 금액과 관련된 로직이 서로 다른 곳에 중복되어 구현되는 것을 막을 수 있다.

객체지향의 장점은 객체를 이용해 도메인의 의미를 풍부하게 표현할 수 있다는 것이다. 따라서 의미를 좀 더 명시적이고 분명하게 표현할 수 있다면 객체를 사용해서 해당 개념을 구현하자.
그 개념이 비록 하나의 인스턴스 변수만 포함하더라도 개념을 명시적으로 표현하는 것은 전체적인 설계의 명확성과 유연성을 높인다.

영화를 예매하기 위해서는 Screening, Movie, Reservation 인스턴스들이 서로의 메서드를 호출하며 상호작용해야 한다.
이처럼 시스테므이 어떤 기능을 구현하기 위해 객체들 사시에 이뤄지는 상호작용을 `협력(Collaboration)`이라고 한다.

```
screening 은 movie 로부터 영화가격을 받고, reservation 을 create 한다. 이 셋은 서로 협력한다.
```
객체지향 프로그램을 작성할 때는 먼저 협력의 관점에서 어떤 객체가 필요한지를 결정하고, 객체들의 공통 상태와 행위를 구현하기 위해 클래스를 작성한다.

#### 협력에 곤한 짧은 이야기
객체는 내부 구현이 아닌 퍼블릭 인터페이스를 통해 소통해야 한다. 서로 `요청(request)`하고 알맞은 `응답(response)`을 해야 한다.
요청자는 응답을 믿어야 한다.

객체끼리 상호작용하는 유일한 방법은 `메시지 전송과 수신(send a message, receive a message)`이다.
수신된 메시지를 처리하기 위한 자신만의 방법이 바로 `메서드(method)`이다.

메시지와 메서드를 구분하는 것은 매우 중요하다. 그것이 `다형성(polymorphism)`의 시작이다.

## 할인 요금 구하기
### 할인 요금 계산을 위한 협력 시작하기

영화의 가격은 Movie 클래스의 calculateMovieFee 메서드에서 결정되는데, 해당 메서드는 discountPolicy에 calculateDiscountAmount 메시지를 전송해 할인 요금을 반환 받는다.
그리고 Movie의 기본 요금인 fee에서 할인 요금만큼 차감한다.

```java
public class Movie {
  public Money calculateMovieFee(Screening screening) {
    return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
}
```
그런데 이 메서드 안에는 한 가지 이상한 점이 있다. 어떤 할인 정책을 사용할 것인지 결정하는 코드가 어디에도 존재하지 않는다.
금액할인? 비율할인? 어떤 할인 정책을 적용하는 것일까?
지금은 단지 discountPolicy에게 메시지를 전송할 뿐이다.

객체지향 패러다임에 익숙한 사람이라면 이 코드가 익숙해야 한다. (난 조금 익숙한 것 같다 ㅎㅎ)
이 코드에는 객체지향에서 중요하다고 여겨지는 두 가지 개념이 숨겨져있다. 하나는 `상속(inheritance)`이고, 다른 하나는 `다형성(polymorphism)`이다.
그리고 그 기반에는 `추상화(abstraction)`라는 원리가 숨겨져 있다.

### 할인 정책과 할인 조건
할인 정책은 금액 할인과 비율 할인 정책으로 구분된다.
두 가지 할인 정책은 대부분의 구현이 유사하고, 계산하는 방식만 조금 다를 것이다. `할인`이라는 공통적 목적을 가지고 있으며, 이를 추상화할 수 있다 우리는.

따라서 우리는 부모 클래스인 DiscountPolicy 안에 중복 코드를 두고, AmountDiscountPolicy 와 PercentDiscountPolicy 클래스가 각각 부모 클래스를 상속 받게 할 것이다.
각각의 클래스는 DiscountPolicy의 getDiscountAmount 메서드를 구현하면 된다.

DiscountPolicy 는 calculateDiscountAmount 메서드를 통해, 할인 정책에 포함된 할인 조건들을 모두 검사하며, 하나라도 만족한다면 getDiscountAmount 추상 메서드를 호출해 할인 요금을 계산한다.
만족하는 할인 조건이 없다면 0원을 반환한다.
DiscountPolicy 는 할인 여부와 요금 계산에 필요한 전체적인 흐름은 정의하지만, 실제로 요금을 계산하는 부분은 추상 메서드에 위임한다.
실제로는 자식 클래스에서 오버라이딩한 메서드가 실행이 된다.

이처럼 부모 클랫에 기본적인 알고리즘의 흐름을 구현하고, 중간에 필요한 처리를 자식 클래스에게 위임하는 디자인 패턴을 `Template Method Pattern`이라고 한다.

할인 조건을 나타내는 DiscountPolicy 클래스는 인터페이스로 선언했다. 순번 조건과 기간 조건이라는 두 가지 할인 조건을 각각 구현하자.
SequenceCondition 클래스에서는 sequence 가 일치하는지 isSatisfiedBy 메서드에서 확인한다.
PeriodCondition 클래스에서는 상영 요일이 같고, 상영 시작 시간이 startTime과 endTime 사이에 있는지 확인한다.

이후 각각 AmountDiscountPolicy 클래스와 PercentDiscountPolicy 도 구현해보았다.
Movie 클래스는 DiscountPolicy 로부터 할인 금액만 받아오고, 실제로 차감하는 것은 Movie 클래스에서 수행한다.
DiscountPolicy 로부터 최종 영화값을 받지 않는다는 것이다. 자기 데이터는 자기가 처리한다!

```
Movie -> DiscountPolicy -> DiscountCondition
```
위와 같은 flow로 메시지를 요청하고 응답을 받아 Movie에서 최종적으로 영화값을 계산한다.

### 할인 정책 구성하기
요청사항은 다음과 같다.

하나의 영화에 대해 단 하나의 할인 정책만 설정할 수 있지만 할인 조건의 경우에는 여러 개를 적용할 수 있다.
이런 제약을 Movie와 DiscountPolicy의 생성자에서 강제할 수 있다.

```java
public class Movie {
  public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
    this.discountPolicy = discountPolicy;
  }
}

public abstract class DiscountPolicy {
  public DiscountPolicy(DiscountCondition ...conditions) {
    this.conditions = Arrays.asList(conditions);
  }
}
```
이처럼 생성자의 파라미터 목록을 이용해 초기화에 필요한 정보를 전달하도록 강제하면 올바른 상태를 가진 객체의 생성을 보장할 수 있다.

## 상속과 다형성
Movie 클래스에는 어디에도 할인 정책을 정하는 코드가 없다. 그러나 우리가 Movie 객체를 생성할 땐 아래와 같이 선언할 수 있다.

```java
import java.time.Duration;

public class Main {
  public static void main(String[] args) {
    Movie avatar = new Movie("아바타",
            Duration.ofMinutes(120),
            Money.wons(10000),
            new AmountDiscountPolicy(Money.wons(1000), ...);
    Movie titanic = new Movie("타이타닉",
            Duration.ofMinutes(180),
            Money.wons(12000),
            new PercentDiscountPolicy(0.1), ...);
  }
}
```
우리는 Movie 생성자의 DiscountPolicy 위치에 추상클래스의 구현체인 AmountDiscountPolicy와 PercentDiscountPolicy 타입을 넣었다.

### 컴파일 시간 의존성과 실행 시간 의존성
Movie는 DiscountPolicy와 의존하지, AmountDiscountPolicy 나 PercentDiscountPolicy 와 의존하지는 않는다.
그들은 추상 클래스인 DiscountPolicy를 상속받는다.

결국 Movie와 각각의 구현체들은 추상 클래스인 DiscountPolicy 를 통해 연결되어 있기 때문에 의존성이 있다고 볼 수 있다.
Movie 인스턴스는 실행 시에 구현체의 인스턴스에 의존한다. 하지만 코드 수준에서는 추상 클래스에 의존한다.

이처럼 코드의 컴파일 시점 의존성과 실행 시점 의존성을 서로 다를 수 있다.
다시 말해, 클래스 사이의 의존성과 객체 사이의 의존성은 동일하지 않을 수 있다.

코드의 컴파일 시점 의존성과 실행 시점 의존성이 다르면 다를 수록 코드는 더 유연해지고 확장 가능해진다. 다만, 다르면 다를수록 코드를 이해하는 것도 어려워진다.
이와 같은 의존성의 양면성은 설계가 트레이드오프의 산물이라는 사실을 잘 보여준다.
설계가 유연해질수록 코드를 이해하고 디버깅하기는 점점 더 어려워진다. 반면 유연성을 억제하면 코드를 이해하고 디버깅하기는 쉬워지지만 재사용성과 확장 가능성은 낮아진다.
훌륭한 객체지향 설계자로 성장하기 위해서는 항상 유연성과 가독성 사이에서 고민해야 한다.

#### 차이에 의한 프로그래밍 programming by difference
부모 클래스와 다른 부분만을 추가해서 새로운 클래스를 쉽고 빠르게 만드는 방법

### 상속과 인터페이스
상속은 두 클래스 사이의 관계를 정의하는 방법이다. 상속 관계를 선언함으로써 한 클래스는 자동으로 다른 클래스가 제공하는 코드를 자신의 일부로 합칠 수 있다.
따라서 상속을 사용하면 코드 중복을 제거하고 여러 클래스 사이에서 동일한 코드를 공유할 수 있게 된다.

상속이 가치있는 이유는 부모 클래스가 제공하는 모든 인터페이스를 자식 클래스가 물려받을 수 있기 때문이다.
상속의 목적은 메서드나 인스턴스 변수를 재사용하는 것이 아니다.

인터페이스는 객체가 이해할 수 있는 메시지의 목록을 정의한다. 상속을 통해 자식 클래스는 자신의 인터페이스에 부모 클래스의 인터페이스를 포함하게 된다.
결과적으로 자식 클래스는 부모 클래스가 수신할 수 있는 모든 메시지를 수신할 수 있기 때문에 외부 객체는 자식 클래스를 부모 클래스와 동일한 타입으로 간주할 수 있다.

```java
public class Movie {
    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
```
위 코드를 보면, Movie 는 DiscountPolicy 의 인터페이스에 정의된 calculateDiscountAmount 메시지를 전송하고 있다.
방금 언급했듯, 자식 클래스인 AmountDiscountPolicy 와 PercentDiscountPolicy 의 인터페이스에도 calculateDiscountAmount 오퍼레이션이 포함되어 있다.
Movie 입장에서는 자신과 협력하는 객체가 어떤 클래스의 인스턴스인지가 중요한 것이 아니라, calculateDiscountAmount 메시지를 수신할 수 있다는 사실이 중요한 것이다.
다시 말해, Movie는 협력 객체가 calculateDiscountAmount 라는 메시지를 이해할 수만 있다면 그 객체가 어떤 클래스의 인스턴스인지는 상관하지 않는다는 것이다.

이처럼 자식 클래스가 부모 클래스를 대신하는 것을 `업캐스팅(upcasting)`이라고 한다. 자식 클래스는 상속을 통해 부모 클래스의 인터페이스를 물려받기 때문에 부모 클래스 대신 사용될 수 있다.

### 다형성
메시지와 메서드는 다른 개념이다.
Movie 는 DiscountPolicy 의 인스턴스에게 calculateDiscountAmount 메시지를 전송한다.
그렇다면 메시지를 받아 실행하는 메서드는 무엇인가?
Movie 와 상호작용, 즉 협력하기 위해 연결된 객체의 클래스가 무엇인가에 따라 달라진다. 각각의 구현체가 오버라이딩한 메서드가 호출되는 것이다.

코드 상에서 Movie 클래스는 DiscountPolicy 클래스에게 메시지를 전송하지만, 실행 시점에 실제로 실행되는 메서드는 Movie 와 협력하는 객체의 실제 클래스가 무엇인지에 따라 달라진다.
Movie 는 언제나 매번 동일한 메시지를 전송하지만, 실제로 호출되는 메서드가 무엇인지를 알 수 있는 시점은 메시지를 수신하는 객체의 클래스가 무엇이냐에 따라 달라진다.

이것이 `다형성(polymorphism)`이다.

다형성은 객체지향 프로그램의 컴파일 시점 의존성과 실행 시점 의존성이 다를 수 있다는 사실을 기반으로 한다. 이를 통해 서로 다른 메서드를 실행할 수 있게 한다.
동일한 메시지를 수신했을 때, 객체의 타입에 따라 다르게 응답을 할 수 있다는 말이다.
따라서 다형적인 협력에 참여하는 객체들은 모두 같은 메시지를 이해할 수 있어야 한다. 인터페이스가 동일해야 한다는 말이다.
AmountDiscountPolicy 와 PercentDiscountPolicy 가 다형적인 협력에 참여할 수 있는 이유는 이들이 DiscountPolicy 로부터 동일한 인터페이스를 물려받았기 때문이다.

* 컴파일 시점 의존성
  * Movie -> DiscountPolicy (calculateDiscountAmount)
* 실행 시점 의존성
  * Movie -> AmountDiscountPolicy (calculateDiscountAmount) or PercentDiscountPolicy (calculateDiscountAmount)


이렇게 객체지향이 컴파일 시점의 의존성과 실행 시점의 의존성을 분리하고, 하나의 메시지를 선택적으로 서로 다른 메서드에 연결할 수 있는 이유가 바로 `지연 바인딩(lazy binding)` 혹은 `동적 바인딩(dynamic binding)`이라고 불리는 메커니즘을 사용하기 덕분이다.

다형성은 추상적인 개념이며 이를 구현할 수 있는 방법은 꽤나 다양하다. 그 중 하나가 상속을 통해 동일한 인터페이스를 공유하는 클래스들을 하나의 타입 계층으로 묶는 것이다.

> 구현 상속과 인터페이스 상속

상속을 구현 상속(implementation inheritance)와 인터페이스 상속(interface inheritance)로 나눌 수 있다. 
이들은 각각 서브클래싱(subclassing)과 서브타이핑(subtyping)이라고도 불린다.
전자는 순수하게 코드 재사용을 위한 상속이다. 후자는 다형적인 협력을 위해 부모 클래스와 자식 클래스가 인터페이스를 공유하기 위한 상속이다.

우리는 인터페이스 상속을 위해 상속을 사용해야 한다. 구현을 재사용할 목적으로 상속을 사용하면 변경에 취약한 코드를 낳게 될 확률이 높다.

## 추상화와 유연성
### 추상화의 힘
할인 정책이나 할인 조건은 추상적인 개념이다. 프로그래밍 언어 측면에서는 DiscountPolicy 나 DiscountCondition 이 인터페이스에 초점을 맞추었기 때문에 추상적이라고 볼 수 있다.
이들은 인터페이스를 정의하고 구현의 일부 또는 전체를 자식 클래스가 결정할 수 있도록 결정권을 위임한다.

추상화의 장점 중 하나는, 추상화의 계층만 따로 떼어 놓고 살펴보면 요구사항의 정책을 높은 수준에서 서술할 수 있다는 것이다. 
두 번째 장점은 추상화를 이용하면 설계가 좀 더 유연해진다는 것이다.
세부적인 내용을 무시하고 상위 정책을 쉽고 간단하게 표현할 수 있는 것은, 도메인의 이해를 높이는 데에 도움이 된다.

추상화의 자식 클래스는 상위의 협력 흐름을 그대로 따르게 된다. 디자인 패턴이나 프레임워크 모두 이러한 관점에서 추상화를 이용한다.
이들은 추상화를 이용해 상위 정책을 정의한다. 그걸 이제 프로그래머가 알아서 갖고 노는 것이다. 우리에게 결정권을 위임하는 것이다.
단, 디자인 패턴이나 프레임워크가 정해놓은 인터페이스 안에서 말이다.

### 유연한 설계
```java
public class Movie {
  public Money calculateMovieFee(Screening screening) {
      if (discountPolicy == null) return fee;
      return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
}
```
위 코드를 보면, 할인 정책이 적용되지 않은 영화에 대한 예외 케이스를 작성해주어야 한다.
이것은 예외 케이스인데, 우리는 항상 예외 케이스를 최소화하고 일관성을 유지할 수 있는 방법을 선택해야 한다.

```java
public class NoneDiscountPOlicy extends DiscountPolicy {
  @Override
  protected Money getDiscountAmount(Screening screening) {
    return Money.ZERO;
  }
}
```
이렇게 구현체를 하나 더 만들어보자.
그럼 아래와 같이 Movie 인스턴스를 만들 수 있다.
```java
import java.time.Duration;

public class Main {
  public static void main(String[] args) {
    Movie starWars = new Movie("스타워즈",
            Duration.ofMinutes(210),
            Money.wons(10000),
            new NoneDiscountPolicy());
  }
}
```
이렇게 추상클래스의 구현체인 `NoneDiscountPolicy` 라는 클래스를 새로 추가하는 것만으로 Movie 클래스의 수정 없이 애플리케이션의 확장이 가능하다.
만약 해당 클래스가 없었더라면 위에서 언급한 것처럼 `if (discountPolicy == null) 인 경우`를 고려하는 일관성이 깨지는 코드를 작성해야 했을 것이다.

결론은 간단하다. 유연성이 필요한 곳에 추상화를 사용하라.

### 추상 클래스와 인터페이스 트레이드오프
이 책의 68 ~ 69 장에 나오는 이야기인데, 다이어그램을 보며 이해하는 것이 좋을 것 같아 따로 정리하지는 않겠다.
다만 절대 넘어가서는 안되는 챕터이다. 중요하다.

