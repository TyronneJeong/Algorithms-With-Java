package analysisofalgorithms.exercise;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

public class L27Hashing {
    /**
     * Hasing
     * - Hash Function 을 이용하여 해쉬함수값을 배열 인덱스로 사용하는 방법
     *
     * ex) 가령 해쉬 함수가 1024의 값을 100%으로 나눈 나머지값을 배열의 주소 값으로 활용한다는 가정이 있다면
     * 검색, 삽입, 정렬등에 모두 이 해쉬함수를 사용하여 크기가 100인 HashTable에 값을 저장 보관 가능해진다.
     * 이때, 우리가 사용한 해쉬 함수는 나머지 연산이므로 시간 복잡도가 크지 않지만, 해쉬함수의 종류와 유형 처리
     * 방식에 따라 그 속도는 달라질 수 있기 때문에 해쉬함수의 시간 복잡도가 O(1) 을 만족하기 위해서는 많은 가정
     * 하에 성립 가능함을 유의해야 한다.
     *
     * SUHA (Simple Uniform Hashing Assumption)
     * - 각각의 키가 모든 슬롯에 균등한 확률로 독립적으로 해슁 된다는 가정
     *   ㄴ 성능분석을 위해서 주로 하는 가정
     *   ㄴ hash 함수는 deterministic 하므로 현실에서는 저 가정의 성립이 불가능
     *
     * Collision 충돌
     * 서로 다른 두개 이상의 키가 동일 위치로 해슁 되는 경우 해결 방법이 필요해 진다.
     * ex) 대표적인 해결 방법으로 chaining 과 open addressing 이 존재한다.
     *     즉, 해쉬함수는 일반적으로 단사함수가 아니기 때문에 충돌이 발생한다.
     *
     * [Chaining]
     * - 둘 이상의 키가 하나의 슬롯에 해슁되는 경우
     *   ㄴ 길이가 n 인 하나의 연결 리스트가 만들어짐
     *   ㄴ chaining 이 발생되는 경우 탐색시간은 O(n) + 해쉬함수 계산시간 이 발생
     *
     * [Open Address]
     * - 모든 키를 해쉬 테이블 자체에 저장
     * - Linear Probing
     *   ㄴ 해슁으로 처음 저장된 위치 근처로 Circular 하게 동일 해슁값을 저장하는 방식
     *   ㄴ 해슁값에 해당하는 저장공간이 이미 점유된 경우에도 Circular 하게 다음 저장공간을 이용함.
     *   ㄴ 이처럼 Circular 하게 생성된 Cluster 는 점점 커지는 현상이 발생된다.
     *   ㄴ Cluster 화의 단점을 보완 한 것이 [Quadratic Probing] 이다.
     *     ㄴ Quadratic Probing 은 충돌 발생시 인근 공간이 아닌 발생된 지점에서의 1제곱, 2제곱, 3제곱의
     *        공간에 저장공간을 물색하는 식으로 Clustering 을 방지한다.
     * - Double Hasing
     *   ㄴ 서로 다른 두 개의 해쉬 함수를 이용하여 주소 공간을 할당.
     */
    private Hashtable<String, String> hashtable;

    public void solution(){
        /**
         * Hash Code in Java
         * - Java의 모든 오브젝트 클래스 는 HashCode() 메서드를 가지고 있다.
         * - 해당 메서드는 하나의 32비트 정수 값을 반환한다.
         * - TreeMap 과 유사한 인터페이스를 제공한다.
         *
         * [HashCode]
         * - HashCode 는 override 가능한 함수이며, 사용자가 원하는 해쉬 함수를 정의하지 않는 경우
         *   기본적인 hashCode 로직이 적용되게 된다.
         *
         * [Hash Function 으로의 사용]은
         * - HashCode 가 가리키게 되는 HashTable 과 hashCode 를 이용하여 얻은 0~M-1 까지의 배열
         *   인덱스를 추출하는 로직이 적용된 함수를 말한다.
         *
         * [HashMap]
         * - 내부적으로 하나의 배열을 해쉬 테이블로 사용한다. (default initial capacity is 16)
         * - 해쉬함수의 유형은 아래와 유사하다.
         *   ------------------------------------------------------------------
         *   private int hash(key key){
         *      return (key.hashCode() & 0x7fffffff) % M;
         *   }
         *   ------------------------------------------------------------------
         * - chaining 으로 collision 을 해결한다.
         * - load factor 를 지정할 수 있고, 저장된 키의 개수가 load factor 를 초과하면, 더 큰
         *   배열을 할당 하고 키들을 재배치 하게 된다 (re-hashing)
         *
         * [HashSet]
         * - 입력값을 해쉬 함수를 이용하여 저장하는 집합 자료형
         *
         * (unordered_set == hashSet)
         * (unordered_map == hashMap)
         */

        // 동일한 스트링 값 sample 1 과 2는 동일한 HashCode 값을 가진다.
        String sample1 = "123";
        String sample2 = "123";
        String sample3 = "333";
        sample3 = "123";

        // 모두 동일한 HashCode 값을 가진다.
        System.out.println(sample1.hashCode());
        System.out.println(sample2.hashCode());
        System.out.println("123".hashCode());
        System.out.println(Integer.valueOf(123).toString().hashCode());
        System.out.println(sample3.hashCode());

        hashtable = new Hashtable<>();
        TreeMap treeMap = new TreeMap();
        HashMap hashMap = new HashMap();

        // hashtable
    }
}
