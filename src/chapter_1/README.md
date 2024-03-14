# 객체, 설계

로버트 L. 글래스(Robert L. Glass)는 <<소프트웨어 크리에이티비티 2.0>>에서 '이론 대 실무'라는 흥미로운 주제에 관한 개인적인 견해를 밝히고 있다.
이론이 먼저일까, 실무가 먼저일까? 로버트는, 어떤 분야를 막론하고 이론을 정립할 수 없는 초기에는 실무가 먼저 급속한 발전을 이룬다고 말한다.

건축처럼 역사가 오래된 여느 다른 공학 분야에 비해 상대적으로 짧은 소프트웨어 분야의 역사를 감안했을 때, 로버트가 하고자 하는 말은 분명하다.
소프트웨어 분야는 아직 걸음마 단계에 머물러있기 떄문에 이론보다 실무가 더 앞서 있으며 실무가 더 중요하다는 것이다.

소프트웨어 개발에서 실무가 이론보다 앞서 있는 대표적인 분야로 `소프트웨어 설계`와 `소프트웨어 유지보수`를 들 수 있다.
해당 이론들은 애초에 이론을 세우자고 해서 만들어진게 아니라, 실무에서 반복적으로 적용되던 기법들을 이론화한 것들이 대부분이다.
특히 소프트웨어 유지보수에 관해서는 효과적인 이론이 발표된 적이 거의 없다.

이 책은 훌륭한 객체지향 프로그램을 설계하고 유지보수하는 데 필요한 원칙과 기법을 설명하기 위해 쓰여졌으며, 로버트의 이야기처럼, 이론을 중심에 두지 않을 것이다.
코드를 통해 개념을 받아들일 것이다.

## 티켓 판매 애플리케이션 구현하기

추첨을 통해 선정된 관람객에게 무료로 관람할 수 있는 초대장을 발송할 것이다.
이벤트에 당첨되지 않은 관람객은 티켓을 구매해야 한다.
따라서 관람객을 입장시키기 전에 이벤트 당첨 여부를 확인하고, 그에 맞게 대처해야 한다.

`Invitation`과 `Ticket` 클래스를 우선 만들어보자. 이벤트 당첨자는 티켓으로 교환할 초대장을 가지고 있을 것이고, 그렇지 않은 관람객은 티켓을 구매할 수 있는 현금을 보유하고 있을 것이다.
따라서 관람객이 가지고 올 수 있는 소지품은 `초대장, 현금, 티켓` 세 가지 뿐이다. 소지품 보관 용도로 가방(Bag) 클래스를 만들자.

관람객의 가방 안에는 현금과 초대장을 함께 보관하거나, 초대장 없이 현금만 보관하는 두 가지 경우만 존재할 것이다. 이를 생성자를 통해 제약을 강제하자.
```java
public class Bag {
    public Bag(long amount) {
        this(null, amount);
    }

    public Bag(Invitation invitation, long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }
}
```
우리는 위와 같이 생성자로 제약을 강제함으로써, 두가지 케이스의 Bag 인스턴스만 생산할 수 있다.

다음은 관람객이라는 개념을 구현하는 Audience 클래스다. 얘는 Bag 만 가지고 있으면 된다.
관람객이 소극장에 입장하기 위해서는 매표소에서 초대장을 티켓으로 교환하거나, 현금을 가지고 티켓을 구매해야 한다.
따라서 매표소에는 관람객에게 판매할 티켓과 티켓의 판매 금액이 보관돼 있어야 한다.
이를 구현하는 `TicketOffice` 클래스를 추가하자.

판매원은 매표소에서 초대장을 티켓으로 교환해주거나 티켓을 판매하는 역할을 수행한다. `TicketSeller`

그리고 소극장을 구현하는 `Theater` 클래스까지 구현하자.
관람객을 맞이할 수 있도록 `enter 메서드`를 함께 구현해보았다.

```java
public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
```

## 무엇이 문제인가

로버트 마틴의 <<클린 소프트웨어>>에는 소프트웨어 모듈이 가져야하는 세가지 기능에 관해 설명되어 있다.
모든 모듈은 제대로 실행되어야 하고, 변경이 용이해야 하며, 이해하기 쉬워야 한다.
첫 번째 조건은 아마 만족할 것 같다.

enter 메서드를 말로 한 번 풀어보자.
```
소극장이 직접, 관람객의 가방을 열어서 초대장 유무를 확인하고, 있으면 판매원이 티켓을 매표소에서 가져와서 관람객의 가방 안에 넣어준다.
초대장이 없다면, 판매원이 티켓을 매표소에서 가져오고, 관람객은 가방 안에서 티켓 값만큼 현금을 까고, 판매원은 매표소의 현금을 티켓 값만큼 채우고, 관람객의 가방에 티켓을 넣는다.
```
문제는 관람객과 판매원이 소극장의 통제를 받는 수동적인 존재라는 점이다.
관람객의 입장에선 제 3자가 내 가방을 뒤지는 꼴이다. 판매원이 심지어 티켓을 가방에 넣는다.
이건 우리의 상식 밖이다.

