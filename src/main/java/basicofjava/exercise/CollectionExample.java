package basicofjava.exercise;

import javax.management.AttributeList;
import java.util.*;
import java.util.concurrent.*;

/**
 * Collection extends Iterabel<E>
 * <p>
 * [superinterface]
 * Iterable<E>
 * <p>
 * [subinterfaces]
 * BeanContext, BeanContextServices, BlockingDeque<E>, BlockingQueue<E>, Deque<E>,
 * List<E>, NavigableSet<E>, Queue<E>, Set<E>, SortedSet<E>, TransferQueue<E>
 * <p>
 * [Implmenting classes]
 * AbstractCollection, AbstractList, AbstractQueue, AbstractSequentialList, AbstractSet,
 * ArrayBlockingQueue, ArrayDeque, ArrayList, AttributeList,
 * BeanContextServicesSupport, BeanContextSupport,
 * ConcurrentHashMap.KeySetView, ConcurrentLinkedDeque, ConcurrentLinkedQueue, ConcurrentSkipListSet,
 * CopyOnWriteArrayList, CopyOnWriteArraySet,
 * DelayQueue, EnumSet, HashSet, JobStateReasons,
 * LinkedBlockingDeque, LinkedBlockingQueue, LinkedHashSet, LinkedList, LinkedTransferQueue,
 * PriorityBlockingQueue, PriorityQueue,
 * RoleList, RoleUnresolvedList, Stack, SynchronousQueue, TreeSet, Vector
 */
public class CollectionExample {
    /* ArrayList */
    private ArrayBlockingQueue<String> arrayBlockingQueue;
    private ArrayDeque<String> arrayDeque;
    private ArrayList<String> arrayList;
    private AttributeList attributeList;

    /* Concurrent Series */
    private ConcurrentHashMap<String, String> concurrentHashMap;
    private ConcurrentLinkedDeque concurrentLinkedDeque;
    private ConcurrentLinkedQueue concurrentLinkedQueue;
    private ConcurrentSkipListSet concurrentSkipListSet;

    /* Copy Op Series - with concurrent */
    private CopyOnWriteArrayList copyOnWriteArrayList;
    private CopyOnWriteArraySet copyOnWriteArraySet;

    /* */
    private DelayQueue delayQueue;
    private EnumSet enumSet;
    private HashSet hashSet;

    /* Linked Series */
    private LinkedBlockingDeque linkedBlockingDeque;
    private LinkedBlockingQueue linkedBlockingQueue;
    private LinkedHashSet linkedHashSet;
    private LinkedList linkedList;
    private LinkedTransferQueue linkedTransferQueue;

    /* etc */
    private PriorityQueue priorityQueue;
    private Stack stack;
    private TreeSet treeSet;
    private Vector vector;

