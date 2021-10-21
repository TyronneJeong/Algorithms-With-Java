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
    private ConcurrentHashMap concurrentHashMap;
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
        System.out.println("\n# arrayBlockingQueue");
        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("\n# stream.filter");
        arrayBlockingQueue.stream()
                .filter(e -> !e.equalsIgnoreCase("C"))
                .forEach(e-> System.out.println(e));

        System.out.println("\n# stream.map");
        arrayBlockingQueue.stream()
                .map(s->s.toLowerCase())
                .forEach(e-> System.out.println(e));

        System.out.println("\n# stream.sorted");
        arrayBlockingQueue.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(e-> System.out.println(e));

        System.out.println("\n# stream.concat");
        Stream<String> stream = Stream.<String>builder().add("A").add("G").add("F").build();
        stream = Stream.concat(stream, arrayBlockingQueue.stream());
        stream.map(s -> s.toUpperCase(Locale.getDefault()))
                .sorted()
                .limit(6)
                .reduce((c, v)-> c+"-"+v)
                .stream()
                .forEach(e-> System.out.println(e));



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
//        System.out.println("\n# arrayDeque");
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }

//        System.out.println("\n########################################################");



    }
}