이해 가능한 코드란 그 동작이 우리의 예상에서 크게 벗어나지 않는 코드다. 하지만 지금의 enter 메서드는 예상을 벗어난다.
현실에서는 관람객이 직접 자신의 가방에서 초대장을 꺼내 판매원에게 건넨다. 혹은 직접 돈을 꺼내 판매원에게 지불한다.
판매원은 매표소에 있는 티켓을 직접 꺼내 관람객에게 건네고 관람객에게서 돈을 받아 매표소에 보관한다.

또한 enter 메서드에서 알아야하는 세부사항들이 너무 많다는 점이 우리로 하여금 코드를 이해하기 어렵게 만든다.
Theater가 Audience의 Bag을 확인하고, 그 안의 현금과 티켓을 확인하고, TicketSeller가 TicketOffice의 티켓을 가져올 수 있다는 모든 세부 사실을 알고 있어야 한다.

하지만 가장 심각한 문제는, Audience와 TicketSeller를 변경할 경우 Theater도 함께 변경해야 한다는 사실이다.
변경에 취약한 코드는 최악이다.
관람객이 가방을 들고 있지 않다면? 현금이 아니라 신용카드를 이용해 결제를 한다면? 매표소 밖에서 거래가 이루어져야 한다면?

이것은 객체 사이의 `의존성(dependency)`과 관련된 문제이다. 의존성은 변경에 대한 영향을 암시한다.
의존성이라는 말은 어떤 객체가 변경될 때 그 객체에 의존하는 다른 객체도 함께 변경될 수 있다는 사실을 내포하고 있다.
물론 완전히 없앨 수는 없다. 필요한 최소한의 의존성만 유지하고, 불필요한 의존성은 제거하자.

객체 사이의 의존성이 과한 경우를 가리켜 `결합도(coupling)`가 높다고 말한다.
두 객체 사이의 결합도가 높으면 높을수록 함께 변경될 확률도 높아지기 때문에 변경하기 어려워진다.
따라서 설계의 목표는 객체 사이의 결합도를 낮춰 변경이 용이한 설계를 만드는 것이다.

## 설계 개선하기

코드를 이해하기 어려운 이유는 Theater가 관람객의 가방과 판매원의 매표소에 직접 접근하기 때문이다.
이것은 관람객과 판매원이 자신의 일을 스스로 처리해야 한다는 우리의 직관을 벗어난다.
의도를 정확하게 의사소통하지 못하기 때문에 코드가 이해하기 어려워진 것이다.
Theater는 지금 Audience와 TicketSeller에 결합되어 있다.

해결방법은 간단하다. Theater가 Audience와 TicketSeller에 관해 너무 세세한 부분까지 알지 못하도록 정보를 차단하면 된다.
Theater가 원하는 것은 관람객이 소극장에 입장하는 것뿐이다.
따라서 관람객이 스스로 가방 안의 현금과 초대장을 처리하고 판매원이 스스로 매표소의 티켓과 판매 요금을 다루게 한다면 이 모든 문제를 해결할 수 있다.

다시 말해 관람객과 판매원을 `자율적인 존재`로 만들면 되는 것이다.

### 자율성을 높이자
우선 Theater의 enter 메서드에서 TicketOffice에 접근하는 모든 코드를 TicketSeller 내부로 숨기자.

```java
public class Theater {
    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}

public class TicketSeller {
    public void sellTo(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketOffice.plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
```
어처럼 개념적이나 물리적으로 객체 내부의 세부적인 사항을 감추는 것을 `캡슐화(encapsulation)`라고 한다.
캡슐화의 목적은 변경하기 쉬운 객체를 만드는 것이다.
캡슐화를 통해 객체 내부로의 접근을 제한하면 객체와 객체 사이의 결합도를 낮출 수 있기 때문에 설계를 좀 더 쉽게 변경할 수 있다.

우선 위 과정을 통해 Theater와 TicketOffice, Ticket 간의 관계를 끊었다. Theater의 결합도를 낮추었다.
Theater는 TicketOffice가 TicketSeller 내부에 존재한다는 사실 조차 모른다.
단지 ticketSeller가 sellTo 메시지를 이해하고 응답할 수 있다는 사실만 알고 있을 뿐이다.

Theater는 오직 TicketSeller의 `인터페이스(interface)`에만 의존한다.
TicketSeller가 내부에 TicketOffice 인스턴스를 포함하고 있다는 사실은 `구현(implement)`의 영역에 속한다.