    Iterator it;
    /* exec */
    public void exec() throws Exception {
        System.out.println("\n########################################################");
        /**
         * ArrayBlockingQueue
         * - 고정길이의 배열 형태의 큐를 제공 한다.
         * - FIFO (First In First Out) 의 자료형이다.
         */
        arrayBlockingQueue = new ArrayBlockingQueue(4);
        arrayBlockingQueue.add("A");
        arrayBlockingQueue.add("B");
        arrayBlockingQueue.put("C");
        arrayBlockingQueue.put("D");
        arrayBlockingQueue.poll();
        arrayBlockingQueue.put("E");

        it = arrayBlockingQueue.iterator();
        System.out.println("\n# ArrayBlockingQueue");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * ArrayDeque
         * - 리스트의 양쪽 끝에서 삽입과 삭제가 모두 허용되는 자료구조
         * - add(후순위추가) push(선순위추가) 둘다 가능 하다.
         * - pop 은 선순위 항목을 추출한다.
         */
        arrayDeque = new ArrayDeque<String>();
        arrayDeque.add("A");
        arrayDeque.add("B");
        arrayDeque.push("C");
        arrayDeque.push("D");
        arrayDeque.pop();
        arrayDeque.pop();

        it = arrayDeque.iterator();
        System.out.println("\n# ArrayDeque");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * ArrayList
         * - 자료 저장을 위하여 임시 배열을 이용하는 리스트 객체.
         *   대량의 데이터 삽입&수정 시 대량의 배열 복사가 동반되므로 성능저하의 문제가 있다.
         * - Thread-safe 하지 않으므로 개발자가 직접 동기화를 해야 한다.
         * - Thread-safe 한 리스트 : synchronizedList
         * - 배열 인덱스가 존재하여 자료 검색에 유리하다.
         *
         * [Keeping vectors efficient]
         * - 자바의 ArrayList 와 C++ 의 std::vector 는 underlying arrays 를 관리하는 데이터 자료형이다.
         * 자료가 담길때 마다 이들은 현재 Array 를 삭제하고 새로운 Array를 만들어 Contents를 복사한다.
         * 때문에 기본적으로 자료가 추가 될 때 마다, 오버 헤드 폭이 증가하게 되며 수행속도는 O(n)에 해당된다.
         */
        arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.remove(0);
        arrayList.add(0, "A");

        it = arrayList.iterator();
        System.out.println("\n# ArrayList");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * AttributeList
         * - MBean Server 와 MBeanSerconnection 속성 정보를 저장하기 위한 리스트 객체
         * - Serialized form 이다.
         * - ArrayList 상속 객체이다.
         */
        attributeList = new AttributeList();
        attributeList.add("A");
        attributeList.add("B");
        attributeList.add("C");
        attributeList.add("D");

        it = attributeList.iterator();
        System.out.println("\n# AttributeList");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * ConcurrentHashMap<String, String>
         * - 2개 이상의 스레드가 동일 데이터를 엑세스 하는 경우가 많을때 권장되는 헤쉬 맵
         * - 동일한 synchronized HashMap 으로 [synchronizedMap] 이 있다.
         * - ConcurrentHashMap 는 synchronized 선언 없이도 전체 맵이 thread safe 하다.
         * - 데이터를 쓰는 동안 Lock 을 걸어 동시성을 유지한다.
         * - [synchronizedMap]
         *   ㄴ Object 단위에서 Synchronization이 유지된다.
         *   ㄴ 읽고/쓰기 모두에서 Lock 이 발생된다.
         *   ㄴ Locking 시 전체 콜랙션의 오버헤드가 발생된다.
         *   ㄴ Iterator 를 리턴한다.
         */
        concurrentHashMap = new ConcurrentHashMap<String, String>();
        concurrentHashMap.put("A","VALUE01");
        concurrentHashMap.put("B","VALUE02");
        concurrentHashMap.put("C","VALUE03");
        concurrentHashMap.put("D","VALUE04");

        concurrentHashMap.forEach((K, V)->{
            System.out.println("Key >> "+K +", Value >> "+V);
        });

        System.out.println("\n########################################################");
        /**
         * ConcurrentLinkedDeque
         * - Concurrent 시리즈는 Thread Safe 를 보장해준다.
         * - 입력이 양방향으로 가능 하다.
         */
        concurrentLinkedDeque = new ConcurrentLinkedDeque();
        concurrentLinkedDeque.add("A");
        concurrentLinkedDeque.add("B");
        concurrentLinkedDeque.push("C");
        concurrentLinkedDeque.push("D");

        it = concurrentLinkedDeque.iterator();
        System.out.println("\n# ConcurrentLinkedDeque");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * concurrentLinkedQueue
         * - Concurrent 시리즈는 Thread Safe 를 보장해준다.
         */
        concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add("A");
        concurrentLinkedQueue.add("B");
        concurrentLinkedQueue.add("C");
        concurrentLinkedQueue.add("D");

        it = concurrentLinkedQueue.iterator();
        System.out.println("\n# ConcurrentLinkedQueue");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * ConcurrentSkipListSet
         * - SkipListSet 또는 SkipListMap 은 다수의 스레드에 의해 엑세스 되는 컨테이너를 정렬할때 유용합니다.
         * - 고성능 Lock-Free Hash Table & List Based Sets 에 기반하여 생성된 자료구조
         * - synchronized 시 데이터 엑세스시 Lock 을 걸지 않고 Skip 함으로 오버헤드가 발생하지 않는다.
         */
        concurrentSkipListSet = new ConcurrentSkipListSet();
        concurrentSkipListSet.add("A");
        concurrentSkipListSet.add("B");
        concurrentSkipListSet.add("C");
        concurrentSkipListSet.add("D");

        it = concurrentSkipListSet.iterator();
        System.out.println("\n# ConcurrentSkipListSet");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * CopyOnWriteArrayList
         * - exnteds Object
         * Thread Safe variant of ArrayList & Concurrent collection class
         * underlying array 구조로 add, set 등의 명령어 입력시 새로운 배열에 복사 대입이 일어난다.
         */

        copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("A");
        copyOnWriteArrayList.add("B");
        copyOnWriteArrayList.add("C");
        copyOnWriteArrayList.add("D");

        it = copyOnWriteArrayList.iterator();
        System.out.println("\n# CopyOnWriteArrayList");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * CopyOnWriteArraySet
         * - exnteds Object
         * Thread Safe variant of ArrayList & Concurrent collection class
         * underlying array 구조로 add, set 등의 명령어 입력시 새로운 배열에 복사 대입이 일어난다.
         */
        copyOnWriteArraySet = new CopyOnWriteArraySet();
        copyOnWriteArraySet.add("A");
        copyOnWriteArraySet.add("B");
        copyOnWriteArraySet.add("C");
        copyOnWriteArraySet.add("D");

        it =copyOnWriteArraySet.iterator();
        System.out.println("\n# CopyOnWriteArraySet");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * DelayQueue
         * - DelayQueue는 Delayed 타입을 객체를 통해서 지연된 처리를 할 수 있도록 해주는 Interface.
         * - Delayed 인터페이스를 활용한 Queue
         * Delay 객체는 주어진 시간 이후 동작하는 객체를 위한 설계되었다.
         */
        delayQueue = new DelayQueue();
        MyDelayed myDelayed1 = new MyDelayed("Sample-1", 4000000000L);
        MyDelayed myDelayed2 = new MyDelayed("Sample-2", 3000000000L);
        MyDelayed myDelayed3 = new MyDelayed("Sample-3", 2000000000L);
        MyDelayed myDelayed4 = new MyDelayed("Sample-4", 1000000000L);

        delayQueue.add(myDelayed1);
        delayQueue.add(myDelayed2);
        delayQueue.add(myDelayed3);
        delayQueue.add(myDelayed4);

        while (!delayQueue.isEmpty()) {
            MyDelayed md = (MyDelayed) delayQueue.take();
            System.out.println("### : " + md.name + "반환!!");
        }

        System.out.println("\n########################################################");
        /**
         * EnumSet
         * - 열거형 자료형인 Enum 의 부분 또는 전체 집합(Set)을 관리하는 자료형
         * - EnumSet의 내부는 bit vector 로 표현된다.
         * EnumSet의 모든 메서드는 산술 비트 연산을 사용하므로 일반적인 연산이 매우 빠르게 처리된다.
         * HashSet과 비교했을때 데이터가 예상 가능한 순서로 저장되어 있으며,
         * 데이터 버킷을 찾기 위한 HashCode 생성이 필요없으며
         * 연산 처리시 비트 연산이 사용되므로 더 빠르다고 할 수 있다.
         *
         * C언어의 bir flag 나 bit mask 등을 대신 해서 EnumSet을 이용할 수 있다.
         * HashMap 을 대신하여 EnumMap 을 이용할 수 있다.
         */
        enumSet = EnumSet.noneOf(Color.class);
        enumSet = EnumSet.of(Color.RED, Color.BLUE);
        enumSet.add(Color.GREEN);
        enumSet.add(Color.BLACK);

        it = enumSet.iterator();
        System.out.println("\n# EnumSet");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * HashSet
         * - superinterface - AbstractSet
         * - 저장순서가 유지되지 않는다. (저장순서 유지 Set - LinkedHashSet
         * - HashSet 은 정렬되지 않는다. TreeSet 은 정렬을 보장한다.
         * - 저장되는 자료형은 모두 Object 로 취급 한다.
         * - Hash 검색을 위한 인덱스가 없어서 HashMap 보다 성능이 떨어진다.
         * - Set 의 내부구현은 결국 Map 이다.
         */
        hashSet = new HashSet();
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("C");
        hashSet.add("D");

        // Hash 값은 실제 주소 값과 구별되는 스트링 벨류에 대한 헤쉬 코드 생성 값을 말한다.
        // 이 HashCode 를 통해 중복을 식별한다.
        System.out.println("contains >> "+ hashSet.contains("C"));

        it = hashSet.iterator();
        System.out.println("\n# HashSet");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * LinkedBlockingDeque
         * -
         * - 큐가 저장되는 MAX_VALUE 를 지정하여 사용하는 자료형
         */
        linkedBlockingDeque = new LinkedBlockingDeque();
        linkedBlockingDeque.add("A");
        linkedBlockingDeque.add("B");
        linkedBlockingDeque.put("C");
        linkedBlockingDeque.put("D");
        linkedBlockingDeque.poll();

        System.out.println("\n# LinkedBlockingQueue");
        it = linkedBlockingDeque.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * LinkedBlockingQueue
         * - 연결된 노드를 기반으로 선택적으로 제한하는 블로킹 큐 자료형
         * - 큐가 저장되는 MAX_VALUE 를 지정하여 사용하는 자료형
         */
        LinkedBlockingQueue linkedBlockingQueue;
        linkedBlockingQueue = new LinkedBlockingQueue();
        linkedBlockingQueue.add("A");
        linkedBlockingQueue.add("B");
        linkedBlockingQueue.add("C");
        linkedBlockingQueue.add("D");
        linkedBlockingQueue.poll();

        it = linkedBlockingQueue.iterator();
        System.out.println("\n# LinkedBlockingQueue");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * LinkedHashSet
         * - HashSet의 확장 구현체
         * - HashSet의 경우 저장값의 정렬을 보장 하지 않지만 Linked 순서를
         * - Serializable , Cloneable , Iterable<E> , Collection<E> , Set<E>
         */
        linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("A");
        linkedHashSet.add("B");
        linkedHashSet.add("C");
        linkedHashSet.add("D");

        it = linkedHashSet.iterator();
        System.out.println("\n# LinkedHashSet");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * LinkedList
         * - 양방향 연결 리스트 (Doubly Linked List)
         * - 각각의 노드는 데이터와 함께 next , prev 노드 값을 관리한다.
         * - ArrayList와 같이 배열관리를 안하므로 데이터 추가와 삭제에 용이하다.
         * - Que 를 구현함에 있어 ArrayDeque 가 LinkedList 보다 낫다는 글이 많다.
         */
        linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        it = linkedList.iterator();
        System.out.println("\n# LinkedList");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * LinkedTransferQueue
         * - Serializable , Iterable<E> , Collection<E> , BlockingQueue<E> , TransferQueue<E> , Queue<E>
         * - 블록킹 Queue 의 성질을 가지고 있다. + TransferQueue 의 특성을 가지고 있다.
         * - 소비자 Consumer 가 필요로 할때 메세지 큐를 수신받을 수 있도록 만들어진 자료구조
         */
        linkedTransferQueue = new LinkedTransferQueue();
        linkedTransferQueue.add("A");
        linkedTransferQueue.add("B");
        linkedTransferQueue.add("C");
        linkedTransferQueue.add("D");

        it = linkedTransferQueue.iterator();
        System.out.println("\n# LinkedTransferQueue");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * PriorityQueue
         * - 우선순위 QUEUE 는 FIFO(First In First Out)의 자료형으로 여기에 우선순위 옵션이 추가된 자료형이다..
         * - 내부는 이진트리 구조로 구성되어 있다.
         *
         */
        priorityQueue = new PriorityQueue(Collections.reverseOrder()); // 역순의 우선순위지정
        priorityQueue.add("A");
        priorityQueue.add("B");
        priorityQueue.add("C");
        priorityQueue.add("D");
        priorityQueue.offer("Z");

        System.out.println("가장 높은 우선순위 >> "+priorityQueue.peek());

        priorityQueue.poll();

        it = priorityQueue.iterator();
        System.out.println("\n# PriorityQueue");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * Stack
         * - LIFO (Last In First Out) 의 대표적인 자료구조
         * - 그래프 깊이 우선 탐색(DFS)에 사용
         */
        stack = new Stack();
        stack.add("A");
        stack.add("B");
        stack.push("C");
        stack.push("D");

        System.out.println("pop something >> " + stack.pop()); // 마지막 추가 항목이 출력됨

        it = stack.iterator();
        System.out.println("\n# Stack");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * TreeSet
         * - Set 의 구현체이다.
         * - HashSet 과 다른점은 TreeSet 은 이진 탐색트리 구조로 구성되어 있다.
         * - HashSet 과 비교하여 데이터의 추가와 삭제에는 시간이 더 걸린다.
         * - 이진탐색트리(BinarySearchTree) 형태로 저장하기 때문에 기본적으로 nature ordering 을 지원한다.
         * - Comparator(비교회로) 객체를 이용하여 정렬방법을 임의로 지정해 줄 수 있다.
         *
         * [Red-Black Tree]
         * - TreeSet 은 이진탐색트리중에서도 성능을 향상시킨 레드-블랙 트리로 구현되어 있다. 일반적으로 이진
         * 탐색트리는 트리의 높이(Depth)만큼 시간이 걸리지만 데이터의 값이 트리에 잘 분산되어 있지 않은 경우
         * 최종 결과값 도출 까지 상당한 시간을 소모하게 된다. 이런 데이터 클러스터링 문제를 해결한 것이 레드
         * 블랙 트리가 된다. 레드 블랙 트리는 부모노드 보다 작은 값은 왼쪽으로, 큰 값은 오른쪽 노르도 배치하여
         * 데이터 추가 삭제시 트리가 한쪽으로 치우치는 것을 방지하여 준다.
         */
        treeSet = new TreeSet(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
                //return o1.trim().compareTo(o2.trim());
            }
        });
        treeSet.add("A");
        treeSet.add(" C");
        treeSet.add("D");
        treeSet.add(" B");
        treeSet.stream().sorted(String.CASE_INSENSITIVE_ORDER);

