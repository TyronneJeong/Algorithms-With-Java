package basicofjava.exercise;

import javax.management.AttributeList;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private Stack stack;
    private SynchronousQueue synchronousQueue;
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
         *
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
    }
}