객체를 인터페이스와 구현으로 나누고, 인터페이스만을 공개하는 것은 객체 사이의 결합도를 낮추고 변경하기 쉬운 코드를 작성하기 위해 따라야 하는 가장 기본적인 설계 원칙이다.

이번엔 Audience의 캡슐화를 개선하자. TicketSeller는 Audience의 Bag에 직접 접근하고 있는데, 그건 캡슐화가 잘 되지 않은 것이다.
Bag 인스턴스에 접근하는 객체가 Theater에서 TicketSeller로 바뀌었을 뿐, 여전히 Audience는 자율적인 존재가 아니다.

```java
public class TicketSeller {
    public void sellTo(Audience audience) {
        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
    }
}

public class Audience {
    public Long buy(Ticket ticket) {
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        } else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
```
Audience의 getBag 메서드를 제거해서, TicketSeller에서 Bag으로 직접 접근하지 않도록 하였다.
Audience 클래스에 buy 메서드를 만들어, TicketSeller에서 buy 메서드를 직접 호출하도록 하였다.
Audience는 직접 가방의 초대장을 확인한다.

이를 통해 TicketSeller와 Audience의 결합도를 낮추었다. TicketSeller는 더이상 Bag 클래스에 접근하지 않는다.
또한 TicketSeller는 Audeience의 인터페이스에만 의존하고, 내부 구현이 캡슐화되었으므로 Audience의 구현 수정이 TicketSeller에 영향을 미치지 않는다.

이를 통해 Audience와 TicketSeller는 각각 내부 구현을 외부에 노출하지 않고, 자신의 문제를 스스로 책임지고 해결하는 자율적인 존재가 되었다.
Audience와 TicketSeller 내부 구현이 변경되어도 Theater에는 영향을 미치지 않는다.

자기 자신의 문제를 스스로 해결하도록 코드를 변경했다. 객체의 자율성을 높였다.
그 결과, 이해하기 쉽고 유연한 설계를 얻을 수 있었다.

### 캡슐화와 응집도
핵심은 객체 내부의 상태를 캡슐화하고, 객체 간에 오직 메시지를 통해서만 상호작용하도록 만드는 것이다.
밀접하게 연관된 작업만을 수행하고 연관성 없는 작업은 다른 객체에게 위임하는 객체를 가리켜 `응집도(cohesion)`가 높다고 말한다.
자신의 데이터를 스스로 처리하는 자율적인 객체를 만들면 결합도를 낮출 수 있을 뿐더러 응집도를 높일 수 있다.

### 절차지향과 객체지향
리팩토링 하기 전의 Theater 클래스의 enter 메서드를 살펴보자.
해당 메서드 안에서 Audience와 TicketSeller로부터 Bag과 TicketOffice를 가져와 관람객을 입장시키는 절차를 구현했다.
각 클래스는 관람객을 입장시키는 데 필요한 정보를 제공하고 모든 처리는 Theater의 enter 메서드에서 수행됐다.

이 관점에서 Theater의 enter 메서드는 `프로세스(Process)`이며
Audience, TicketSeller, Bag, TicketOffice는 `데이터(Data)`이다. 해당 클래스들은 데이터의 역할만을 수행한다.
이처럼 프로세스와 데이터를 별도의 모듈에 위치시키는 방식을 `절차지향적 프로그래밍(Procedural Programming)`이라고 부른다.
데이터로 사용된 클래스 중 하나라도 변경될 경우 Theater 클래스에 영향을 미친다.
프로세스가 필요한 모든 데이터에 의존해야 한다는 근본적인 문제점이 변경에 취약하다.

데이터와 프로세스를 동일한 모듈 내부에 위치하도록 하는 프로그래밍 방식이 바로 `객체지향 프로그래밍(Object-Oriented Programming)`이다.
의존성을 통제하고, 변경으로 인한 여파를 억제한다.
훌륭한 객체지향 설계의 핵심은 캡슐화를 이용해 의존성을 적절히 관리함으로써 객체 사이의 결합도를 낮추는 것이다.

### 책임의 이동 shift of responsibility
절차지향적 프로그래밍에서는 책임이 Theater 클래스의 enter 안에 집중되어 있었다.
객체지향적 설계를 통해 책임을 각 객체에 적절히 분산해야 한다.
자신을 스스로 책임지자. 객체지향 애플리케이션은 스스로 책임을 수행하는 자율적인 객체들의 공동체를 구성함으로써 완성된다.

객체지향 설계의 핵심은 적절한 객체에 적절한 책임을 할당하는 것이다.
설계를 어렵게 만드는 것은 의존성이다.
해결 방법은 불필요한 의존성을 제거함으로써 객체 사이의 결합도를 낮추는 것이다.
결합도를 낮추기 위해서는 캡슐화가 필요하다. 이를 통해 객체의 자율성을 높이고 응집도 높은 객체들의 공동체를 창조할 수 있다.