        it = treeSet.iterator();
        System.out.println("\n# TreeSet");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n########################################################");
        /**
         * Vector (리스트)
         * - AbstractList<E> 를 상속받은 객체 고로 기본적으로 List 이다.
         * - implements List<E>, RandomAccess, Cloneable, java.io.Serializable
         * - 객체직렬화가 보장되는 자료형으로 ArrayList 와 쓰임새가 비슷하다.
         * - 스레드간 동기화를 위해 객체직렬화하는 비용이 크다. 때문에 ArrayList 보다는 성능이 다소 떨어진다.
         * - ArrayList 와는 다르게 인덱스 값을 보관하는 배열을 따로 설정 하지 않는다.
         */
        vector = new Vector();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");
        vector.set(1, "H");
        vector.add(1, "B"); // 인덱스를 지정하여 Element 를 추가하면 나머지 모든 항목을 한칸씩 뒤로 밀어낸다.

        it = vector.iterator();
        System.out.println("\n# Vector");
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    /**
     * Enumeration - Color
     */
    private enum Color {
        RED, YELLOW, GREEN, BLUE, BLACK, WHITE
    }

    /**
     * Enumeration - Coin
     */
    private enum Coin {
        PENNY(1), NICKEL(5), DIME(10), QUATER(25);
        Coin(int value){this.value = value;}
        private final int value;
        public int value() {return value;}
    }

