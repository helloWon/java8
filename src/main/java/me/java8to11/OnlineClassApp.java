package me.java8to11;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OnlineClassApp {
        public static void main(String[] args) {

                List<OnlineClass> springClasses = new ArrayList<>();
                springClasses.add(new OnlineClass(1, "spring boot", true));
                springClasses.add(new OnlineClass(2, "spring data jpa", true));
                springClasses.add(new OnlineClass(3, "spring mvc", false));
                springClasses.add(new OnlineClass(4, "spring core", false));
                springClasses.add(new OnlineClass(5, "rest api development", false));

                OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
                // key 값이 null이면 절대 안됨

                Optional<OnlineClass> optional = springClasses.stream()
                                .filter(oc -> oc.getTitle().startsWith("jpa"))
                                .findFirst();
                boolean present = optional.isPresent();
                System.out.println(present);

                // get 없는 경우 java.util.NoSuchElementException 발생
                // OnlineClass onlineClass = optional.get();
                // System.out.println(onlineClass.getTitle());

                // if문 사용해서 확인하고 꺼내기 (권장 X)
                if (optional.isPresent()) {
                        OnlineClass onlineClass1 = optional.get();
                        System.out.println(onlineClass1.getTitle());
                }

                // 권장
                optional.ifPresent(oc -> System.out.println(oc.getTitle()));

                // orElse: 이미 만들어진 상수 같은거
                // orElseGet: 동적으로 무언가 생성해야 하는 거
                // orElseThrow: 무언가 못 만드는 상황
                OnlineClass onlineClass = optional.orElseGet(() -> createNewClasss());
                System.out.println(onlineClass.getTitle());

                Optional<Integer> integer = optional.map(OnlineClass::getId);
                System.out.println(integer.isPresent());

                // flatMap: 알아서 양파껍질 까줌
                Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
                Optional<Progress> progress1 = progress.orElseThrow();
                progress1.orElseThrow();

                optional.flatMap(OnlineClass::getProgress);

        }

        private static OnlineClass createNewClasss() {
                System.out.println("creating new online class");
                return new OnlineClass(10, "New class", false);
        }
}