불필요한 세부사항을 캡슐화하는 자율적인 객체들이 낮은 겷바도와 높은 응집도를 가지고 협력하도록 최소한의 의존성만을 남기는 것이 훌륭한 객체지향 설계이다.

Bag을 자율적인 객체로 만들어보자.
```java
public class Audience {
    public Long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}

public class Bag {
    public Long hold(Ticket ticket) {
        if (hasInvitation()) {
            setTicket(ticket);
            return 0L;
        } else {
            setTicket(ticket);
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
```
가방 안에 ticket을 넣고, 현금을 차감하는 것을 Bag에서 하도록 했다. 또한 외부에서 호출하던 메서드인 `setTicket, hasInvitation, minusAmount` 메서드는 private 접근자로 수정하여 캡슐화하였다.
Bag의 구현을 캡슐화시켰으니, 이제 Audeience는 Bag의 구현이 아닌 인터페이스에만 의존할 수 있다.

TicketOffice의 자율성도 찾아줘볼까.
```java
public class TicketSeller {
    public void sellTo(Audience audience) {
        ticketOffice.sellTicketTo(audience);
    }
}

public class TicketOffice {
    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buy(getTicket()));
    }
}
```
마찬가지로 몇몇 메서드는 private 접근자를 통해 캡슐화하고, 인터페이스로만 소통하도록 수정하였다.

하지만 중요한 문제가 있다. TicketOffice와 Audience 간의 의존성이 추가되었다.
자율성을 높였지만 전체 설계의 관점에서는 결합도가 상승했다.

트레이드오프의 시점이 온 것이다. 우리는 둘 모두를 택할 수는 없다. 어떤 것을 우선할까?
우리는 일단 결합도를 낮추는 쪽으로 가보자.
훌륭한 설계는 적절한 트레이드오프의 결과물이다. 우리는 늘 의사결정을 해야한다.

우리는 객체의 자율성을 높이고자 지금껏 수정을 해왔다. Audience와 TicketSeller가 스스로 무언가를 하는 것은 우리의 직관, 상식과 일맥상통한다.
그러나 Theater, Bag, TicketOffice는? 이들은 사실 현실세계에서는 능동적인 존재가 아니다. 가방에 티켓을 넣고 돈을 꺼내고는 누군가 해주어야 한다.

비록 이처럼 현실세계에서는 수동적인 존재라고 하더라도 일단 객체지향의 세계에 들어오면 모든 것이 능동적이고 자율적인 존재로 바뀐다.
레베카 워프스브록(Rebecca Wirfs-Brock)은 이처럼 능동적이고 자율적인 존재로 소프트웨어 객체를 설계하는 원칙을 가치켜 `의인화(anthropomorphism)`라고 부른다.

비록 실세계에서는 생명이 없는 수동적인 존재라고 하더라도 생물처럼 스스로 생각하고 행동하도록 소프트웨어 객체를 설계하자.
모든 객체들이 자율적으로 행동하는 설계가 훌륭한 객체지향 설계이다.

## 객체지향 설계
설계란 코드를 배치하는 것이다.

설계와 코드작성은 동떨어져있는 개념이 아니다. 설계는 코드를 작성하는 매 순간 코드를 어떻게 배치할 것인지를 결정하는 과정에서 나온다.
설계는 코드 작성의 일부이며 코드를 작성하지 않고서는 검증할 수 없다.

예제코드를 돌이켜보자. 두 코드를 실행한 결과는 같다. 성능상 차이도 없을 것이다.
하지만 코드를 배치하는 방법은 완전히 다르다. 첫 번째 코드에서는 데이터와 프로세스를 나누어 별도의 클래스에 배치했다. Theater 클래스에 집중되었다.
두 번째 코드에서는 필요한 데이터를 보유한 클래스 안에 프로세스를 함께 배치했다. 자기 데이터는 자기가 책임지고 처리했다.

좋은 설계란 오늘 요구하는 기능을 온전히 수행하면서 내일의 변경을 매끄럽게 수용할 수 있는 설계다.
변경을 수용할 수 있는 설계가 중요한 이유는 두 가지이다. 요구사항이 쉽게 자주 변경되기 때문이다. 또한 버그 추적에 용이하기 때문이다.
코드를 수정하지 않으면 버그는 발생하지 않는다. 요구사항 변경은 코드 수정을 초래하고, 코드 수정은 버그 발생 가능성을 높인다.
모든 것이 한 곳에 몰려 있으면 버그 추적이 쉽지 않다.

의존성을 완전히 끊어낼 수는 없다. 적절히 관리하자.