    /**
     * Enumeration - CoinColor
     */
    private enum CoinColor {COOPER, NICKEL, SILVER};
    static CoinColor color(Coin c){
        switch(c){
            case PENNY:
                return CoinColor.COOPER;
            case NICKEL:
                return CoinColor.COOPER;
            case DIME:
            case QUATER:
                return CoinColor.COOPER;
            default:
                throw new AssertionError("Unknown coin: "+c);
        }
    }

    /**
     * DelayQueue 확인을 위한 Deplayed 구현 클래스
     */
    class MyDelayed implements Delayed {
        String name;
        final long DELAY_TIME = 1000000000L; //모든 Delayed는 1초씩 증가
        final long EXPIRE_TIME;
        long accumulateTime = 0; //누적시간

        public MyDelayed(String name ,long expire_time) {
            this.name = name;
            this.EXPIRE_TIME = expire_time;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            this.accumulateTime += DELAY_TIME;
            System.out.println(name + "가 반환되기까지 남은 시간" + (EXPIRE_TIME - accumulateTime)/1000000000L + "초");
            return unit.toNanos(EXPIRE_TIME - accumulateTime > 0 ? DELAY_TIME : 0);
        }
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.EXPIRE_TIME , ((MyDelayed)o).EXPIRE_TIME);
        }
    }
}
